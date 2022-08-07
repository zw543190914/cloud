package com.zw.cloud.sentinel.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String url) {
        if (StringUtils.isBlank(url)) {
            return url;
        }
        if (url.startsWith("/clear/")) {
            return "/clear/*";
        }
        return url;
    }
}
