package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/user-test")
public class UserTestController {
    @Autowired
    private IUserTestService userService;
    @Autowired
    private UserInfoMapper mapper;

    @GetMapping("/testBatchInsertOneByOne")
    //http://localhost:8080/user-test/testBatchInsertOneByOne
    public void testBatchInsertOneByOne() {
        userService.testBatchInsertOneByOne(buildUserList());
    }

    @GetMapping("/testBatchInsertByMapper")
    //http://localhost:8080/user-test/testBatchInsertByMapper
    public void testBatchInsertByMapper() {
        userService.testBatchInsertByMapper(buildUserList());
    }

    @GetMapping("/testBatchInsertByMybatisPlus")
    //http://localhost:8080/user-test/testBatchInsertByMybatisPlus
    public void testBatchInsertByMybatisPlus() {
        userService.testBatchInsertByMybatisPlus(buildUserList());
    }

    @GetMapping("/insertWithJson")
    //http://localhost:8080/user-test/insertWithJson
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

    @GetMapping
    //http://localhost:8080/user-test?name=test2
    public void saveOrUpdate(String name) {
        UserInfo user = new UserInfo();
        user.setName(name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2222","name11");
        jsonObject.put("date",new Date());
        user.setOther(Lists.newArrayList(jsonObject));
        userService.saveOrUpdate(user);
        System.out.println(JSON.toJSONString(user));
    }

    @GetMapping("/batchInsertByMapper")
    //http://localhost:8080/user-test/batchInsertByMapper
    public void batchInsertByMapper() {

        userService.batchSaveOrUpdate(buildData());

    }


    @GetMapping("/batchUpdate")
    //http://localhost:8080/user-test/batchUpdate
    public void batchUpdate() {
        userService.batchSaveOrUpdate(buildData());
    }

    @GetMapping("/query")
    //http://localhost:8080/user-test/query?name=fd2
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
    //http://localhost:8080/user-test/queryJsonData?name=ee挺剂TF-630
    public List<UserInfo> queryJsonData(String name) {
        return userService.queryJsonData(name);
    }

    @GetMapping("/queryJsonDataLike")
    //http://localhost:8080/user-test/queryJsonDataLike?name=挺剂
    public List<UserInfo> queryJsonDataLike(String name) {
        return userService.queryJsonDataLike(name);
    }

    @GetMapping("/queryAllDataTest")
    //http://localhost:8080/user-test/queryAllDataTest
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
        user2.setName("r挺剂TF-630ff");
        user2.setAge(22);
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
        for (int i = 0; i < 2000; i++) {
            UserInfo user2 = new UserInfo();
            //user2.setId(1438688954489552898L);
            user2.setName("test");
            user2.setAge(22);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("2222","name11");
            jsonObject.put("date",new Date());
            user2.setOther(Lists.newArrayList(jsonObject));
            userInfoList.add(user2);
        }
        return userInfoList;
    }
}
