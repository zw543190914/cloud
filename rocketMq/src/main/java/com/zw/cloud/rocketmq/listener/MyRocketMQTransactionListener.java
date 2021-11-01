package com.zw.cloud.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQTransactionListener()
public class MyRocketMQTransactionListener implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            log.info("[MyRocketMQTransactionListener][executeLocalTransaction]【本地业务执行完毕】 msg:{}, Object:{}", message, o);
            System.out.println("业务。。。");
        } catch (Exception e) {
            log.error("[MyRocketMQTransactionListener][executeLocalTransaction]【执行本地业务异常】 exception message:{}", e.getMessage());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        try {
            log.info("[MyRocketMQTransactionListener][checkLocalTransaction]【执行检查任务】");
            int i = 1 / 0;
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
