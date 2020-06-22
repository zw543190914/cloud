package com.zw.cloud.feignprovider.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ZkInterProcessMutexService {

    @Autowired
    CuratorFramework zkClient;

    private String lockPath = "/demo/local";

    private Logger logger = LoggerFactory.getLogger(ZkInterProcessMutexService.class);

    public void testZkLock()throws Exception {
        logger.info("[ZkInterProcessMutexService][testZkLock]start.....");

        InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
        boolean acquire = lock.acquire(1, TimeUnit.SECONDS);

        if (acquire){
            // 执行业务
            try {
                logger.info("执行业务.....");
                Thread.sleep(6000);
            } catch (Exception e) {
                logger.error("[ZklocakDemo][testZkLock] error is {}",e);
                throw e;
            }finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    logger.error("[ZklocakDemo][testZkLock]release error is {}",e);
                }
            }
        } else {
            throw new RuntimeException("acquire lock failed");
        }

    }
}
