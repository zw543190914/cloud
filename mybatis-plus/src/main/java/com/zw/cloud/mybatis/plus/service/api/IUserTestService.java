package com.zw.cloud.mybatis.plus.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.UserInfo;

import java.util.List;

public interface IUserTestService extends IService<UserInfo> {

    void batchUpdateUserListByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserList(List<UserInfo> userInfoList);
}
