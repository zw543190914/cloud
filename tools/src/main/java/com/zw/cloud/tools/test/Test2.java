package com.zw.cloud.tools.test;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1","2");
        Test test = new Test(list);
        System.out.println(JSON.toJSONString(Test.map));
    }
}
