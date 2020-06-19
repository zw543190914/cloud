package com.zw.cloud.user.service.impl;

import com.zw.cloud.user.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements IUserService {

    @Value("${server.port}")
    private int port;

    @Override
    public String detail(String msg) {
        return "this is provider01,port is " + port + ", msg is " + msg;
    }
}
