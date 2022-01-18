<template>
    <div>
        <el-row class="home" :gutter="20">
            <el-col :span="8" style="margin-top: 20px">
                <el-card shadow="hover">
                    <div class="user">
                        <img :src="userImg" />
                        <div class="userInfo">
                            <p>Admin</p>
                            <p>超级管理员</p>
                        </div>
                    </div>
                    <div class="login-info">
                        <p>上次登录时间:</p>
                        <p>上次登录地址:</p>
                    </div>
                  <el-upload
                      class="upload-demo"
                      action="/vue/netty/file/uploadToLocal"
                      :on-preview="handlePreview"
                      :on-remove="handleRemove"
                      :before-remove="beforeRemove"
                      :file-list="fileList">
                    <el-button size="small" type="primary">点击上传</el-button>
                    <div slot="tip" class="el-upload__tip">文件不能超过1024MB</div>
                  </el-upload>
                    <el-input v-model="fileName" placeholder="请输入下载文件名称"></el-input>
                    <el-button type="primary" @click="fileDownload">下载</el-button>

                </el-card>
            </el-col>
            <el-col :span="16"></el-col>
        </el-row>

    </div>
</template>

<script>
  import {download} from '@/api/file/file'
    export default {
        data(){
            return {
                userImg:require("../../assets/img/nick.jpg"),
                fileName:'application-qa.yml',
                fileList:[]
            }
        },
      methods:{
        fileDownload(){
            if (this.fileName) {
              console.log(this.fileName)
              download(this.fileName)
            }
          },
        handleRemove(file, fileList) {
          console.log(file, fileList);
        },
        handlePreview(file) {
          console.log(file);
        },

        beforeRemove(file, fileList) {
          return this.$confirm(`确定移除 ${ file.name }？`);
        }
      }
    }

</script>