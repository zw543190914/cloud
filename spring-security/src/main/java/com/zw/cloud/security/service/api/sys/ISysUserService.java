package com.zw.cloud.security.service.api.sys;

import com.zw.cloud.security.entity.sys.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
public interface ISysUserService extends IService<SysUser> {

    boolean register(SysUser sysUser);

    String login(SysUser addedUser);

    String refresh(String oldToken);

    SysUser queryByUsername(String username);
}
