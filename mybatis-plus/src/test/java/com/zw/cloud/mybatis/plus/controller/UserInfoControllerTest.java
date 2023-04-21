package com.zw.cloud.mybatis.plus.controller;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoService;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserInfoControllerTest {

    @InjectMocks
    UserInfoController userInfoController;
    @Mock
    IUserInfoService userService;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
        // queryWrapper.eq(Objects.nonNull(user.getId()), UserInfo::getId, user.getId())
        TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), UserInfo.class);
    }

    @Test
    void pageQuery(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L).setAge(12);
        Assertions.assertDoesNotThrow(() -> userInfoController.pageQuery(userInfo));
    }

}
