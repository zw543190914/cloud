package com.zw.cloud.netty.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.zw.cloud.netty.utils.RedisUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class LoadingCacheTest {

    LoadingCache<Integer,Integer> numCache = CacheBuilder.newBuilder().
            // 设置并发级别为cpu核心数
                    concurrencyLevel(Runtime.getRuntime().availableProcessors()).
                    expireAfterWrite(15, TimeUnit.SECONDS).
                    expireAfterAccess(10, TimeUnit.SECONDS).
                    maximumSize(100).
                    build(new CacheLoader<Integer, Integer>() {
                        @Override
                        public Integer load(Integer key) throws Exception {
                            return queryFromDB(key);
                        }
                    });

    public void main(String[] args) throws Exception{
        while (true) {
            queryNum(1);
            Thread.sleep(3000);
        }
    }

    public void queryNum(Integer key) throws Exception{
        System.out.println(numCache.get(key));
    }

    private Integer queryFromDB(Integer key){
        System.out.println("db");
        RedisUtils.
        return key;
    }
}
