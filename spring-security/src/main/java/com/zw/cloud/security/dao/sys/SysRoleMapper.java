package com.zw.cloud.security.dao.sys;

import com.zw.cloud.security.entity.sys.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-09-18
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("SELECT * FROM `sys_role` where id in (SELECT role_id FROM `sys_user_role` where user_id = #{userId});")
    List<SysRole> queryRolesByUserId(@Param("userId") Integer userId);
}
