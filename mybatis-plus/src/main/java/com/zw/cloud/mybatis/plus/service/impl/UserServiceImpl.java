package com.zw.cloud.mybatis.plus.service.impl;

import com.zw.cloud.mybatis.plus.entity.User;
import com.zw.cloud.mybatis.plus.mapper.UserMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> queryUserRoleIdList() {
        return baseMapper.queryUserRoleIdList();
    }
}
