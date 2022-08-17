package com.zw.cloud.feignproviderapi.client;

import com.zw.cloud.feignproviderapi.service.IFeignProviderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("feign-provider")
public interface FeignProviderClient extends IFeignProviderService {
}
