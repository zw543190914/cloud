<template>
    <div style="height: 100%">
        <el-menu
                class="el-menu-vertical-demo"
                @open="handleOpen" @close="handleClose"
                :collapse="isCollapse"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
        >
            <h4 align="center">后台管理系统</h4>
            <el-menu-item :index="item.path"
                          v-for="item in noChildren"
                          :key="item.path"
                          @click="clickMenu(item)"
            >
                <i :class="'el-icon-' + item.icon"></i>
                <span slot="title">{{item.label}}</span>
            </el-menu-item>

            <el-submenu :index="item.label" v-for="item in hasChildren" :key="item.path">
                <template slot="title">
                    <i :class="'el-icon-' + item.icon"></i>
                    <span slot="title">{{item.label}}</span>
                </template>
                <el-menu-item-group>
                    <el-menu-item :index="subItem.label"
                                  v-for="(subItem,subIndex) in item.children" :key="subIndex"
                                  @click="clickMenu(subItem)" >
                        <i :class="'el-icon-' + subItem.icon"></i>
                        <span slot="title">{{subItem.label}}</span>
                    </el-menu-item>
                </el-menu-item-group>
            </el-submenu>

        </el-menu>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                menu:[
                    {
                        path:'/',
                        name:'home',
                        label:'首页',
                        icon:'s-home',
                        url:'Home/Home'
                    },
                    {
                        path:'/mall',
                        name:'mall',
                        label:'商品管理',
                        icon:'video-play',
                        url:'MallManage/MallManage'
                    },
                    {
                        label:'即时通讯',
                        icon:'user',
                        children:[
                          {
                            path:'/ws/oneToMany',
                            name:'ws-oneToMany',
                            label:'聊天室',
                            icon:'setting',
                          },
                          {
                            path:'/ws/oneToOne',
                            name:'ws-oneToOne',
                            label:'单聊',
                            icon:'setting',
                          },
                        ]
                    },
                  {
                    label:'TC',
                    icon:'setting',
                    children:[
                      {
                        path:'/tc/tc-main',
                        name:'tc-main',
                        label:'TC',
                        icon:'setting',
                      },
                      {
                        path:'/tc/echarts',
                        name:'echarts-tc',
                        label:'TC统计',
                        icon:'setting',
                      },
                    ]
                  },
                    {
                        label:'图表',
                        icon:'location',
                        children:[
                            {
                                path:'/echarts/01',
                                name:'echarts01',
                                label:'折线图',
                                icon:'setting',
                            },
                            {
                                path:'/echarts/02',
                                name:'echarts02',
                                label:'柱状图',
                                icon:'setting',
                            },
                        ]
                    },
                ]
            };
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            clickMenu(item){
                this.$router.push({
                    name:item.name,
                    query:{
                        title:item.label
                    }
                })
            }

        },
        computed :{
            noChildren(){
                return this.menu.filter((item)=> !item.children);
            },
            hasChildren(){
                return this.menu.filter((item)=> item.children);
            },
            // 是否折叠
            isCollapse(){
                return this.$store.state.tab.isCollapse;
            }
        }
    }
</script>


<style scoped lang="scss">
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 700px;
        border: 0;
    }
</style>