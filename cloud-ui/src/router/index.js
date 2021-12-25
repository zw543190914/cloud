import VueRouter from 'vue-router'

import TcMain from '../pages/tc/TcMain'
import Main from '../pages/Main'
import Echarts from '../pages/echarts/Echarts'

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router =  new VueRouter({
    // mode:'hash',
    routes:[
        {
            name:'main',
            path:'/main',
            component:Main,
            meta:{title:'首页'}
        },
        {
            name:'tcMain',
            path:'/tcMain',
            component:TcMain,
            meta:{title:'TC首页'}
        },
        {
            name:'echarts',
            path:'/echarts',
            component:Echarts,
            meta:{title:'Echarts'}
        },

    ],
})

export default router


