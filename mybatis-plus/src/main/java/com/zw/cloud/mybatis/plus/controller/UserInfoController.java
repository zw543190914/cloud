package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/user-info")
@Slf4j
public class UserInfoController {
    @Autowired
    private IUserInfoService userService;
    @Autowired
    private UserInfoMapper mapper;

    @GetMapping("/testBatchInsertOneByOne")
    //http://localhost:8080/user-info/testBatchInsertOneByOne
    public void testBatchInsertOneByOne() {
        userService.testBatchInsertOneByOne(buildUserList());
    }

    @GetMapping("/testBatchInsertByMapper")
    //http://localhost:8080/user-info/testBatchInsertByMapper
    public void testBatchInsertByMapper() {
        userService.testBatchInsertByMapper(buildUserList());
    }

    @GetMapping("/testBatchInsertByMybatisPlus")
    //http://localhost:8080/user-info/testBatchInsertByMybatisPlus
    public void testBatchInsertByMybatisPlus() {
        long start = System.currentTimeMillis();
        userService.saveBatch(buildUserList(),2000);
        // 1895 3369 3252
        log.info("[testBatchInsertByMybatisPlus] use time {}", System.currentTimeMillis() - start);

    }

    @GetMapping("/insertWithJson")
    //http://localhost:8080/user-info/insertWithJson
    public void insertWithJson() {
        UserInfo user = new UserInfo();
        user.setName("test121");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());
        user.setOther(Lists.newArrayList(jsonObject));
        mapper.insertByMapper(user);
        System.out.println(JSON.toJSONString(user));
    }

    @PostMapping
    //http://localhost:8080/user-info?name=test100000
    public void saveOrUpdate(@RequestBody UserInfo user) {
        user.setAge(22);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());

        user.setOther(Lists.newArrayList(jsonObject));
        userService.saveOrUpdate(user);
        System.out.println(JSON.toJSONString(user));
    }

    @GetMapping("/batchInsertByMapper")
    //http://localhost:8080/user-info/batchInsertByMapper
    public void batchInsertByMapper() {

        try {
            userService.batchSaveOrUpdate(buildData());
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("名称重复");
        }

    }


    @GetMapping("/batchUpdate")
    //http://localhost:8080/user-info/batchUpdate
    public void batchUpdate() {
        userService.batchSaveOrUpdate(buildData());
    }

    @GetMapping("/testMvcc")
    //http://localhost:8080/user-info/testMvcc?id=1542758664088105011
    public void testMvcc(@RequestParam Long id) {
        userService.testMvcc(id);
    }

    @GetMapping("/query")
    //http://localhost:8080/user-info/query?name=test100000
    public Page<UserInfo> pageQuery(String name) {
        UserInfo user = new UserInfo();
        user.setName(name);
       /* QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name")
                .eq(Objects.nonNull(user.getId()),"id",user.getId())
                .eq(Objects.nonNull(user.getName()),"name",user.getName())
                .orderByDesc("id");*/
                //.last("limit 1");
       /* LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
                .eq(Objects.nonNull(user.getName()), UserInfo::getName, user.getName())
                .orderByDesc(UserInfo::getId);*/
        LambdaQueryWrapper<UserInfo> lambdaQuery = Wrappers.lambdaQuery(user);
        Page<UserInfo> page = new Page<>(1,10);
        return userService.page(page, lambdaQuery);
    }

    @GetMapping("/queryJsonData")
    //http://localhost:8080/user-info/queryJsonData?name=ee挺剂TF-630
    public List<UserInfo> queryJsonData(String name) {
        return userService.queryJsonData(name);
    }

    @GetMapping("/queryJsonDataLike")
    //http://localhost:8080/user-info/queryJsonDataLike?name=挺剂
    public List<UserInfo> queryJsonDataLike(String name) {
        return userService.queryJsonDataLike(name);
    }

    @GetMapping("/queryAllDataTest")
    //http://localhost:8080/user-info/queryAllDataTest
    public List<UserInfo> queryAllDataTest() {
        return userService.queryAllDataTest();
    }

    private List<UserInfo> buildData() {
        UserInfo user = new UserInfo();
        //user.setId(1438686379807698945L);
        user.setName("zzzz");
        user.setAge(11);
        UserInfo user2 = new UserInfo();
        //user2.setId(1438688954489552898L);
        user2.setName("zzzz");
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
        List<UserInfo> userInfoList = new ArrayList<>(30000);
        Random random = new Random();
        for (int i = 0; i < 2000; i++) {
            UserInfo user = new UserInfo();
            user.setName("test" + i);
            user.setAge(random.nextInt(100));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickName",user.getName());
            jsonObject.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            user.setOther(Lists.newArrayList(jsonObject));
            userInfoList.add(user);
        }
        return userInfoList;
    }
}
