package com.zw.cloud.feignproviderapi.service;

import com.zw.cloud.common.utils.WebResult;

public interface IFeignProviderService {

    WebResult queryAllUser(Integer pageNo,Integer pageSize);
}
