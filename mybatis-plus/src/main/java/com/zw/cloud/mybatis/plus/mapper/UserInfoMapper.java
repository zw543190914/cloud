package com.zw.cloud.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    void batchUpdate(@Param("list") List<UserInfo> userList);

    List<UserInfo> queryJsonData(@Param("name") String name);

    List<UserInfo> queryJsonDataLike(@Param("name") String name);
}
