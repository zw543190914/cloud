package com.zw.cloud.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserTestService;
import org.springframework.stereotype.Service;

@Service
public class UserTestServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserTestService {

}
