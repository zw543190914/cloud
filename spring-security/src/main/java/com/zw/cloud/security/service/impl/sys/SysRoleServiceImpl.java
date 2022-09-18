package com.zw.cloud.security.service.impl.sys;

import com.zw.cloud.security.entity.sys.SysRole;
import com.zw.cloud.security.dao.sys.SysRoleMapper;
import com.zw.cloud.security.service.api.sys.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> queryRolesByUserId(Integer userId) {
        return baseMapper.queryRolesByUserId(userId);
    }
}
