package com.zw.cloud.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertOneByOne(List<UserInfo> userInfoList) {
        long start = System.currentTimeMillis();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
            userInfoList.forEach(mapper::insertByMapper);
            sqlSession.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
        // 2000条数据 260
        // 10000条数据 810
        // 1362 2793 2862
        log.info("[testBatchInsertOneByOne] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMapper(List<UserInfo> userInfoList) {
        long start = System.currentTimeMillis();
        userInfoMapper.batchInsertByMapper(userInfoList);
        // 2000条数据 330
        // 10000条数据 1180
        // 556 1129 1115
        log.info("[testBatchInsertByMapper] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMybatisPlus(List<UserInfo> userInfoList) {
        long start = System.currentTimeMillis();
        saveBatch(userInfoList,10000);
        // 2000条数据 320
        // 10000条数据 1140
        // 1895 3369 3252
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
    @Transactional(rollbackFor = Exception.class)
    public void testMvcc(Long id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        //UserInfo userInfo = userInfoMapper.queryByIdForUpdate(id);
        Integer age = userInfo.getAge();
        log.info("[testMvcc] age is {}", age);
        try {
            Thread.sleep(2000);
            Integer age2 = userInfoMapper.selectById(id).getAge();
            log.info("[testMvcc] age2 is {}", age2);
        } catch (Exception e) {
            log.error("[testMvcc] error is ", e);
        }
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

    @Override
    public Map<String, Object> queryUserData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT sum(if(pt_quantity is null,0,pt_quantity)) ptQuantityTotal").append(",");
        List<UserInfo> userInfoList = new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            sql.append("SUM(if(JSON_CONTAINS(assistant,JSON_OBJECT('parentName','" + userInfo.getName() + "')) = 1,pt_quantity,0)) AS '" + userInfo.getName() + "' ,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));

        sql.append(" FROM base_product_record where org_code = '").append("orgCode")
                .append("' and workshop_id = ").append("1")
                .append(" and calc_day = '").append("2021-11-10").append("'");
        //.append("' GROUP BY device_id,device_name");
        /*SELECT device_id,device_name,
        SUM(if(pt_quantity is null,0,pt_quantity)) ptQuantityTotal,
                SUM(if(JSON_CONTAINS(assistant,JSON_OBJECT('parentName','防水类')) = 1,pt_quantity,0)) AS 'waterproof' ,
                SUM(if(JSON_CONTAINS(assistant,JSON_OBJECT('parentName','软剂类')) = 1,pt_quantity,0)) AS 'softener' ,
                SUM(if(JSON_CONTAINS(assistant,JSON_OBJECT('parentName','其它')) = 1,pt_quantity,0)) AS 'other' ,
                SUM(if(JSON_CONTAINS(assistant,JSON_OBJECT('parentName','助剂大')) = 1,pt_quantity,0)) AS '276'
        FROM base_product_record
        where org_code = 'devController' and workshop_id = 1
        and calc_day = '2021-11-10'
        GROUP BY device_id,device_name*/
        return userInfoMapper.queryUserData(sql.toString());
    }
}
