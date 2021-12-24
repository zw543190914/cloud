import VueRouter from 'vue-router'

import TcMain from '../pages/tc/TcMain'

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router =  new VueRouter({
    // mode:'hash',
    routes:[
        {
            name:'tcMain',
            path:'/tcMain',
            component:TcMain,
            meta:{title:'TC首页'}
        }
    ],
})

export default router


