export default {


    mutations:{
        collapseMenu(state){
            state.isCollapse = !state.isCollapse
        }

    },
    state:{
        isCollapse:false
    },
}