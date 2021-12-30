package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-08-29 21:02:43
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 不存在插入，存在更新
     * @param name
     */
    @GetMapping("/insertOrUpdate")
    //http://localhost:9040/user/insertOrUpdate?name=zw&age=18&desc=11
    public void insertOrUpdate(String name,int age,String desc){
        User userPlus = new User();
        userPlus.setAge((byte)age);
        userPlus.setName(name);
        userPlus.setDescription(desc);
        userService.insertOrUpdate(userPlus);
    }

    @PostMapping
    //http://localhost:9040/user
    public void insert(){
        User userPlus = new User();
        userPlus.setAge((byte)11);
        userPlus.setName("001");
        User insert = userService.insert(userPlus);
        System.out.println(insert);
        // 主键生成策略- 默认雪花算法
        System.out.println(userPlus.getId());// 1327531913719611394
    }

    @PutMapping
    //http://localhost:9040/user
    public void update(){
        User userPlus = userService.queryById(2L);
        userPlus.setAge((byte)2);
        userPlus.setName("test01");

        User userPlus2 = userService.queryById(2L);
        userService.update(userPlus);
        System.out.println(JSONObject.toJSONString(userPlus));
        userPlus2.setAge((byte)3);
        // 更新失败
        userService.update(userPlus2);
        System.out.println(JSONObject.toJSONString(userPlus2));
    }

    @DeleteMapping
    //http://localhost:9040/user
    public void delete(){
        userService.deleteById(2L);
    }

    @GetMapping("/queryAll")
    //http://localhost:9040/user/queryAll
    public List<User> queryAll(){
        return userService.queryAll();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }

    @GetMapping("queryBySql")
    //http://localhost:9040/user/queryBySql
    public Map<String,Object> queryBySql(Long id) {
        return this.userService.queryBySql(id);
    }

}