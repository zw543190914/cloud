import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router'
//移动端rem适配
// import 'amfe-flexible/index'
// 引入 store
import store from './store'
import dayjs from "./utils/dayjs.min"

// 按需引入
import {
  Aside, Header,
  Container,
  Card,
  Col,
  Checkbox,
  CheckboxButton,
  CheckboxGroup,
  Button,
  DatePicker,
  Dialog,
  Dropdown, DropdownMenu, DropdownItem,
  Descriptions, DescriptionsItem,
  Form, FormItem,
  Input, InputNumber,

  MessageBox, Message,
  Menu, Submenu, MenuItem, MenuItemGroup,
  Main,
  Pagination,
  Radio,
  RadioGroup,
  RadioButton,
  Row,
  Select,
  Switch,
  Option,
  OptionGroup,
  Table, TableColumn,
  Tag, TimePicker, Upload,
}
  from 'element-ui';

Vue.config.productionTip = false
Vue.use(VueRouter)

Vue.use(Aside);
Vue.use(Container);
Vue.use(Header);

Vue.use(Button);
Vue.use(Dialog)
Vue.use(DatePicker);
Vue.use(Descriptions)
Vue.use(DescriptionsItem)

Vue.use(Card);
Vue.use(Col);

Vue.use(Dropdown);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(InputNumber);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Main);

Vue.use(Pagination)
Vue.use(Radio);
Vue.use(RadioGroup);
Vue.use(RadioButton);
Vue.use(Checkbox);
Vue.use(CheckboxButton);
Vue.use(CheckboxGroup);
Vue.use(Row);
Vue.use(Select);
Vue.use(Switch);
Vue.use(Option);
Vue.use(OptionGroup);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Tag);
Vue.use(TimePicker)
Vue.use(Upload)


Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$message = Message

// 全局过滤器
Vue.filter('dateFormatFilter',function(dateTime,format='YYYY-MM-DD HH:mm:ss'){
  return dayjs(dateTime).format(format);
})

new Vue({
  store,
  render: h => h(App),
  router:router,
  beforeCreate(){
    // 安装全局事件总线
    Vue.prototype.$bus = this
  },
}).$mount('#app')
