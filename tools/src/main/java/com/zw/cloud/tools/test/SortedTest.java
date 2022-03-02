package com.zw.cloud.tools.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.tools.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class SortedTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("zw" + i);
            user.setAge((byte)i);
            list.add(user);
        }

        List<User> collect = list.stream().sorted(Comparator.comparing(SortedTest::compare)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        Map<String, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getName));
        map.forEach((k,v) ->{
            list.add(new User());
        });
      /*  Map<String, List<User>> map1 = list.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println(JSON.toJSONString(map1));*/

        ArrayList<String> strings = Lists.newArrayList( "013", "100", "101","001", "002", "010");
        strings.sort(Comparator.comparing(s -> s));
        System.out.println(strings.toString());
    }
    public static int compare(User user){
        if (Objects.equals(user.getName(),"zw4")){
            return 1;
        }
        if (Objects.equals(user.getName(),"zw6")){
            return 2;
        }
        if (Objects.equals(user.getName(),"zw8")){
            return 3;
        }
        return 5;
    }
}
