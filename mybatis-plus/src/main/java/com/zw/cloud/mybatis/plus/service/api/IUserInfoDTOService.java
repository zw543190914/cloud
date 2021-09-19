package com.zw.cloud.mybatis.plus.service.api;


import com.zw.cloud.mybatis.plus.db.entity.PageInfo;
import com.zw.cloud.mybatis.plus.entity.dto.QueryDTO;
import com.zw.cloud.mybatis.plus.entity.dto.UserInfoDTO;

public interface IUserInfoDTOService {

    UserInfoDTO saveOrUpdate(UserInfoDTO userInfoDTO);

    PageInfo<UserInfoDTO> pageQuery(QueryDTO queryDTO);

    void delete(Long id);
}
