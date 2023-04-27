package com.zw.cloud.rocketmq.consumer.handler;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component("changeConsumerHandlerInstance")
@Slf4j
public class ChangeConsumerHandlerInstance extends ConsumerHandler{

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[ChangeConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
        /*Map<String,String> param = new HashMap<>();
        param.put("name",messageBody);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("userId", "E5F887E61DED452E9AD28E84746D49E9");
        HttpEntity<Object> httpEntity = new HttpEntity<>(param,headers);*/

        JSONObject jsonObject = restTemplate.exchange("http://127.0.0.1:8082/user-info/query?name=" + messageBody,
                HttpMethod.GET, null, JSONObject.class).getBody();

        log.info("[ChangeConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}, jsonObject is {}",messageBody, JSON.toJSONString(jsonObject));
    }
}
