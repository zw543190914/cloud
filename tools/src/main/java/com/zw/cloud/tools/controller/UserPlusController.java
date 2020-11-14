package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.cloud.dao.UserPlusMapper;
import com.zw.cloud.entity.UserPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/plus")
public class UserPlusController {

    @Autowired
    private UserPlusMapper userPlusMapper;

    @GetMapping("/insert")
    //http://localhost:9040/user/plus/insert
    public void insert(){
        UserPlus userPlus = new UserPlus();
        userPlus.setAge(11);
        userPlus.setName("001");
        int insert = userPlusMapper.insert(userPlus);
        System.out.println(insert);
        // 主键生成策略- 默认雪花算法
        System.out.println(userPlus.getId());// 1327531913719611394
    }

    @GetMapping("/update")
    //http://localhost:9040/user/plus/update
    public void update(){
        UserPlus userPlus = userPlusMapper.selectById(2L);
        userPlus.setAge(2);
        userPlus.setName("test01");

        UserPlus userPlus2 = userPlusMapper.selectById(2L);
        userPlusMapper.updateById(userPlus);
        System.out.println(JSONObject.toJSONString(userPlus));
        userPlus2.setAge(3);
        // 更新失败
        userPlusMapper.updateById(userPlus2);
        System.out.println(JSONObject.toJSONString(userPlus2));
    }

    @GetMapping("/delete")
    //http://localhost:9040/user/plus/delete
    public void delete(){
        userPlusMapper.deleteById(3L);
    }

    @GetMapping("/queryAll")
    //http://localhost:9040/user/plus/queryAll
    public List<UserPlus> queryAll(){
        return userPlusMapper.selectList(null);
    }

    /**
     * 自定义查询
     * @return
     */
    @GetMapping("/queryByMap")
    //http://localhost:9040/user/plus/queryByMap
    public List<UserPlus> queryByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","test");
        return userPlusMapper.selectByMap(map);
    }

    /**
     * 分页
     * @return
     */
    @GetMapping("/query")
    //http://localhost:9040/user/plus/query
    public Page<UserPlus> query(){
        Page<UserPlus> page = new Page<>(2,2);
        return userPlusMapper.selectPage(page, null);
    }

    @GetMapping("/queryByWrapper")
    //http://localhost:9040/user/plus/query
    public Page<UserPlus> queryByWrapper(){
        Page<UserPlus> page = new Page<>(2,2);

        QueryWrapper<UserPlus> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .ge("name","test01")
                .inSql("id","select id from user_plus where id < 3");
        return userPlusMapper.selectPage(page, null);
    }
}
