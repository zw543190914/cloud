package com.zw.cloud.tools.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ThreadPoolControllerTest {

    @InjectMocks
    private ThreadPoolController threadPoolController;
    @Mock
    private ThreadPoolTaskExecutor ioThreadPoolTaskExecutor;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void query() {
        Mockito.doAnswer((InvocationOnMock invocation) -> {
            ((Runnable) invocation.getArguments()[0]).run();
            return null;
        }).when(ioThreadPoolTaskExecutor).execute(Mockito.any(Runnable.class));
        Assertions.assertDoesNotThrow(() -> threadPoolController.query(1));
    }
}
