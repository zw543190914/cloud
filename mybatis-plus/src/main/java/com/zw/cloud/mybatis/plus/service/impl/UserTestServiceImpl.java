package com.zw.cloud.mybatis.plus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserTestServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserTestService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertOneByOne() {
        long start = System.currentTimeMillis();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        for (int i = 0; i < 20000; i++) {
            UserInfo user2 = new UserInfo();
            //user2.setId(1438688954489552898L);
            user2.setName("test");
            user2.setAge(22);
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put("2222","name11");
            jsonObject.put("date",new Date());
            user2.setOther(Lists.newArrayList(jsonObject));*/
            mapper.insertByMapper(user2);
        }
        sqlSession.commit();
        // 1362 2793
        log.info("[testBatchInsertOneByOne] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMapper() {
        long start = System.currentTimeMillis();
        List<UserInfo> userInfoList = new ArrayList<>(50000);
        for (int i = 0; i < 20000; i++) {
            UserInfo user2 = new UserInfo();
            //user2.setId(1438688954489552898L);
            user2.setName("test");
            user2.setAge(22);
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put("2222","name11");
            jsonObject.put("date",new Date());
            user2.setOther(Lists.newArrayList(jsonObject));*/
            userInfoList.add(user2);
        }
        userInfoMapper.batchInsertByMapper(userInfoList);
        // 556 1129
        log.info("[testBatchInsertByMapper] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMybatisPlus() {
        long start = System.currentTimeMillis();
        List<UserInfo> userInfoList = new ArrayList<>(50000);
        for (int i = 0; i < 20000; i++) {
            UserInfo user2 = new UserInfo();
            //user2.setId(1438688954489552898L);
            user2.setName("test");
            user2.setAge(22);
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put("2222","name11");
            jsonObject.put("date",new Date());
            user2.setOther(Lists.newArrayList(jsonObject));*/
            userInfoList.add(user2);
        }
        saveBatch(userInfoList,10000);
        // 1895 3369
        log.info("[testBatchInsertByMybatisPlus] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    public void batchInsertByMapper(List<UserInfo> userInfoList) {
        userInfoMapper.batchInsertByMapper(userInfoList);
    }

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
