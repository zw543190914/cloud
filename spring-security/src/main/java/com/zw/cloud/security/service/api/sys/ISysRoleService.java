package com.zw.cloud.security.service.api.sys;

import com.zw.cloud.security.entity.sys.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> queryRolesByUserId(Integer userId);
}
