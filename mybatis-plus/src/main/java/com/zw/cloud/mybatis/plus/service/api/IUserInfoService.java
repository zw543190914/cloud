package com.zw.cloud.mybatis.plus.service.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.mybatis.plus.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IUserInfoService extends IService<UserInfo> {

    void testBatchInsertJdbc(List<UserInfo> userInfoList) throws SQLException;

    void testBatchInsertOneByOne(List<UserInfo> userInfoList);

    void testBatchInsertByMapper(List<UserInfo> userInfoList);

    void testBatchInsertByMybatisPlus(List<UserInfo> userInfoList);

    void batchInsertByMapper(List<UserInfo> userInfoList);

    /**
     * 异步中编程式事务测试
     */
    void asynUpdate(Long id);

    void batchUpdateUserListByMapper(List<UserInfo> userInfoList);

    void batchUpdateUserList(List<UserInfo> userInfoList);

    void batchSaveOrUpdate(List<UserInfo> userInfoList);

    void testMvcc(Long id);

    void testRepeatRead(Long id);

    void testSerializable();

    /**
     * 传播属性测试
     */
    void testPropagationRequiresNew(UserInfo userInfo);

    void testPropagationRequires(UserInfo userInfo);

    void testPropagationNested(UserInfo userInfo);

    void testPropagationDefault(UserInfo userInfo);

    List<UserInfo> queryJsonData(String name);

    List<UserInfo> queryJsonDataLike(String name);

    List<UserInfo> queryByOrgCode(String orgCode);

    IPage<UserInfo> queryAllDataTest(Integer pageNo, Integer pageSize);

    Map<String, Object> queryUserData();

    void onDuplicateUpdate(List<UserInfo> userInfoList);

    void updateByName(String name);
}
