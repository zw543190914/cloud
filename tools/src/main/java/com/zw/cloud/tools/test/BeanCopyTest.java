package com.zw.cloud.tools.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.entity.UserDTO;
import com.zw.cloud.tools.entity.poem.Poem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

public class BeanCopyTest {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Person {
        private String name;
    }



    public static void main(String[] args){
        User source = new User();
        source.setAge((byte)12);
        User target = new User();
        target.setId(1L);
        BeanUtil.copyProperties(source,target);
        System.out.println(JSON.toJSONString(target));

        User source1 = new User();
        source1.setAge((byte)12);
        User target1 = new User();
        target1.setId(1L);
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreNullValue();
        BeanUtil.copyProperties(source1,target1, copyOptions);
        System.out.println(JSON.toJSONString(target1));

        testDeepCopy();

        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");
        // Java 中只有值传递，是没有引用传递的
        // person1 和 person2 的互换只是拷贝的两个地址的互换罢了，并不会影响到实参 xiaoZhang 和 xiaoLi
        swap(xiaoZhang, xiaoLi);
        System.out.println("xiaoZhang:" + xiaoZhang.getName()); // 小张
        System.out.println("xiaoLi:" + xiaoLi.getName()); // 小李
    }

    public static void testDeepCopy(){
        UserDTO source = new UserDTO();
        source.setId(1L);
        source.setName("zw");
        Poem poem = new Poem();
        poem.setTitle("poem");
        source.setPoem(poem);

        UserDTO target = new UserDTO();
        BeanUtils.copyProperties(source,target);
        System.out.println(JSON.toJSONString(target));
        System.out.println(source == target); // false
        System.out.println(source.getPoem() == target.getPoem()); // true
        // 转为JSON 后重新转换
        target = JSON.parseObject(JSON.toJSONString(source), UserDTO.class);
        System.out.println(source == target); // false
        System.out.println(source.getPoem() == target.getPoem()); // false
    }


    public static void swap(Person person1, Person person2) {
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println("person1:" + person1.getName()); // 小李
        System.out.println("person2:" + person2.getName()); // 小张
    }
}
