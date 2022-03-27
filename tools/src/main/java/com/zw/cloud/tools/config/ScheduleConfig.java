package com.zw.cloud.tools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.*;

@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(scheduleTaskExecutor());
    }

    @Bean
    public Executor scheduleTaskExecutor() {
        return  new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),new ThreadPoolExecutor.AbortPolicy());
    }
}

