package com.zw.cloud.rocketmq.consumer.handler;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component("orderlyConsumerHandlerInstance")
@Slf4j
public class OrderlyConsumerHandlerInstance extends ConsumerHandler{

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void handleRocketMQMsg(String messageBody) {
        log.info("[OrderlyConsumerHandlerInstance][handleRocketMQMsg] messageBody is {}",messageBody);
        // 同步顺序发送，使用orderly可以顺序消费 有异常，maxReconsumeTimes不设置，阻塞后续消费,不断重试
        // 异步发送无法，使用orderly无法保证顺序消息
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
    }
}
