import VueRouter from 'vue-router'

import Main from '../pages/Main'



// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router =  new VueRouter({
    // mode:'hash',
    routes:[
        {
            path:'/',
            component:Main,
            children:[
                {
                    name:'home',
                    path:'/',
                    meta:{title:'首页'},
                    component:()=>import('../pages/home/Home')
                }
            ]
        },
        {
            path:'/user',
            component:Main,
            meta:{title:'ws'},
            children:[
                {
                    name:'ws-oneToMany',
                    path:'/ws/oneToMany',
                    meta:{title:'聊天室'},
                    component:()=>import('../pages/ws/OneToMany')
                }
            ]
        },
        {
            path:'/tc',
            name:'tc',
            meta:{title:'TC'},
            component:Main,
            children:[
                {
                    path:'/tc/main',
                    name:'tc-main',
                    meta:{title:'TC'},
                    component:()=>import('../pages/tc/TcMain')
                },
                {
                    path:'/tc/detail',
                    name:'tc-detail',
                    meta:{title:'TC详情'},
                    component:()=>import('../pages/tc/TcDetail'),
                },
                {
                    path:'/tc/edit',
                    name:'tc-edit',
                    meta:{title:'新增/编辑'},
                    component:()=>import('../pages/tc/TcEdit'),
                },
                {
                    path:'/tc/echarts',
                    name:'echarts-tc',
                    meta:{title:'TC统计图'},
                    component:()=>import('../pages/echarts/EchartsTc')
                },
            ]
        },
        {
            name:'echarts',
            path:'/echarts',
            component:Main,
            children:[
                {
                    path:'/echarts/01',
                    name:'echarts01',
                    meta:{title:'折线图'},
                    component:()=>import('../pages/echarts/Echarts')
                },
                {
                    path:'/echarts/02',
                    name:'echarts02',
                    meta:{title:'统计图'},
                    component:()=>import('../pages/echarts/EchartsTc')
                }
            ]
        },

    ],
})

router.afterEach((to,from)=>{
    document.title = to.meta.title
})

export default router


