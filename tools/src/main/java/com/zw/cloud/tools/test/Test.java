package com.zw.cloud.tools.test;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Test {

    public static final Map<String,String> map = Maps.newConcurrentMap();


    public Test(List<String> list){
        list.forEach(s-> map.put(s,s));
    }



/* public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("0.9");

        System.out.println(bigDecimal.multiply(new BigDecimal("50")).divide(new BigDecimal(100)));
    }*/
}
