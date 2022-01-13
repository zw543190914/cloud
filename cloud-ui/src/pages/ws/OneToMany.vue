<!-- socket demo -->
<template xmlns="http://www.w3.org/1999/html">
  <div class = "app-container">
    <div class = "filter-container">
      接收：
      <el-input v-model = "news" type = "textarea" :rows = "4" class = "filter-item" placeholder = "没有消息"/>
    </div>
    <div class = "filter-container">
      当前用户ID:<el-input v-model = "userId" controls-position = "right" size = "small" @change = "initDisabledVal"/>
      <el-button class = "filter-item" :disabled = "initDisabled" type = "primary" @click = "initWebSocket"> 初始化监控 {{ initVal }}
      </el-button>
      <br></br>
      发送：
      <br></br>
      接收人：<el-input v-model = "targetUserId" type = "input" class = "filter-item"/>
      消息内容：
      <br></br>
      <el-input v-model = "messageVal" type = "textarea" :rows = "4" class = "filter-item"/>
      <el-button class = "filter-item" type = "primary" @click = "pushMessage">发送</el-button>
    </div>
    <div>
      <div ref = "print">
        <span>在线用户列表</span>
        <el-table
            :data="onlineUsers"
            border
            style="width: 100%">
          <el-table-column
              prop="date"
              label="日期"
              width="180">
          </el-table-column>
          <el-table-column
              prop="name"
              label="姓名"
              width="180">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址">
          </el-table-column>
        </el-table>
      </div>

    </div>
  </div>
</template>

<script>

  const userId = new Date().getTime();
  export default {

  name: 'socket',
  data() {
    return {
      initDisabled: false,
      initVal: '',
      websock: '',
      news: '',
      userId: userId,//接入的ID
      messageVal: '',
      targetUserId:'',
      tag:1,// 消息类型 1, "第一次(或重连)初始化连接",2, "聊天消息",3, "消息签收",5, "拉取好友"
      onlineUsers: []
    }
  },
  methods: {
    initWebSocket() {
      if (typeof (WebSocket) === 'undefined') {
        console.log('您的浏览器不支持会话')
      } else {
        this.close()
        this.initDisabled = true
        this.websock = new WebSocket('ws://127.0.0.1:18888/ws?userId=' + this.userId)
        // 打开事件
        this.websock.onopen = this.onopen
        // 获得消息事件
        this.websock.onmessage = this.onmessage
        // 关闭事件
        this.websock.onclose = this.onclose
        // 发生了错误事件
        this.websock.onerror = this.onerror
      }
    },
    onopen() {
      console.log('Socket 已打开')
      this.news = 'Socket 已打开'
    },
    onmessage(msg) {
      // 接收到的消息进入
      const data = JSON.parse(msg.data);
      console.log('onmessage',data)
      if (5 === data.tag) {
        const result = data.data
        this.onlineUsers = result.map((v) => {
          return {'name':v}
        })
      } else {
        this.news =this.news + '/n' + data.userId + ':' +data.data
      }

    },
    onclose() {
      console.log('Socket已关闭')
      this.news = 'Socket已关闭'
    },
    onerror() {
      this.$notify.error({
        title: '发生了错误',
        message: '此时可以尝试刷新页面',
        type: 'error',
        showClose: false
      })
      // 此时可以尝试刷新页面
    },
    pushMessage() {
      if (this.websock !== null && this.websock.readyState === 3) {
        this.websock.close()
        this.initWebSocket()
      } else if (this.websock.readyState === 1) {
        const msgType = this.tag >1 ? 2 : 1
        const msgContent = {
          userId:this.userId,
          tag:msgType,
          data:this.messageVal,
          targetUserId:this.targetUserId,
        }
        console.log('msgContent',JSON.stringify(msgContent))
        this.websock.send(JSON.stringify(msgContent))
        this.tag = 2;
      } else if (this.websock.readyState === 0) {
        this.news = '链接建立中，请稍后发送'
      }
      /*socketApi.pushMessage(this.messageWin, {message: this.messageVal}).then(res => {
        console.log('发送消息结果：', res)
      })*/
    },
    /**
     * 发送数据但连接未建立时进行处理等待重发
     * @param {any} message 需要发送的数据
     */
    /*connecting() {
      setTimeout(() => {
        if (this.Socket.readyState === 0) {
          this.connecting()
        } else {
          this.pushMessage()
        }
      }, 1000)
    },*/
    initDisabledVal() {
      this.initDisabled = false
      this.close()
    },
    close() {
      if (this.websock === '') {
        return
      }
      this.websock.close()// 关闭websocket
      this.websock.onclose = function (e) {
        console.log(e)// 监听关闭事件
      }
    }
  }
}
</script>
<style scoped>

</style>


