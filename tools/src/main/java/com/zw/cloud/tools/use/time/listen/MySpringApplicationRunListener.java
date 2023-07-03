package com.zw.cloud.tools.use.time.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * https://mp.weixin.qq.com/s/8PaGxFjvR4O8B1E6-KX7cg
 * 添加一个自定义的实现类，在不同阶段结束时打印下当前时间，通过计算不同阶段的运行时间，就能大体定位哪些阶段耗时比较高
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    // 这个构造函数不能少，否则反射生成实例会报错
    public MySpringApplicationRunListener(SpringApplication springApplication, String[] args) {
    }
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        log.info("[MySpringApplicationRunListener]starting {}", LocalDateTime.now());
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
                                    ConfigurableEnvironment environment) {
        log.info("[MySpringApplicationRunListener]environmentPrepared {}", LocalDateTime.now());
    }
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("[MySpringApplicationRunListener]contextPrepared {}", LocalDateTime.now());
    }
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("[MySpringApplicationRunListener]contextLoaded {}", LocalDateTime.now());
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("[MySpringApplicationRunListener]started {}", LocalDateTime.now());
    }
    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("[MySpringApplicationRunListener]running {}", LocalDateTime.now());
    }
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("[MySpringApplicationRunListener]failed {}", LocalDateTime.now());
    }
}