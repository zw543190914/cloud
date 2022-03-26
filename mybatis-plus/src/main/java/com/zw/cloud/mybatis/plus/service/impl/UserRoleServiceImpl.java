package com.zw.cloud.mybatis.plus.service.impl;

import com.zw.cloud.mybatis.plus.entity.UserRole;
import com.zw.cloud.mybatis.plus.mapper.UserRoleMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表角色关联表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
