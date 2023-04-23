package com.zw.cloud.common.config.thread.pool;

import com.zw.cloud.common.thread.pool.TIDThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@ConditionalOnExpression("${com.zw.cloud.default.thread.pool.enable:true}")
public class ThreadPoolTaskExecutorAutoConfig {

    @Bean("cpuThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor cpuThreadPoolTaskExecutor() {
        //返回可用处理器的虚拟机的最大数量不小于1
        int cpu = Runtime.getRuntime().availableProcessors();
        log.info("cpuThreadPoolTaskExecutor cpu : {}", cpu);
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 自定义方式,在多线程中传入TID
        TIDThreadPoolTaskExecutor executor = new TIDThreadPoolTaskExecutor();

        //配置核心线程数
        executor.setCorePoolSize(cpu + 1);
        //配置最大线程数
        executor.setMaxPoolSize(cpu + 1);
        executor.setKeepAliveSeconds(60);
        //配置队列大小
        executor.setQueueCapacity(2000);
        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(120);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("cpu-executor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    @Bean("ioThreadPoolTaskExecutor")
    public ThreadPoolTaskExecutor ioThreadPoolTaskExecutor() {
        int cpu = Runtime.getRuntime().availableProcessors();
        log.info("ioThreadPoolTaskExecutor cpu : {}", cpu);
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 自定义方式,在多线程中传入TID
        TIDThreadPoolTaskExecutor executor = new TIDThreadPoolTaskExecutor();

        //配置核心线程数
        executor.setCorePoolSize(cpu * 2);
        //配置最大线程数
        executor.setMaxPoolSize(cpu * 2);
        executor.setKeepAliveSeconds(60);
        //配置队列大小
        executor.setQueueCapacity(2000);
        //用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(120);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("io-executor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
