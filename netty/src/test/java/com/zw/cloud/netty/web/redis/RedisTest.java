package com.zw.cloud.netty.web.redis;

import com.google.common.collect.Sets;
import com.zw.cloud.netty.web.controller.RedisController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class RedisTest {

    @InjectMocks
    RedisController redisController;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    RedisTemplate redisTemplate;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test() {
        Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
        set.add(ZSetOperations.TypedTuple.of("zw",1.0));
        set.add(ZSetOperations.TypedTuple.of("zw",2.0));
        Mockito.when(redisTemplate.opsForZSet().reverseRangeWithScores(any(),anyLong(),anyLong())).thenReturn(set);
        Mockito.when(redisTemplate.opsForZSet().rangeByScore(any(),anyLong(),anyLong())).thenReturn(Sets.newHashSet("1"));

        redisController.sortedSet();
    }
}
