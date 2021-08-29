package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSONObject;

import com.zw.cloud.tools.base.ThreadContext;
import com.zw.cloud.tools.dao.UserDao;
import com.zw.cloud.tools.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/plus")
public class UserPlusController {

    @Autowired
    private UserDao userPlusMapper;
    @Autowired
    private ThreadContext threadContext;

    @PostMapping
    //http://localhost:9040/user/plus
    public void insert(){
        System.out.println(threadContext.getWorkIdThreadLocal().get());
        User userPlus = new User();
        userPlus.setAge((byte)11);
        userPlus.setName("001");
        int insert = userPlusMapper.insert(userPlus);
        System.out.println(insert);
        // 主键生成策略- 默认雪花算法
        System.out.println(userPlus.getId());// 1327531913719611394
    }

    @PutMapping
    //http://localhost:9040/user/plus
    public void update(){
        User userPlus = userPlusMapper.queryById(2L);
        userPlus.setAge((byte)2);
        userPlus.setName("test01");

        User userPlus2 = userPlusMapper.queryById(2L);
        userPlusMapper.update(userPlus);
        System.out.println(JSONObject.toJSONString(userPlus));
        userPlus2.setAge((byte)3);
        // 更新失败
        userPlusMapper.update(userPlus2);
        System.out.println(JSONObject.toJSONString(userPlus2));
    }

    @DeleteMapping
    //http://localhost:9040/user/plus
    public void delete(){
        userPlusMapper.deleteById(2L);
    }

    @GetMapping("/queryAll")
    //http://localhost:9040/user/plus/queryAll
    public List<User> queryAll(){
        return userPlusMapper.queryAll(null);
    }

    /**
     * 自定义查询
     * @return
     */
    /*@GetMapping("/queryByMap")
    //http://localhost:9040/user/plus/queryByMap
    public List<User> queryByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","test");
        return userPlusMapper.selectByMap(map);
    }*/

    /**
     * 分页
     * @return
     */
   /* @GetMapping("/query")
    //http://localhost:9040/user/plus/query
    public Page<UserPlus> query(){
        Page<User> page = new Page<>(2,2);
        return userPlusMapper.selectPage(page, null);
    }*/

   /* @GetMapping("/queryByWrapper")
    //http://localhost:9040/user/plus/query
    public Page<UserPlus> queryByWrapper(){
        Page<UserPlus> page = new Page<>(2,2);

        QueryWrapper<UserPlus> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .ge("name","test01")
                .inSql("id","select id from user_plus where id < 3");
        return userPlusMapper.selectPage(page, null);
    }*/
}
