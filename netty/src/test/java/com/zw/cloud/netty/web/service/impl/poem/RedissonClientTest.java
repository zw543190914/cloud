package com.zw.cloud.netty.web.service.impl.poem;


import com.zw.cloud.netty.web.dao.poem.PoemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static org.mockito.ArgumentMatchers.*;

public class RedissonClientTest {

    @InjectMocks
    PoemServiceImpl poemService;
    @Mock
    PoemMapper mapper;
    @Mock
    RedissonClient redissonClient;
    @Mock
    RLock lock;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLock() {
        Mockito.when(redissonClient.getLock(anyString())).thenReturn(lock);
        Mockito.doNothing().when(lock).lock();
        try {
            poemService.testLock(1L,"1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
