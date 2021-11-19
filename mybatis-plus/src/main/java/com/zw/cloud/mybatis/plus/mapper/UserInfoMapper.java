package com.zw.cloud.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    void insertByMapper(UserInfo userInfo);

    void batchInsertByMapper(@Param("list") List<UserInfo> userList);

    void batchUpdate(@Param("list") List<UserInfo> userList);

    List<UserInfo> queryJsonData(@Param("userInfo") UserInfo userInfo);

    List<UserInfo> queryJsonDataLike(@Param("userInfo") UserInfo userInfo);

    @Select("select * from user_info")
    List<UserInfo> queryAllDataTest();

    @Select({"${sql}"})
    @ResultType(Map.class)
    Map<String, Object> queryUserData(@Param("sql") String sql);
}
