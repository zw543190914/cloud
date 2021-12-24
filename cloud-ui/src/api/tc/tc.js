import axios from '../../utils/request'

export function tc_pageQuery(param) {
    return axios({
        method: 'post',
        url:`/tc/pageQuery`,
        data: JSON.stringify(param)
    })
}

export function tc_queryDetail(param){
    return axios({
        method: 'get',
        url:`/tc/queryById?id=${param}`,
    })
}

