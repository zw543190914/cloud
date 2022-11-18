package com.zw.cloud.tools.service.impl.poem;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.dao.poem.PoemMapper;
import com.zw.cloud.tools.entity.poem.Poem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mockStatic;

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
        //TableInfoHelper.initTableInfo(new MapperBuilderAssistant(new MybatisConfiguration(), ""), Poem.class);

    }

    @Test
    void testBatch(){
        Poem poem = new Poem();
        poem.setContent("test");
        ArrayList<Poem> poems = Lists.newArrayList(poem);
       /* ServiceImpl spy = Mockito.spy(service);
        Mockito.doReturn(true).when(spy).saveBatch(Mockito.anyCollection());*/
        //Mockito.when(poemService.saveBatch(poems)).thenReturn(true);
        try (MockedStatic<SqlHelper> sqlHelperMockedStatic = mockStatic(SqlHelper.class)) {
            sqlHelperMockedStatic.when(() -> SqlHelper.executeBatch(any(),any(),anyCollection(),anyInt(),any())).thenReturn(true);
            boolean b = poemService.saveBatch(Lists.newArrayList(poems));
            System.out.println(b);
        }

    }
}
