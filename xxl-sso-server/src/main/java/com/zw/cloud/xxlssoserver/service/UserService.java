package com.zw.cloud.xxlssoserver.service;


import com.zw.cloud.xxlssoserver.core.model.UserInfo;
import com.zw.cloud.xxlssoserver.core.result.ReturnT;

public interface UserService {

    public ReturnT<UserInfo> findUser(String username, String password);

}
