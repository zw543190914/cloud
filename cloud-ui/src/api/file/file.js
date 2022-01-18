
import axios from '../../utils/request'
import { Message, Loading } from 'element-ui'

export function download(filename) {
    let downloadLoadingInstance = Loading.service({ text: "正在下载数据，请稍后", spinner: "el-icon-loading", background: "rgba(0, 0, 0, 0.7)", })
    return axios({
        method: 'get',
        url:`/netty/file/downloadFileFromLocal?fileName=${filename}`,
        headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8;' },
        responseType: 'blob',
    }).then(({data}) => {
        // const content = data
        // const blob = new Blob([content])
        // saveAs(blob, filename)
        console.log(data)
        download2(data,filename)
        downloadLoadingInstance.close();
    }).catch((r) => {
        console.error(r)
        Message.error('下载文件出现错误，请联系管理员！')
        downloadLoadingInstance.close();
    })
}
function download2 (data,filename)
{
    var ele = document.createElement('a');// 创建下载链接
    ele.download = filename;//设置下载的名称
    ele.style.display = 'none';// 隐藏的可下载链接
    // 字符内容转变成blob地址
    var blob = new Blob([data]);
    ele.href = URL.createObjectURL(blob);
    // 绑定点击时间
    document.body.appendChild(ele);
    ele.click();
    // 然后移除
    document.body.removeChild(ele);
}