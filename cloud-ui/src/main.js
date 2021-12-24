import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router'
import dayjs from "./utils/dayjs.min"

// 按需引入
import {  Aside,Button,
  Container,
  DatePicker,
  Dialog,
  Dropdown, DropdownMenu, DropdownItem,
  Header,
  MessageBox, Message,
  Menu,Submenu,MenuItem,MenuItemGroup,
  Main,
  Pagination,
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

Vue.use(Dialog)
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

Vue.use(Pagination)
Vue.use(RadioGroup);
Vue.use(Row);
Vue.use(Table);
Vue.use(TableColumn);

Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$message = Message

// 全局过滤器
Vue.filter('dateFormatFilter',function(dateTime,format='YYYY-MM-DD HH:mm:ss'){
  return dayjs(dateTime).format(format);
})

new Vue({
  render: h => h(App),
  router:router
}).$mount('#app')
