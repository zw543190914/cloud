package com.zw.cloud.tools.service.impl.poem;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.dao.poem.PoemMapper;
import com.zw.cloud.tools.entity.poem.Poem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.*;


class PoemServiceImplTest {

    @InjectMocks
    private PoemServiceImpl poemService;
    @Mock
    ServiceImpl service;
    @Mock
    private PoemMapper baseMapper;

    @BeforeEach
    void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBatch(){
        int count = 1;
        Mockito.when(service.updateBatchById(any())).thenReturn(true);

        Mockito.when(service.saveBatch(any())).thenReturn(true);
        Poem poem = new Poem();
        poem.setContent("test");
        poemService.saveBatch(Lists.newArrayList());
    }
}
