import Axios from 'axios'
let axios = Axios.create({
  baseURL: 'http://localhost:8087/vue',
  timeout: 1000 * 15,
});

axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';//配置请求头信息。
axios.defaults.headers.put['Content-Type'] = 'application/json;charset=UTF-8';//配置请求头信息。

export default axios
