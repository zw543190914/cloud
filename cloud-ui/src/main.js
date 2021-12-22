import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router'


// 按需引入
import {  Aside,Button,
  Container,
  DatePicker,
  Dropdown, DropdownMenu, DropdownItem,
  Header,

  Menu,Submenu,MenuItem,MenuItemGroup,
  Main,
  RadioGroup,
  Row,
  Table, TableColumn,
  }
  from 'element-ui';

Vue.config.productionTip = false
Vue.use(VueRouter)

Vue.use(Aside);
Vue.use(Button);
Vue.use(Container);


Vue.use(DatePicker);

Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Header);

Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Main);

Vue.use(RadioGroup);
Vue.use(Row);
Vue.use(Table);
Vue.use(TableColumn);




new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
