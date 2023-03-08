package com.zw.cloud.tools.event.config;

import com.zw.cloud.tools.config.MyThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class ApplicationEventMulticasterConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(threadPoolTaskExecutor());
        return eventMulticaster;
    }

    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        //返回可用处理器的虚拟机的最大数量不小于1
        int cpu = Runtime.getRuntime().availableProcessors();
        log.info("threadPoolTaskExecutor cpu : {}", cpu);
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 自定义方式,在多线程中传入TID
        MyThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();

        //配置核心线程数
        executor.setCorePoolSize(cpu);
        //配置最大线程数
        executor.setMaxPoolSize(cpu);
        executor.setKeepAliveSeconds(60);
        //配置队列大小
        executor.setQueueCapacity(2000);
        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(120);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("event-task-executor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
