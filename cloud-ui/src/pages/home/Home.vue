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
                </el-card>
            </el-col>
            <el-col :span="16"></el-col>
        </el-row>

    </div>
</template>

<script>

    export default {
        data(){
            return {
                userImg:require("../../assets/img/nick.jpg")
            }
        }
    }

</script>