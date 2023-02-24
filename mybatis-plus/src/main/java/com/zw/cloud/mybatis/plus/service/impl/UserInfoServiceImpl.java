package com.zw.cloud.mybatis.plus.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.entity.UserRole;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import com.zw.cloud.mybatis.plus.service.api.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertJdbc(List<UserInfo> userInfoList) throws SQLException {
        long start = System.currentTimeMillis();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            //设置手动提交
            connection.setAutoCommit(false);
            //预编译sql对象,只编译一回
            PreparedStatement ps = connection.prepareStatement(
                    "insert into user_info_0 (name, age, bir, other,update_time) values(?,?,?,?,?)");
            for (UserInfo userInfo : userInfoList) {
                ps.setString(1, userInfo.getName());
                ps.setInt(2,userInfo.getAge());
                // java.sql.Date
                ps.setDate(3, java.sql.Date.valueOf(userInfo.getBir()));
                ps.setString(4,JSON.toJSONString(userInfo.getOther()));
                ps.setTimestamp(5,java.sql.Timestamp.valueOf(LocalDateTime.now()));
                //添加到批次
                ps.addBatch();
            }
            //提交批处理
            ps.executeBatch();
            connection.commit();
        } catch (SQLException sqlException) {
            if (Objects.nonNull(connection)) {
                connection.rollback();
            }
            throw sqlException;
        } finally {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        }
        // 10000 条数据 431
        // 30万 13796 20075 13521
        log.info("[testBatchInsertJdbc] use time {}", System.currentTimeMillis() - start);
    }

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
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
        // 2000条数据 260
        // 10000条数据 810  接入 sharding-jdbc 6868 2518
        // 1362 2793 2862
        // 30万 31076 24161 19086
        log.info("[testBatchInsertOneByOne] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMapper(List<UserInfo> userInfoList) {
        long start = System.currentTimeMillis();
        baseMapper.batchInsertByMapper(userInfoList);
        // 2000条数据 330
        // 10000条数据 1180  接入 sharding-jdbc 1953 1596
        // 556 1129 1115
        // 30万 74593 41711
        log.info("[testBatchInsertByMapper] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testBatchInsertByMybatisPlus(List<UserInfo> userInfoList) {
        long start = System.currentTimeMillis();
        saveBatch(userInfoList,userInfoList.size());
        // 2000条数据 320
        // 10000条数据 1140 接入 sharding-jdbc 3824 6966
        // 1895 3369 3252
        // 30万 80725 46066 34753
        log.info("[testBatchInsertByMybatisPlus] use time {}", System.currentTimeMillis() - start);
    }

    @Override
    public void batchInsertByMapper(List<UserInfo> userInfoList) {
        baseMapper.batchInsertByMapper(userInfoList);
    }

    @Override
    public void batchUpdateUserListByMapper(List<UserInfo> userInfoList) {
        baseMapper.batchUpdate(userInfoList);
    }

    @Override
    public void batchUpdateUserList(List<UserInfo> userInfoList) {
        updateBatchById(userInfoList);
    }

    @Override
    public void batchSaveOrUpdate(List<UserInfo> userInfoList) {
        saveOrUpdateBatch(userInfoList);
    }

    /**
     * 不可重复读的重点是修改：
     * 同样的条件，你读取过的数据，再次读取出来发现值不一样了
     * 幻读的重点在于新增或者删除：
     * 同样的条件，第 1 次和第 2 次读出来的记录数不一样
     */
    /**
     * 无论是否有 @Transactional
     * 数据存在，其他事务此时更新,再次读取任然是当前值
     * 数据不存在，其他事务插入新数据并提交，此时更新或者删除 上一个事务提交的数据，可以更新成新值或者删除新数据  没有完全禁止幻读
     * 解决：@Transactional 并且使用锁 select for update
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testMvcc(Long id) {
        UserInfo userInfo = baseMapper.selectById(id);
        //UserInfo userInfo = baseMapper.queryByIdForUpdate(id);
        // 此时数据为空
        log.info("[testMvcc] userInfo is {}", JSON.toJSONString(userInfo));

        try {
            Thread.sleep(3000);
            UserInfo updateUserInfo = new UserInfo();
            updateUserInfo.setId(id);
            updateUserInfo.setAge(66);
            // 此时其他事务提交，该id 有数据，可以修改成功
            int i = baseMapper.updateById(updateUserInfo);
            log.info("[testMvcc] update is {}", i);
        } catch (Exception e) {
            log.error("[testMvcc] error is ", e);
        }
    }

    /**
     * 重复读  同样的条件，你读取过的数据，再次读取出来发现值不一样了
     * 该事务过程中，其他事务修改了该数据 age
     * 没有 @Transactional 两次读取数据 age 不一致
     * 加上 @Transactional 两次读取 age 一样
     */
    @Override
    @Transactional
    public void testRepeatRead(Long id) {
        UserInfo userInfo = baseMapper.selectById(id);
        log.info("[testRepeatRead] userInfo is {}", JSON.toJSONString(userInfo));
        try {
            Thread.sleep(3000);
            UserInfo userInfo2 = baseMapper.selectById(id);
            log.info("[testRepeatRead] userInfo2 is {}", JSON.toJSONString(userInfo2));
        } catch (Exception e) {
            log.error("[testRepeatRead] error is ", e);
        }
    }

    /**
     * 幻读  同样的条件，第 1 次和第 2 次读出来的记录数不一样
     * 该事务过程中，其他事务 新增数据
     * 没有 @Transactional 两次读取数据 count 不一致
     * 加上 @Transactional 两次读取 count 一样
     */
    @Override
    @Transactional
    public void testSerializable() {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(UserInfo::getId, Lists.newArrayList(1L,2L));
        Integer count = baseMapper.selectCount(queryWrapper);
        log.info("[testSerializable] count is {}",count);
        try {
            Thread.sleep(3000);
            Integer count2 = baseMapper.selectCount(queryWrapper);
            log.info("[testSerializable] count2 is {}",count2);
        } catch (Exception e) {
            log.error("[testSerializable] error is ", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testPropagationRequiresNew(UserInfo userInfo) {
        baseMapper.updateById(userInfo);
        UserRole userRole = new UserRole();
        userRole.setUserId(0);
        userRole.setRoleId(0);
        userRoleService.testPropagationRequiresNew(userRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testPropagationRequires(UserInfo userInfo) {
        baseMapper.updateById(userInfo);
        UserRole userRole = new UserRole();
        userRole.setUserId(0);
        userRole.setRoleId(0);
        // 使用 REQUIRED 异常被捕获，userInfo 任然没有更新
        // Transaction rolled back because it has been marked as rollback-only -- 应该使用 NESTED
        try {
            userRoleService.testPropagationRequires(userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 1 里面抛异常 如果被捕获不能影响外层执行
     * 2 外面抛出异常，里面都要回滚
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testPropagationNested(UserInfo userInfo) {
        baseMapper.updateById(userInfo);
        UserRole userRole = new UserRole();
        userRole.setUserId(0);
        userRole.setRoleId(0);
        // 使用 NESTED,子事务抛出异常，区别于REQUIRED userInfo 可以正常 更新
        try {
            userRoleService.testPropagationNested(userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("sssss");
    }

    /**
     * propagation = Propagation.REQUIRES_NEW 或者 默认
     * Transaction rolled back because it has been marked as rollback-only
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testPropagationDefault(UserInfo userInfo) {
        baseMapper.updateById(userInfo);
        UserRole userRole = new UserRole();
        userRole.setUserId(0);
        userRole.setRoleId(0);
        // Transaction rolled back because it has been marked as rollback-only
        try {
            userRoleService.testPropagationDefault(userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserInfo> queryJsonData(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return baseMapper.queryJsonData(userInfo);
    }

    @Override
    public List<UserInfo> queryJsonDataLike(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        return baseMapper.queryJsonDataLike(userInfo);
    }

    @Override
    public IPage<UserInfo> queryAllDataTest(Integer pageNo,Integer pageSize){
        IPage<UserInfo> page = new Page<>(pageNo,pageSize);
        return baseMapper.queryAllDataTest(page);
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
        return baseMapper.queryUserData(sql.toString());
    }
}
