package com.zw.cloud.tools.test;

import com.alibaba.fastjson2.JSON;
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
       /* Map<String, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getName));
        map.forEach((k,v) ->{
            list.add(new User());
        });*/
      /*  Map<String, List<User>> map1 = list.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println(JSON.toJSONString(map1));*/
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            User next = iterator.next();
            System.out.println(JSON.toJSONString(next));
        }
        ArrayList<String> strings = Lists.newArrayList( "013", "100", "101","000", "002", "010","1","3");
        List<String> strings1 = strings.subList(strings.size() - 2, strings.size());
        System.out.println(strings1.toString());
        strings.sort(Comparator.comparing(s -> s));
        System.out.println(strings.toString());
        strings.sort(Comparator.comparing(Integer::parseInt));
        System.out.println(strings.toString());

        List<User> users = list.stream().filter(user -> user.getAge() > 11).sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(users));
        List<String> names = list.stream().filter(user -> user.getAge() > 11).map(User::getName).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(names));

        Map<String, Integer> map = fillValue(6, 600);
        System.out.println(JSON.toJSONString(map));
        Map<String, Integer> map2 = fillValue(10, 600);
        System.out.println(JSON.toJSONString(map2));
        Map<String, Integer> map3 = fillValue(12, 600);
        System.out.println(JSON.toJSONString(map3));

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

    private static LinkedHashMap<String,Integer> fillValue(int count,int maxValue) {
        String s = "{\"dryingRoomPresetTemp1\": 18, \"dryingRoomPresetTemp2\": 28, \"dryingRoomPresetTemp3\": 38, \"dryingRoomPresetTemp4\": 88, \"dryingRoomPresetTemp5\": 88, \"dryingRoomPresetTemp6\": 48, \"dryingRoomPresetTemp7\": 68, \"dryingRoomPresetTemp8\": 78, \"dryingRoomPresetTemp9\": 88, \"dryingRoomPresetTemp10\": 88}";
        LinkedHashMap<String, Integer> map = JSON.parseObject(s, LinkedHashMap.class);
        if (count == 10) {
            return map;
        }
        map.put("dryingRoomPresetTemp" + count,Optional.ofNullable(map.get("dryingRoomPresetTemp10")).orElse(maxValue));
        if (count > 10) {
            for (int i = 10; i <= count-1; i++) {
                map.put("dryingRoomPresetTemp" + i,maxValue);
            }
            return map;
        }
        for (int i = count; i <= 10; i++) {
            map.remove("dryingRoomPresetTemp" + i);
        }
        return map;
    }
}
