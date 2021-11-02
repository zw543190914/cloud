package com.zw.cloud.mybatis.plus.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.UserInfo;

import java.util.List;

public interface IUserTestService extends IService<UserInfo> {

    void testBatchInsertOneByOne();

    void testBatchInsertByMapper();

    void testBatchInsertByMybatisPlus();

    void batchInsertByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserListByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserList(List<UserInfo> userInfoList);

    void batchSaveOrUpdate(List<UserInfo> userInfoList);

    List<UserInfo> queryJsonData(String name);

    List<UserInfo> queryJsonDataLike(String name);

    List<UserInfo> queryAllDataTest();
}
