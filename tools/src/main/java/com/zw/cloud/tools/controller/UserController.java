/*
package com.zw.cloud.tools.controller;


import com.github.pagehelper.PageInfo;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.tools.modle.vo.ParamVO;
import com.zw.cloud.tools.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tools/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    //http://localhost:9040/tools/user
    public void insert(User user, MultipartFile file)throws Exception{
        userService.insert(user,file);
    }

    @PutMapping
    //http://localhost:9040/tools/user
    public void update(User user, MultipartFile file)throws Exception{
        userService.update(user,file);
    }

    @PostMapping("/query")
    //http://localhost:9040/tools/user/query
    public PageInfo<User> query(@RequestBody ParamVO paramVO)throws Exception{
        return userService.query(paramVO);
    }
}
*/
