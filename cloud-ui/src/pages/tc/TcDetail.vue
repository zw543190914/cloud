<template>
    <div>
      <el-descriptions title="详细信息">
        <el-descriptions-item label="id">{{tc.id}}</el-descriptions-item>
        <el-descriptions-item label="第一位">{{tc.one}}</el-descriptions-item>
        <el-descriptions-item label="第二位">{{tc.two}}</el-descriptions-item>
        <el-descriptions-item label="第三位">{{tc.three}}</el-descriptions-item>
        <el-descriptions-item label="第四位">{{tc.four}}</el-descriptions-item>
        <el-descriptions-item label="第五位">{{tc.five}}</el-descriptions-item>
        <el-descriptions-item label="蓝一">
          <el-tag size="small">{{tc.six}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="蓝二">
          <el-tag size="small">{{tc.seven}}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </div>
</template>

<script>

    import {tc_queryDetail} from '@/api/tc/tc'

    export default {
        name:'TcDetail',

        data() {
            return {
              tc:{},
            }
        },
      mounted() {
       /* this.$bus.$on('showTcDetail',(detailId)=>{
          this.queryTcDetail(detailId)
        })*/
        console.log(this.$route.query.id)
        this.queryTcDetail(this.$route.query.id)
      },

      methods: {
        queryTcDetail(id){
          tc_queryDetail(id).then(
              response=>{
                this.tc = response.data.data;
              },
              error => {
                alert(error.message)
              }
          )
        },

      }
    }
</script>

<style>

</style>