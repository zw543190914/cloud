package com.zw.cloud.mybatis.plus.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.cloud.mybatis.plus.db.MybatisPlusUtil;
import com.zw.cloud.mybatis.plus.db.base.BaseServiceImpl;
import com.zw.cloud.mybatis.plus.db.entity.PageInfo;
import com.zw.cloud.mybatis.plus.entity.UserInfo;
import com.zw.cloud.mybatis.plus.entity.dto.QueryDTO;
import com.zw.cloud.mybatis.plus.entity.dto.UserInfoDTO;
import com.zw.cloud.mybatis.plus.mapper.UserInfoMapper;
import com.zw.cloud.mybatis.plus.service.api.IUserInfoDTOService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoDTOServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfoDTO, UserInfo> implements IUserInfoDTOService {

    @Override
    public PageInfo<UserInfoDTO> pageQuery(QueryDTO queryTO) {
        IPage<UserInfo> page = new Page<>(queryTO.getPageNo(), queryTO.getPageSize());
        QueryWrapper<UserInfo> queryWrapper = MybatisPlusUtil.wrapperFrom(queryTO, UserInfo.class);
        IPage<UserInfo> userInfoIPage = mapper.selectPage(page, queryWrapper);
        System.out.println(JSON.toJSONString(userInfoIPage));
        return MybatisPlusUtil.toPageInfo(userInfoIPage, UserInfoDTO.class);
    }
}
