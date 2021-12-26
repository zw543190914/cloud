<template>

        <el-container style="height: 100%">
            <el-aside width="auto">
                <CommonAside/>
            </el-aside>
            <el-container>
                <el-header>
                    <CommonHeader/>
                </el-header>

                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>

</template>

<script>
    import CommonAside from "./CommonAside";
    import CommonHeader from './CommonHeader'

    export default {
        name:'Main',
        components:{CommonAside,CommonHeader}
    }

</script>

<style lang="scss" scoped>
    .el-header{
        background: #333;
    }
    .el-main{
        padding-top:0 ;
    }

</style>