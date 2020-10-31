package com.zw.cloud.tools.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomerExecutorService {

    public static ExecutorService pool = new ThreadPoolExecutor(4, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10240));
}
