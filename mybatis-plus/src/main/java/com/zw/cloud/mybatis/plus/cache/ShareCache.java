package com.zw.cloud.mybatis.plus.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ShareCache {

    LoadingCache<String, List<String>> shareCache = CacheBuilder.newBuilder().
            expireAfterWrite(30, TimeUnit.MINUTES).
            expireAfterAccess(30, TimeUnit.MINUTES).
            maximumSize(100).
            build(new CacheLoader<String, List<String>>() {
                @Override
                public List<String> load(String key) {
                    log.info("[ShareCache][load]key is {}", key);
                    return queryShareList(key);
                }
            });

    public List<String> queryShareListFromCache(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return shareCache.get(key);
        } catch (CacheLoader.InvalidCacheLoadException | ExecutionException e) {
            return null;
        }
    }

    private List<String> queryShareList(String key) {
        log.info("[ShareCache][queryShareList] ");
        return Lists.newArrayList("devController","devController1");
    }

}
