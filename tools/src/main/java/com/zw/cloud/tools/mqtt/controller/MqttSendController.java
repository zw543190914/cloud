package com.zw.cloud.tools.mqtt.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.mqtt.config.MqttConfig;
import com.zw.cloud.tools.utils.CustomerExecutorService;
import com.zw.cloud.tools.utils.MqttPublishSample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengwei
 * @date 2022/5/25 13:36
 */
@RestController
@RequestMapping("/mqtt")
@Slf4j
public class MqttSendController {

    @Autowired
    private MqttConfig mqttClient;

    @RequestMapping("/sendMessage/{setValue}/{actValue}")
    //http://localhost:9040/mqtt/sendMessage/80/96
    public WebResult<Boolean> sendMessage(@PathVariable Integer setValue,@PathVariable Integer actValue) throws Exception{
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ScheduledFuture<?> scheduledFuture = CustomerExecutorService.scheduledExecutorService.scheduleAtFixedRate(() -> {
            long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            int incrementAndGet = count.incrementAndGet();
            String message = MqttPublishSample.buildContent(setValue, actValue + incrementAndGet, actValue + incrementAndGet, second);
            try {
                mqttClient.publish(message);
                log.info("[MqttSendController][sendMessage] count  is {}", incrementAndGet);
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 15, TimeUnit.SECONDS);
        countDownLatch.await();
        scheduledFuture.cancel(false);
        return WebResult.build(true);
    }
}
