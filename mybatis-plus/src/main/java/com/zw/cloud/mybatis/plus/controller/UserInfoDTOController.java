package com.zw.cloud.mybatis.plus.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.mybatis.plus.db.entity.PageInfo;
import com.zw.cloud.mybatis.plus.entity.dto.QueryDTO;
import com.zw.cloud.mybatis.plus.entity.dto.UserInfoDTO;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test")
public class UserInfoDTOController {
    @Autowired
    private IUserInfoDTOService userInfoService;

    @GetMapping
    //http://localhost:8080/test?name=test4
    public void saveOrUpdate(String name) {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().name(name).build();
        UserInfoDTO userInfoDTO1 = userInfoService.saveOrUpdate(userInfoDTO);
        System.out.println(JSON.toJSONString(userInfoDTO1));
    }

    @GetMapping("/{id}")
    //http://localhost:8080/test/1439464213926895617
    public void delete(@PathVariable("id") Long id) {
        userInfoService.delete(id);
    }

    @GetMapping("/query")
    //http://localhost:8080/test/query?name=test1
    public PageInfo<UserInfoDTO> pageQuery(String name) {
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setName(name);
        return userInfoService.pageQuery(queryDTO);
    }
}
