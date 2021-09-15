package com.zw.cloud.tools.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class LoadingCacheTest {

    static LoadingCache<Integer,Integer> numCache = CacheBuilder.newBuilder().
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

    public static void main(String[] args) throws Exception{
        while (true) {
            queryNum(1);
            Thread.sleep(3000);
        }
    }

    public static void queryNum(Integer key) throws Exception{
        System.out.println(numCache.get(key));
    }

    private static Integer queryFromDB(Integer key){
        System.out.println("db");
        return key;
    }
}
