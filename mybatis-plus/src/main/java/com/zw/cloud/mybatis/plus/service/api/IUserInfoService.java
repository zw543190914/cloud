package com.zw.cloud.mybatis.plus.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface IUserInfoService extends IService<UserInfo> {

    void testBatchInsertOneByOne(List<UserInfo> userInfoList);

    void testBatchInsertByMapper(List<UserInfo> userInfoList);

    void testBatchInsertByMybatisPlus(List<UserInfo> userInfoList);

    void batchInsertByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserListByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserList(List<UserInfo> userInfoList);

    void batchSaveOrUpdate(List<UserInfo> userInfoList);

    void testMvcc(Long id);

    void testRepeatRead(Long id);

    void testSerializable();

    List<UserInfo> queryJsonData(String name);

    List<UserInfo> queryJsonDataLike(String name);

    List<UserInfo> queryAllDataTest();

    Map<String, Object> queryUserData();
}
