package com.zw.cloud.feignprovider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zw.cloud.common.utils.MyPermissionCheckException;
import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.db.entity.UserExample;
import com.zw.cloud.feignproviderapi.service.IFeignProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeignProviderImpl implements IFeignProviderService {
    @Autowired
    private UserMapper mapper;
    @Value("${server.port}")
    private int port;

    private Logger logger = LoggerFactory.getLogger(FeignProviderImpl.class);
    @Override
    public WebResult queryAllUser(Integer pageNo, Integer pageSize) {
        logger.info("[FeignProviderImpl][queryAllUser] server.port is {}",port);
        PageHelper.startPage(pageNo,pageSize);
        List<User> users = mapper.selectByExampleWithBLOBs(new UserExample());
        //throw new MyPermissionCheckException("wwww");
        return WebResult.success().withData(new PageInfo<>(users));
    }
}
