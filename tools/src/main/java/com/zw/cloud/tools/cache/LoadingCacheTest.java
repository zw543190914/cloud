package com.zw.cloud.tools.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
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
                public Integer load(Integer key) {
                    System.out.println(key + " db");
                    // 结果返回为空 会抛出InvalidCacheLoadException,后续查询会继续查询数据库,只有缓存中有值才会走缓存
                    return queryFromDB(key);
                }
            });

    public static void main(String[] args) throws Exception{
        while (true) {

            queryNum(1);
            // 返回 null 抛出InvalidCacheLoadException,后面需要查询db
            queryNum(0);

            Thread.sleep(3000);
        }

       /* String str = "1-2-3-4-  5-  6   - 9  1";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list);*/

    }

    public static Integer queryNum(Integer key) {
        try {
            return numCache.get(key);
        } catch (CacheLoader.InvalidCacheLoadException | ExecutionException e) {
            //e.printStackTrace();
        }
        return null;
    }

    private static Integer queryFromDB(Integer key){
        if (Objects.equals(0,key)) {
            return null;
        }
        return key;
    }
}
