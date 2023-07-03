package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user-info")
@Slf4j
public class UserInfoController {
    @Autowired
    private IUserInfoService userService;
    @Autowired
    private UserInfoMapper mapper;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Resource(name = "ioThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    @GetMapping("/testBatchInsertJdbc")
    //http://localhost:8082/user-info/testBatchInsertJdbc
    public void testBatchInsertJdbc() throws SQLException {
        userService.testBatchInsertJdbc(buildUserList());
    }

    @GetMapping("/testBatchInsertOneByOne")
    //http://localhost:8082/user-info/testBatchInsertOneByOne
    public void testBatchInsertOneByOne() {
        userService.testBatchInsertOneByOne(buildUserList());
    }

    @GetMapping("/testBatchInsertByMapper")
    //http://localhost:8082/user-info/testBatchInsertByMapper
    public void testBatchInsertByMapper() {
        userService.testBatchInsertByMapper(buildUserList());
    }

    @GetMapping("/testBatchInsertByMybatisPlus")
    //http://localhost:8082/user-info/testBatchInsertByMybatisPlus
    public void testBatchInsertByMybatisPlus() {
        long start = System.currentTimeMillis();
        userService.testBatchInsertByMybatisPlus(buildUserList());
        // 1895 3369 3252
        log.info("[testBatchInsertByMybatisPlus] use time {}", System.currentTimeMillis() - start);

    }

    /**
     * 异步中编程式事务测试
     */
    @GetMapping("/asynUpdate/{id}")
    //http://localhost:8082/user-info/asynUpdate/1
    public void asynUpdate(@PathVariable Long id) {
        userService.asynUpdate(id);
    }

    /**
     * 异步中编程式事务测试
     */
    @GetMapping("/asynTest/{rollback}")
    //http://localhost:8082/user-info/asynTest/true
    public void asynTest(@PathVariable boolean rollback) {
        ioThreadPoolTaskExecutor.submit(() -> {
            DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
            // ISOLATION_READ_COMMITTED
            defaultTransactionDefinition.setIsolationLevel(2);
            TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
            try {
                userService.testBatchInsertByMapper(buildData());
                log.info("[UserInfoController][asynTest]testBatchInsertByMapper end");
                if (rollback) {
                    throw new BizException("asynTest");
                }
                transactionManager.commit(status);
            } catch (BizException e) {
                transactionManager.rollback(status);
            }
        });

    }

    @GetMapping("/insertWithJson")
    @Transactional
    //http://localhost:8082/user-info/insertWithJson
    public void insertWithJson() {
        UserInfo user = new UserInfo();
        user.setId(1L);
        user.setName("test121");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());
        user.setOther(Lists.newArrayList(jsonObject));
        mapper.insert(user);
        System.out.println(JSON.toJSONString(user));
    }

    @PostMapping
    @Transactional
    //http://localhost:8082/user-info?name=test100000
    public void updateUserInfo(@RequestBody UserInfo user) {
        user.setAge(22);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());
        user.setOther(Lists.newArrayList(jsonObject));
        userService.updateById(user);
        System.out.println(JSON.toJSONString(user));
    }

    @GetMapping("/updateTest")
    //http://localhost:8082/user-info/updateTest
    @Transactional
    public void updateTest() throws InterruptedException {
        UserInfo userPlus = userService.getById(1588093790661382146L);
        userPlus.setAge(2);
        userPlus.setName("test01");

        userService.updateById(userPlus);
        TimeUnit.SECONDS.sleep(5);
        UserInfo userPlus2 = userService.getById(1588093790661382148L);
        userPlus2.setAge(3);

        userService.updateById(userPlus2);
        System.out.println(JSONObject.toJSONString(userPlus2));

    }

    @GetMapping("/updateTest2")
    //http://localhost:8082/user-info/updateTest2
    @Transactional
    public void updateTest2() throws InterruptedException {

        UserInfo userPlus2 = userService.getById(1588093790661382148L);
        userPlus2.setAge(3);
        userService.updateById(userPlus2);


        TimeUnit.SECONDS.sleep(5);
        UserInfo userPlus = userService.getById(1588093790661382146L);
        userPlus.setAge(2);
        userPlus.setName("test01");
        userService.updateById(userPlus);
        System.out.println(JSONObject.toJSONString(userPlus2));

    }

    @GetMapping("/batchInsertByMapper")
    //http://localhost:8082/user-info/batchInsertByMapper
    public void batchInsertByMapper() {

        try {
            userService.batchSaveOrUpdate(buildData());
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("名称重复");
        }

    }

    @GetMapping("/updateToNull")
    //http://localhost:8082/user-info/updateToNull?id=1588093790661382148
    public void updateToNull(@RequestParam Long id) {
        UserInfo user = new UserInfo();
        user.setName("updateToNull");
        LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
        // 或者 @TableField(updateStrategy = FieldStrategy.IGNORED)
        updateWrapper.set(UserInfo::getOther,null);
        updateWrapper.eq(UserInfo::getId,id);
        mapper.update(user,updateWrapper);
    }


    @GetMapping("/batchUpdate")
    //http://localhost:8082/user-info/batchUpdate
    public void batchUpdate() {
        userService.batchSaveOrUpdate(buildData());
    }

    @GetMapping("/testMvcc")
    //http://localhost:8082/user-info/testMvcc?id=1
    public void testMvcc(@RequestParam Long id) {
        userService.testMvcc(id);
    }

    @GetMapping("/testRepeatRead")
    //http://localhost:8082/user-info/testRepeatRead?id=1542758664088105011
    public void testRepeatRead(@RequestParam Long id) {
        userService.testRepeatRead(id);
    }

    @GetMapping("/testSerializable")
    //http://localhost:8082/user-info/testSerializable?id=794254126413250560
    public void testSerializable() {
        userService.testSerializable();
    }

    @GetMapping("/testPropagationRequiresNew/{id}")
    //http://localhost:8082/user-info/testPropagationRequiresNew/1588093790661382146
    public void testPropagationRequiresNew(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("testSerializable");
        userService.testPropagationRequiresNew(userInfo);
    }

    @GetMapping("/testPropagationRequires/{id}")
    //http://localhost:8082/user-info/testPropagationRequires/1588093790661382146
    public void testPropagationRequires(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("testPropagationRequires");
        userService.testPropagationRequires(userInfo);
    }

    @GetMapping("/testPropagationNested/{id}")
    //http://localhost:8082/user-info/testPropagationNested/1588093790661382146
    public void testPropagationNested(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("testPropagationNested");
        userService.testPropagationNested(userInfo);
    }

    @GetMapping("/testPropagationDefault/{id}")
    //http://localhost:8082/user-info/testPropagationDefault/1588093790661382146
    public void testPropagationDefault(@PathVariable Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("testPropagationDefault");
        userService.testPropagationDefault(userInfo);
    }

    @GetMapping("/queryWithLock/{id}")
    @Transactional
    //http://localhost:8082/user-info/queryWithLock/1
    public UserInfo queryWithLock(@PathVariable Long id) throws InterruptedException {
        UserInfo userInfo = mapper.queryByIdForUpdate(id);
        TimeUnit.SECONDS.sleep(15);
        return userInfo;
    }

    int i = 0;
    @GetMapping("testDuplicateUpdate")
    //http://localhost:8082/user-info/testDuplicateUpdate
    public void testDuplicateUpdate() {
        // i 线程不安全
        i = i+1;
        onDuplicateUpdate(String.valueOf(i));
        updateByName(String.valueOf(i - new Random().nextInt(20)));
    }

    @GetMapping("/updateByName/{name}")
    //http://localhost:8082/user-info/updateByName/zw4
    public void updateByName(@PathVariable String name) {
        userService.updateByName(name);
    }

    @GetMapping("/onDuplicateUpdate/{name}")
    //http://localhost:8082/user-info/onDuplicateUpdate/zw2
    public void onDuplicateUpdate(@PathVariable String name) {
        log.info("[UserInfoController][onDuplicateUpdate]name is {}",name);
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(23);
        userInfo.setOther(Lists.newArrayList(3));
        userInfo.setUpdateTime(LocalDateTime.now());
        userService.onDuplicateUpdate(Lists.newArrayList(userInfo));
    }

    @GetMapping("updateWrapper/{age}/{id}")
    //http://localhost:8082/user-info/updateWrapper/12/1656220227651997698
    public void updateWrapper(@PathVariable Integer age,@PathVariable Long id) {
        LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
         /*updateWrapper
                .set(UserInfo::getAge,age)
                .set(UserInfo::getOther,new HashMap<>())
                .in(UserInfo::getId,Lists.newArrayList(id));*/
        UserInfo userInfo = new UserInfo();
        userInfo.setOther(new HashMap<>());
        updateWrapper
                .in(UserInfo::getId,Lists.newArrayList(id));
        mapper.update(userInfo,updateWrapper);
    }

    @PostMapping("/query")
    //http://localhost:8082/user-info/query
    public Page<UserInfo> pageQuery(@RequestBody UserInfo user) {
        log.info("[UserInfoController][pageQuery]user is {}",JSON.toJSONString(user));
       /* QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .eq(Objects.nonNull(user.getId()),"id",user.getId())
                .eq(Objects.nonNull(user.getName()),"name",user.getName())
                .orderByDesc("id");*/
                //.last("limit 1");
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        /*queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName())
                .orderByDesc(UserInfo::getId);*/

        /**
         *  () or ()
         */
        //  WHERE deleted=0 AND ((id = ? AND name = ?) OR (age = ? AND name = ?)) ORDER BY id DESC LIMIT ?
        queryWrapper.and(wrapper -> wrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName()));

        LambdaQueryWrapper<UserInfo> or = queryWrapper.or();
        or.or(wrapper -> wrapper.eq(Objects.nonNull(user.getAge()), UserInfo::getAge, user.getAge())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName()));
        /**
         * and  or
         */
        // WHERE deleted=0 AND (id = ? AND name = ? OR age = ?) ORDER BY id DESC LIMIT ?
        /*queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName());
        queryWrapper.or().eq(Objects.nonNull(user.getAge()), UserInfo::getAge, user.getAge());
        queryWrapper.orderByDesc(UserInfo::getId);*/
        //LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery(user);
        Page<UserInfo> page = new Page<>(1,10);
        return userService.page(page, queryWrapper);
    }

    @GetMapping("/queryJsonData")
    //http://localhost:8082/user-info/queryJsonData?name=ee挺剂TF-630
    public List<UserInfo> queryJsonData(String name) {
        return userService.queryJsonData(name);
    }

    @GetMapping("/queryJsonDataLike")
    //http://localhost:8082/user-info/queryJsonDataLike?name=挺剂
    public List<UserInfo> queryJsonDataLike(String name) {
        return userService.queryJsonDataLike(name);
    }

    @GetMapping("/queryRangeDataTest")
    //http://localhost:8082/user-info/queryRangeDataTest?id=794253004214632449
    public List<UserInfo> queryRangeDataTest(@RequestParam Long id) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(UserInfo::getId,id);
        return mapper.selectList(queryWrapper);
    }

    @GetMapping("/queryAllDataTest/{pageNo}/{pageSize}")
    //http://localhost:8082/user-info/queryAllDataTest/1/10
    public IPage<UserInfo> queryAllDataTest(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return userService.queryAllDataTest(pageNo,pageSize);
    }

    private List<UserInfo> buildData() {
        UserInfo user = new UserInfo();
        //user.setId(1438686379807698945L);
        user.setName("zzzz");
        user.setAge(11);
        UserInfo user2 = new UserInfo();
        //user2.setId(1438688954489552898L);
        user2.setName("zzzz2");
        user2.setAge(11);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());
        user2.setOther(Lists.newArrayList(jsonObject));
        //userService.batchUpdateUserListByMapper(Lists.newArrayList(user,user2));
        //userService.batchUpdateUserList(Lists.newArrayList(user,user2));
        return Lists.newArrayList(user,user2);
    }

    private List<UserInfo> buildUserList(){
        List<UserInfo> userInfoList = new ArrayList<>(300000);
        Random random = new Random();
        for (int i = 0; i < 300000; i++) {
            UserInfo user = new UserInfo();
            user.setName("test" + i);
            user.setAge(random.nextInt(100));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickName",user.getName());
            jsonObject.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            user.setOther(Lists.newArrayList(jsonObject));
            user.setBir(LocalDate.now());
            userInfoList.add(user);
        }
        return userInfoList;
    }
}
