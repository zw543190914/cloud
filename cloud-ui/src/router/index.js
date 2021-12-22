import VueRouter from 'vue-router'

import Main from '../pages/Main'

const router =  new VueRouter({
    mode:'hash',
    routes:[
        {
            name:'main',
            path:'/main',
            component:Main,
            meta:{title:'首页'}
        }
    ],
})

export default router
