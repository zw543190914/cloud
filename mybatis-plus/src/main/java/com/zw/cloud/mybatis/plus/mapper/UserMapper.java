package com.zw.cloud.mybatis.plus.mapper;

import com.zw.cloud.mybatis.plus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-03-26
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> queryUserRoleIdList();
}
