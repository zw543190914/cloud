package com.zw.cloud.tools.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    @BeforeEach
    public void init(){
        System.out.println("------------开始测试------------");
    }
    @AfterEach
    public void after(){
        System.out.println("------------测试结束------------");
    }
}
