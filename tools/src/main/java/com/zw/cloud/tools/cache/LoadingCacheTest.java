package com.zw.cloud.tools.cache;

import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
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
            try {
                queryNum(1);
                // 返回 null 抛出InvalidCacheLoadException,后面需要查询db
                queryNum(0);
            } catch (CacheLoader.InvalidCacheLoadException e) {

            }

            Thread.sleep(3000);
        }

       /* String str = "1-2-3-4-  5-  6   - 9  1";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);*/

    }

    public static void queryNum(Integer key) throws Exception{
        numCache.get(key);
    }

    private static Integer queryFromDB(Integer key){
        if (Objects.equals(0,key)) {
            System.out.println(key + " db");
            return null;
        }
        System.out.println(key + " db");
        return key;
    }
}
