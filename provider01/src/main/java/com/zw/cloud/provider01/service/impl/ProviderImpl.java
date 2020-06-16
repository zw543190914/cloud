package com.zw.cloud.provider01.service.impl;

import com.zw.cloud.provider01.service.IProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProviderImpl implements IProviderService {

    @Value("${server.port}")
    private int port;

    @Override
    public String detail(String msg) {
        return "this is provider01,port is " + port + ", msg is " + msg;
    }
}
