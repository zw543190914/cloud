<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-container>
                <el-main>
                    <el-table
                            :data="data.records"
                            border
                            style="width: 100%">
                        <el-table-column
                                fixed
                                prop="id"
                                label="日期"
                                width="150">
                        </el-table-column>
                        <el-table-column
                                prop="one"
                                label="第一位"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="two"
                                label="第二位"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="three"
                                label="第三位"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="four"
                                label="第四位"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="five"
                                label="第五位"
                                width="120">
                        </el-table-column>
                      <el-table-column
                          prop="six"
                          label="蓝一"
                          width="120">
                        <template slot-scope="scope">
                          <span style="color:blue"> {{scope.row.six}} </span>
                        </template>
                      </el-table-column>
                      <el-table-column
                          prop="seven"
                          label="蓝二"
                          width="120">
                        <template slot-scope="scope">
                          <span style="color:blue"> {{scope.row.seven}} </span>
                        </template>
                      </el-table-column>
                      <el-table-column
                          prop="updateTime"
                          label="更新时间"
                          width="120">
                        <template slot-scope="scope">
                          <span> {{ scope.row.updateTime | dateFormatFilter}} </span>
                        </template>
                      </el-table-column>
                      <el-table-column
                              fixed="right"
                              label="操作"
                              width="100">
                          <template slot-scope="scope">
                              <el-button @click="queryTcDetail(scope.row)" type="text" size="small">查看</el-button>
                              <el-button @click="editTcDetail(scope.row)" type="text" size="small">编辑</el-button>
                          </template>
                      </el-table-column>
                    </el-table>

                    <!-- 分页-->
                    <div class="block">
                      <el-pagination
                          @size-change="handleSizeChange"
                          @current-change="handleCurrentChange"
                          :current-page="this.data.pageNum"
                          :page-sizes="[10, 20, 40, 80, 200]"
                          :page-size="this.data.pageSize"
                          layout="total, sizes, prev, pager, next, jumper"
                          :total="this.data.total">
                      </el-pagination>
                    </div>

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    import {tc_pageQuery} from '@/api/tc/tc'

    export default {
        name:'TcMain',
        beforeDestroy(){
            console.log('TcMain组件即将销毁')
        },
        data() {
            return {
              data:{
                records:[],
              },
              queryDTO:{
                pageNo:1,
                pageSize:10,
              },
            }
        },
      mounted() {
        tc_pageQuery(this.queryDTO).then(
          response=>{
            //console.log('tc_pageQuery',response.data.data.records)
            if (!response.data.success) {
              alert(response.data.errorMsg)
            }
            this.data = response.data.data;
            console.log(this.data)

          },
          error => {
            alert(error.errorMsg)
          }
        )
      },

      methods: {
        queryTcDetail(data){
          this.$router.push({
            name:'tc-detail',
            query:{
              id:data.id
            }
          })
          //this.$bus.$emit('showTcDetail',data.id)
        },
        editTcDetail(data){
          this.$router.push({
            name:'tc-edit',
            query:{
              tc:data
            }
          })
        },
        handleSizeChange(size) {
          this.queryDTO.pageSize = size
          this.pageQuery()
        },
        handleCurrentChange(pageNum) {
          this.queryDTO.pageNo = pageNum
          this.pageQuery()
        },
        pageQuery(){
          tc_pageQuery(this.queryDTO).then(
              response=>{
                this.data = response.data.data;
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