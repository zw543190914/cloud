package com.zw.cloud.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTestServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserTestService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void batchUpdateUserListByMapper(List<UserInfo> userInfoList) {
        userInfoMapper.batchUpdate(userInfoList);
    }

    @Override
    public void batchUpdateUserList(List<UserInfo> userInfoList) {
        updateBatchById(userInfoList);
    }

    @Override
    public void batchSaveOrUpdate(List<UserInfo> userInfoList) {
        saveOrUpdateBatch(userInfoList);
    }

    @Override
    public List<UserInfo> queryJsonData(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return userInfoMapper.queryJsonData(userInfo);
    }

    @Override
    public List<UserInfo> queryJsonDataLike(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return userInfoMapper.queryJsonDataLike(userInfo);
    }

    @Override
    public List<UserInfo> queryAllDataTest(){
        return userInfoMapper.queryAllDataTest();
    }
}
