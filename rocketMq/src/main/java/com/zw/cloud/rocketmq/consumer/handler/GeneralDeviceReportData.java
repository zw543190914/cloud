package com.zw.cloud.rocketmq.consumer.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.rocketmq.entity.dto.IotInfoDto;
import com.zw.cloud.rocketmq.entity.dto.IotMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component("generalDeviceReportData")
@Slf4j
public class GeneralDeviceReportData extends ConsumerHandler{
    @Resource
    private RestTemplate restTemplate;

    @Resource(name = "ioThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[GeneralDeviceReportData][handleRocketMQMsg] messageBody is {}",messageBody);
        // 非顺序发送,无论同步还是异步 有异常，不阻塞后续消费
        // 无论同步还是异步顺序发送，没有顺序
        /*if (StringUtils.equals("zw5",messageBody)) {
            throw new BizException("ex");
        }*/
        /*Map<String,String> param = new HashMap<>();
        param.put("name",messageBody);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("userId", "E5F887E61DED452E9AD28E84746D49E9");
        HttpEntity<Object> httpEntity = new HttpEntity<>(param,headers);*/

        /*JSONObject jsonObject = restTemplate.exchange("http://127.0.0.1:8082/user-info/query?name=" + messageBody,
                HttpMethod.GET, null, JSONObject.class).getBody();
        log.info("[ChangeConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}, jsonObject is {}",messageBody, JSON.toJSONString(jsonObject));*/
        ioThreadPoolTaskExecutor.execute(() -> {
            Map map = JSON.parseObject(messageBody, Map.class);
            IotMessageDto iotMessageDto = JSON.parseObject(JSON.toJSONString(map.get("generalReport")), IotMessageDto.class);
            IotInfoDto iotInfoDto = new IotInfoDto();
            BeanUtils.copyByCopyField(iotMessageDto, iotInfoDto);
            iotInfoDto.setCtime(LocalDateTime.ofEpochSecond(iotMessageDto.getCtime(),0, ZoneOffset.of("+8")));
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = restTemplate.exchange("http://127.0.0.1:8082/general-device-report-data",
                    HttpMethod.POST, new HttpEntity<>(JSON.toJSONString(iotInfoDto)), JSONObject.class).getBody();
            log.info("[GeneralDeviceReportData][handleRocketMQMsg] messageBody is {}, jsonObject is {}",messageBody, JSON.toJSONString(jsonObject));
        });

    }
}
