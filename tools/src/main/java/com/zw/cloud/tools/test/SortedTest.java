package com.zw.cloud.tools.test;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SortedTest {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("zw" + i);
            list.add(user);
        }

        List<User> collect = list.stream().sorted(Comparator.comparing(SortedTest::compare)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
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
