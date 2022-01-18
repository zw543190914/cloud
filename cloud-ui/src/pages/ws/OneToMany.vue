<template>
  <div title="在线沟通" v-model="chatVisible"
                 :width="580" style="margin-top: 10px">
    <div class="chat">
      <div  class="chat-message-body"  id ="chatform" >
        <span v-if="loading">
          <Icon type="ios-loading" size=18 class="spin-icon-load"></Icon>
        </span>
        <div  dis-hover v-for="(item,index) in news"
              :key="index" class="message-card">
          <div :class="item.isOneself === 1 ? 'message-row-right': 'message-row-left'">
            <img :src="item.avatar ? item.avatar : defualtAvatar[item.userId%3]"
                 height="40" width="35" >
            <div class="message-content">
              <div :style="item.isOneself === 1 ?'text-align:right;display: flex;flex-direction:row-reverse':''">
                <span class="message-time">{{item.userId}}</span>
                <span class="message-time">
                   {{item.createTime | dateFormatFilter}}</span>
              </div>
              <div class="message-body">
                {{item.content}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div>
        <el-input v-model = "messageVal" type = "textarea" :rows = "2"  style="width: 545px"  placeholder="主动一点，世界会更大！"/>
      </div>
      <div class="footer-btn">
<!--        <el-button class = "filter-item" size="mini" :disabled = "initDisabled" type = "primary" @click = "initWebSocket">初始化</el-button>-->

        <el-button size="mini" type = "success" @click = "pushMessage">发送</el-button>

      </div>
    </div>

  </div>
</template>

<script>

  const userId = new Date().getTime();
  export default {
    name: 'ws-oneToMany',
    data() {
      return {
        initDisabled: false,
        initVal: '',
        websock: '',
        userId: userId,//接入的ID
        messageVal: '',
        targetUserId:'',
        tag:1,// 消息类型 1, "第一次(或重连)初始化连接",2, "聊天消息",3, "消息签收",5, "拉取好友"
        onlineUsers: [],

        chatVisible:true,
        loading:false,
        defualtAvatar:[require('../../../public/nick01.jpeg'),require('../../../public/nick02.jpeg'),require('../../../public/nick03.jpeg')], // 后端没有返回头像默认头像，注意：需要用require请求方式才能动态访问本地文件
        news: [],

        heartTimeout: 60 * 1000, //60秒一次心跳
        heartBeat: null, //心跳
        lockReconnect: false, //是否真正建立连接
        reconnectId: null //断开 重连倒计时
      }
    },
    methods: {
      initWebSocket() {
        if (typeof (WebSocket) === 'undefined') {
          console.log('您的浏览器不支持会话')
          this.news.push({content:'您的浏览器不支持会话',createTime:new Date(),userId:this.userId})

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

          clearInterval(this.heartBeat)
          this.startHeart()
        }
      },
      startHeart(){
        const that = this
        that.heartBeat = setInterval(function() {
          //这里发送一个心跳
          if (that.websock.readyState === 1) {
            //如果连接正常
            console.log(that.userId,' send heartbeat')
            const msgContent = {
              userId:that.userId,
              tag:4,
              data:"heartbeat",

            }
            that.websock.send(JSON.stringify(msgContent));
          } else {
            //否则重连
            that.reconnect();
          }
        }, that.heartTimeout);
      },

      onopen() {
        this.news.push({content:'Socket 已打开',
          createTime:new Date(),
          userId:this.userId,
          isOneself:1})
      },
      onmessage(msg) {
        // 接收到的消息进入
        const data = JSON.parse(msg.data);
        console.log('onmessage',data)
        if (5 === data.tag) {
          this.onlineUsers = data.data
        } else {
          const isOneself = data.userId == this.userId ? 1 : 0
          console.log('isOneself',isOneself)
          this.news.push({content:data.data,
            createTime:data.createTime,
            userId:data.userId,
            isOneself:isOneself})
        }

      },
      onclose() {
        console.log('Socket已关闭')
        this.news.push({content:'Socket已关闭',
          createTime:new Date(),
          userId:this.userId,
          isOneself:1})

        clearInterval(this.heartBeat)
        clearTimeout(this.reconnectId);
      },
      onerror() {
        console.log(this.userId + ' onerror')
        clearInterval(this.heartBeat)
        clearTimeout(this.reconnectId);
        this.reconnect()
      },
      pushMessage() {
        if (this.websock !== null && this.websock.readyState === 3) {
          console.log(this.userId + ' pushMsg error,websock.readyState ',this.websock.readyState)
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
          this.messageVal = '';
        } else if (this.websock.readyState === 0) {
          this.news.push({content:'链接建立中，请稍后发送',
            createTime:new Date(),
            userId:this.userId,
            isOneself:1})
        }
        this.scrollToBottom()
        /*socketApi.pushMessage(this.messageWin, {message: this.messageVal}).then(res => {
          console.log('发送消息结果：', res)
        })*/
      },
      reconnect() {
        //重新连接
        const that = this;
        if (that.lockReconnect) {
          return;
        }
        console.log('reconnect',that.userId)
        that.lockReconnect = true;
        //没连接上会一直重连，设置延迟避免请求过多
        that.reconnectId && clearTimeout(that.reconnectId);
        that.reconnectId = setTimeout(function() {
          //新连接
          that.initWebSocket();
          that.lockReconnect = false;
        }, 1000);
      },

      initDisabledVal() {
        this.initDisabled = false
        this.close()
      },
      close() {
        if (this.websock === '') {
          return
        }
        clearInterval(this.heartBeat)
        clearTimeout(this.reconnectId)
        this.websock.close()// 关闭websocket
        this.websock.onclose = function (e) {
          console.log(e)// 监听关闭事件
        }
      },
      scrollToBottom(){ // 滚动到窗体底部
        this.$nextTick(() => {
          setTimeout(() => {
            const textarea = document.getElementById('chatform');
            textarea.scrollTop = textarea.scrollHeight;
          }, 13)
        })
      },
      beforeunloadFn (e) {
        this.close()
      },
    },
    mounted() {
      this.close()
      this.initWebSocket()
      window.addEventListener('beforeunload', e => this.beforeunloadFn(e))

    },
    beforeDestroy() {
      clearInterval(this.heartBeat)
      clearTimeout(this.reconnectId);
      window.removeEventListener('beforeunload', e => this.beforeunloadFn(e))
    }
}
</script>
<style scoped lang="less" >
  .message {
    height: 350px;
  }
  .ivu-card-body {
    padding:5px;
  }
  .ivu-modal-body{
    padding: 0px 16px 16px  16px;
  }
  .chat-message-body {
    background-color:#F8F8F6;
    width:545px;
    height: 350px;
    overflow: auto;
  }
  .message-card {
    margin:5px;
  }
  .message-row-left {
    display: flex;
    flex-direction:row;
  }
  .message-row-right {
    display: flex;
    flex-direction:row-reverse;
  }
  .message-content {
    margin:-5px 5px 5px 5px;
    display: flex;
    flex-direction:column;
  }
  .message-body {
    border:1px solid #D9DAD9;
    padding:5px;
    border-radius:3px;
    background-color:#FFF;
  }
  .message-time {
    margin:0 5px;
    font-size:5px;
    color:#D9DAD9;
  }

  .footer-btn {
    float:left;
    margin-bottom: 5px;
    margin-left: 480px;
  }
  .spin-icon-load {
    animation:ani-spin 1s linear infinite;
  }
  @keyframes ani-spin{
    form{transform: rotate(0deg);}
    50% {transform: rotate(180deg);}
    to  {transform: rotate(360deg);}
  }
</style>


