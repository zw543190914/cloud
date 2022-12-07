package com.zw.cloud.netty.server;

import java.util.concurrent.*;

public class CustomerExecutorService {

    public static ExecutorService pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10240),new ThreadPoolExecutor.CallerRunsPolicy());

}
