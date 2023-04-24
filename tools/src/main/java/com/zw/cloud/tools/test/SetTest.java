package com.zw.cloud.tools.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zw.cloud.tools.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetTest {
    public static void main(String[] args) {
        HashSet<Integer> set1 = Sets.newHashSet(1, 2, 3,5);
        HashSet<Integer> set2 = Sets.newHashSet( 2, 3,4);
        Set<Integer> difference = Sets.difference(set1, set2);
        System.out.println("difference" + difference.toString());
        System.out.println("symmetricDifference" + Sets.symmetricDifference(set1, set2));
        System.out.println("intersection" + Sets.intersection(set1, set2).toString());
        ArrayList<Integer> list = Lists.newArrayList();
        Integer sum = list.stream().reduce(null,Integer::sum);
        System.out.println(sum);
        BigDecimal sum2 = Lists.newArrayList(new BigDecimal(1),new BigDecimal(1),new BigDecimal(3)).stream().reduce(new BigDecimal(0),BigDecimal::add);
        System.out.println(sum2);
        System.out.println("reduce:" + set1.stream().reduce(0, Integer::sum));
        System.out.println("summarizingLong:" + set1.stream().collect(Collectors.summarizingLong(v->v)).getSum());

        User user = new User();
        user.setId(null);
        user.setName("1");
        User user2 = new User();
        user2.setId(null);
        user2.setName("2");
        User user3 = new User();
        user3.setId(1L);
        user3.setName("3");
        User user4 = new User();
        user4.setId(1L);
        user4.setName("4");
        User user5 = new User();
        user5.setId(5L);
        user5.setName("5");
        Map<Long, String> map = Lists.newArrayList(user, user2, user3, user4,user5).stream().collect(Collectors.toMap(User::getId, User::getName, (v1, v2) -> v1));
        System.out.println(JSON.toJSONString(map));

    }
}
