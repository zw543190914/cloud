package com.zw.cloud.tools.mqtt.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.mqtt.config.MqttConfig;
import com.zw.cloud.tools.utils.CustomerExecutorService;
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
            String message = buildContent(setValue, actValue + incrementAndGet, actValue + incrementAndGet, second);
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

    private static String buildContent(int setValue,int actValue,int speed,Long second){
        String content = "{\n" +
                "\"stenterStatus\":{\n" +
                "    \"rtime\": "+ second +",     \n" +
                "    \"ctime\": "+ second +",    \n" +
                "    \"mt\": 1,\n" +
                "    \"ec\": 8830,\n" +
                "    \"d01\": "+ setValue +",\n" +
                "    \"d02\": "+ speed +",\n" +
                "    \"d04\": " + actValue +",\n" +
                "    \"d05\": " + actValue +",\n" +
                "    \"d03\": " + actValue +",\n" +
                "    \"d06\": " + setValue +",\n" +
                "    \"d07\": " + actValue +",\n" +
                "    \"d08\": " + setValue +",\n" +
                "    \"d09\": " + actValue +",\n" +
                "    \"d10\": " + setValue +",\n" +
                "    \"d11\": " + actValue +",\n" +
                "    \"d12\": " + setValue +",\n" +
                "    \"d13\": " + actValue +",\n" +
                "    \"d14\": " + setValue +",\n" +
                "    \"d15\": " + actValue +",\n" +
                "    \"d16\": " + setValue +",\n" +
                "    \"d17\": " + actValue +",\n" +
                "    \"d18\": " + setValue +",\n" +
                "    \"d19\": " + actValue +",\n" +
                "    \"d20\": " + setValue +",\n" +
                "    \"d21\": " + actValue +",\n" +
                "    \"d22\": " + setValue +",\n" +
                "    \"d23\": " + actValue +",\n" +
                "    \"d24\": " + actValue +",\n" +
                "    \"d25\": " + actValue +",\n" +
                "    \"d26\": " + actValue +",\n" +
                "    \"d27\": " + actValue +",\n" +
                "    \"d28\": " + actValue +",\n" +
                "    \"d29\": " + actValue +",\n" +
                "    \"d30\": " + actValue +",\n" +
                "    \"d31\": " + actValue +",\n" +
                "    \"d32\": " + actValue +",\n" +
                "    \"d33\": " + actValue +",\n" +
                "    \"d34\": " + setValue +",\n" +
                "    \"d35\": " + actValue +",\n" +
                "    \"d36\": " + setValue +",\n" +
                "    \"d37\": " + actValue +",\n" +
                "    \"d38\": " + setValue +",\n" +
                "    \"d39\": " + actValue +",\n" +
                "    \"d40\": " + setValue +",\n" +
                "    \"d41\": " + actValue +",\n" +
                "    \"d42\": " + setValue +",\n" +
                "    \"d43\": " + actValue +",\n" +
                "    \"d44\": " + setValue +",\n" +
                "    \"d45\": " + actValue +",\n" +
                "    \"d46\": " + setValue +",\n" +
                "    \"d47\": " + actValue +",\n" +
                "    \"d48\": " + setValue +",\n" +
                "    \"d49\": " + actValue +",\n" +
                "    \"d50\": " + setValue +",\n" +
                "    \"d51\": " + actValue +",\n" +
                "    \"d52\": " + setValue +",\n" +
                "    \"d53\": " + actValue +",\n" +
                "    \"d54\": " + setValue +",\n" +
                "    \"d55\": " + actValue +",\n" +
                "    \"d56\": " + setValue +",\n" +
                "    \"d57\": " + actValue +",\n" +
                "    \"d58\": " + setValue +",\n" +
                "    \"d59\": " + actValue +",\n" +
                "    \"d60\": " + actValue +",\n" +
                "    \"d61\": " + actValue +",\n" +
                "    \"d65\": " + setValue +",\n" +
                "    \"d66\": " + actValue +",\n" +
                "    \"d67\": " + setValue +",\n" +
                "    \"d68\": " + actValue +",\n" +
                "    \"d69\": " + setValue +",\n" +
                "    \"d70\": " + actValue +",\n" +
                "    \"d71\": " + setValue +",\n" +
                "    \"d72\": " + actValue +",\n" +
                "    \"d73\": " + setValue +",\n" +
                "    \"d74\": " + actValue +",\n" +
                "    \"d75\": " + setValue +",\n" +
                "    \"d76\": " + actValue +",\n" +
                "    \"d77\": " + setValue +",\n" +
                "    \"d78\": " + actValue +",\n" +
                "    \"d79\": " + setValue +",\n" +
                "    \"d80\": " + actValue +",\n" +
                "    \"d81\": " + setValue +",\n" +
                "    \"d82\": " + actValue +",\n" +
                "    \"d83\": " + 80 +",\n" +
                "    \"d84\": " + 80 +",\n" +
                "    \"d85\": " + 80 +",\n" +
                "    \"d86\": " + 80 +",\n" +
                "    \"d87\": " + 80 +",\n" +
                "    \"d88\": " + 80 +",\n" +
                "    \"d89\": " + 80 +",\n" +
                "    \"d90\": " + 80 +",\n" +
                "    \"d91\": " + 80 +",\n" +
                "    \"d92\": " + 80 +",\n" +
                "    \"d93\": " + 80 +",\n" +
                "    \"d94\": " + 80 +",\n" +
                "    \"d95\": " + 80 +",\n" +
                "    \"d96\": " + 80 +",\n" +
                "    \"d97\": " + 80 +",\n" +
                "    \"d98\": " + 80 +",\n" +
                "    \"d99\": " + 80 +",\n" +
                "    \"d100\": " + 80 +",\n" +
                "    \"d101\": " + 80 +" \n" +
              /*  "    \"d150\": " + 100 +" \n" +
                "    \"d300\": " + 2 +" \n" +
                "    \"d400\": " + setValue +",\n" +
                "    \"d401\": " + actValue +",\n" +
                "    \"d500\": " + actValue +",\n" +
                "    \"d501\": " + actValue +",\n" +
                "    \"d502\": " + actValue +",\n" +
                "    \"d600\": " + actValue +",\n" +
                "    \"d601\": " + actValue +",\n" +
                "    \"d602\": " + actValue +",\n" +
                "    \"d603\": " + actValue +",\n" +
                "    \"d604\": " + setValue +",\n" +
                "    \"d605\": " + actValue +",\n" +
                "    \"d606\": " + setValue +",\n" +
                "    \"d607\": " + actValue +",\n" +
                "    \"d608\": " + setValue +",\n" +
                "    \"d609\": " + actValue +",\n" +
                "    \"d610\": " + setValue +",\n" +
                "    \"d611\": " + actValue +",\n" +
                "    \"d612\": " + setValue +",\n" +
                "    \"d613\": " + actValue +",\n" +
                "    \"d614\": " + setValue +",\n" +
                "    \"d615\": " + actValue +",\n" +
                "    \"d616\": " + setValue +",\n" +
                "    \"d617\": " + actValue +",\n" +
                "    \"d618\": " + setValue +",\n" +
                "    \"d619\": " + actValue +",\n" +
                "    \"d620\": " + setValue +",\n" +
                "    \"d621\": " + actValue +",\n" +
                "    \"d622\": " + setValue +",\n" +
                "    \"d623\": " + actValue +",\n" +
                "    \"d624\": " + setValue +",\n" +
                "    \"d625\": " + actValue +",\n" +
                "    \"d626\": " + setValue +",\n" +
                "    \"d627\": " + actValue +",\n" +
                "    \"d628\": " + setValue +",\n" +
                "    \"d629\": " + actValue +",\n" +
                "    \"d700\": " + setValue +",\n" +
                "    \"d701\": " + actValue +",\n" +
                "    \"d702\": " + actValue +",\n" +
                "    \"d703\": " + actValue +",\n" +
                "    \"d704\": " + actValue +",\n" +
                "    \"d705\": " + actValue +",\n" +
                "    \"d706\": " + actValue +",\n" +
                "    \"d707\": " + actValue +",\n" +
                "    \"d708\": " + actValue +",\n" +*/
                "  }\n" +
                "}";
        return content;
    }
}
