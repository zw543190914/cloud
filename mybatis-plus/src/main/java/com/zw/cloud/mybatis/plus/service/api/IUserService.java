package com.zw.cloud.mybatis.plus.service.api;

import com.zw.cloud.mybatis.plus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
public interface IUserService extends IService<User> {
    List<User> queryUserRoleIdList();
}
