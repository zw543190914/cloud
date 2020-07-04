package com.zw.cloud.shiro.service.api;

import com.zw.cloud.shiro.entity.User;

import java.util.Map;

public interface IUserService {

    User queryUserByUserName(String username);

    User insert(User user);

    User update(User user);

    boolean duplicationCheck(String userName);

    Map<String,Object> queryPermission(String userName);

}
