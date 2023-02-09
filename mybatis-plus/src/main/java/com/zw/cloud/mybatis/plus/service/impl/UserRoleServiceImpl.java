package com.zw.cloud.mybatis.plus.service.impl;

import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.mybatis.plus.entity.UserRole;
import com.zw.cloud.mybatis.plus.mapper.UserRoleMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void testPropagationRequiresNew(UserRole role) {
        save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void testPropagationRequires(UserRole role) {
        save(role);
        throw new BizException("exception");
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void testPropagationNested(UserRole role) {
        save(role);
        throw new BizException("exception");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testPropagationDefault(UserRole role) {
        save(role);
        throw new BizException("exception");
    }
}
