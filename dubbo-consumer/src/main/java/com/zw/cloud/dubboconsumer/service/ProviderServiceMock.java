package com.zw.cloud.dubboconsumer.service;

import com.zw.cloud.dubboproviderapi.service.IProviderService;

public class ProviderServiceMock implements IProviderService {
    @Override
    public String testProvider(String msg) {
        return "触发降级,返回MOCK";
    }
}
