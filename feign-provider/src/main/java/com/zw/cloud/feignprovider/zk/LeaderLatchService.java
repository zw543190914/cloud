/*
package com.zw.cloud.feignprovider.zk;

import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class LeaderLatchService {

    private static final String PATH = "/demo/leader";

    private LeaderLatch leaderLatch;

    @Value("${server.port}")
    private String port;

    @Autowired
    private CuratorFramework client;

    private Logger logger = LoggerFactory.getLogger(LeaderLatchService.class);


    //@PostConstruct 相当于 init，初始化时调用
    @PostConstruct
    public void start() throws Exception{
        logger.info("[LeaderLatchService] start ,port is {}", port);

        List<LeaderLatch> latchList = new ArrayList<>();
        List<CuratorFramework> clients = new ArrayList<>();

        clients.add(client);
        leaderLatch = new LeaderLatch(client, PATH, InetAddress.getLocalHost().getHostAddress());
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                logger.info("[LeaderLatchService] start ,port is {},leaderLatch id is {},I am leader. I am doing jobs!", port, leaderLatch.getId());
                System.out.println("======>port:" + port + leaderLatch.getId() + ":I am leader. I am doing jobs!");
            }

            @Override
            public void notLeader() {
                logger.info("[LeaderLatchService] start ,port is {},leaderLatch id is {},,I am not leader. I will do nothing!", port, leaderLatch.getId());
                System.out.println("======>port:" + port + leaderLatch.getId() + ":I am not leader. I will do nothing!");
            }
        });
        latchList.add(leaderLatch);
        try {
            leaderLatch.start();
            logger.info("[LeaderLatchService] started ,port is {},leaderLatch id is {}", port, leaderLatch.getId());
            //leaderLatch.close();
        } catch (Exception e) {
            logger.error("[LeaderLatchService] port is {},leaderLatch start error is {}",port,e );
        }

    }

    public boolean isLeader() {
        try {
            return leaderLatch != null && leaderLatch.hasLeadership() && leaderLatch.getLeader().getId().equals(
                    InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<String> getLeader() {
        return Optional.ofNullable(leaderLatch).map(latch -> {
            try {
                return latch.getLeader().getId();
            } catch (Exception e) {
                return null;
            }
        });
    }

    */
/**
     * 多台机器测试，当一台机器停止后，其他机器继续运行
     *//*

    @Async
    @Scheduled(cron = "0/15 * * * * ? ")
    public void testZkLock(){
        System.out.println("======>");
        if (isLeader()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("======>" + port + ":" + format.format(new Date()) + ": is running");
        }
    }
}
*/
