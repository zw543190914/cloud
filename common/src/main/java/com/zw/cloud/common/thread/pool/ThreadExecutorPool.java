package com.zw.cloud.common.thread.pool;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.*;

public class ThreadExecutorPool {

    public static ExecutorService msgThreadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), new NamedThreadFactory("thread-pool-msg",true),new ThreadPoolExecutor.CallerRunsPolicy());
}
