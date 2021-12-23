<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-container>
                <el-main>
                    <el-table
                            :data="data.list"
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
                                fixed="right"
                                label="操作"
                                width="100">
                            <template slot-scope="scope">
                                <el-button @click="queryTcDetail(scope.row)" type="text" size="small">查看</el-button>
                                <el-button type="text" size="small">编辑</el-button>
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

                    <!-- 详情页对话框-->
                    <el-dialog
                        title="编辑"
                        :visible.sync="dialogVisible"
                        width="50%"
                        :before-close="handleClose">
                      <el-table
                          :data="data.detail"
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
                            fixed="right"
                            label="操作"
                            width="100">
                          <template slot-scope="scope">
                            <el-button @click="queryTcDetail(scope.row)" type="text" size="small">查看</el-button>
                            <el-button type="text" size="small">编辑</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="dialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
                      </span>
                    </el-dialog>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    import axios from 'axios'

    export default {
        name:'TcMain',
        beforeDestroy(){
            console.log('TcMain组件即将销毁')
        },
        data() {
            return {
              data:{
                list:[],
              },
              detail:{},
              queryDTO:{
                pageNo:1,
                pageSize:20,
              },
              dialogVisible: false
            }
        },
      mounted() {
        axios({
          method: 'post',
          url:`http://localhost:8080/vue/tc/pageQuery`,
          data: JSON.stringify(this.queryDTO)
        }).then(
          response=>{
            if (!response.data.success) {
              alert(response.data.errorMsg)
            }
            this.data = response.data.data;

          },
          error => {
            alert(error.errorMsg)
          }
        )
      },

      methods: {
        queryTcDetail(data){
          console.log(data)
          axios({
            method: 'get',
            url:`http://localhost:8080/vue/tc/queryById?id=${data.id}`,

          }).then(
              response=>{
                this.detail = response.data.data;
                console.log(result)
                this.dialogVisible = true

              },
              error => {
                alert(error.message)
              }
          )
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
          axios({
            method: 'post',
            url:`http://localhost:8080/vue/tc/pageQuery`,
            data: JSON.stringify(this.queryDTO)
          }).then(
              response=>{
                this.data = response.data.data;
              },
              error => {
                alert(error.message)
              }
          )
        },

        handleClose(done) {
          this.$confirm('确认关闭？')
              .then(_ => {
                done();
              })
              .catch(_ => {});
        },

      }
    }
</script>

<style>

</style>