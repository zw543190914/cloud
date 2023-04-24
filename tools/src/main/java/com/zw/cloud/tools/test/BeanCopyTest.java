package com.zw.cloud.tools.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.utils.bean.test.entity.Student;
import com.zw.cloud.common.utils.bean.test.entity.StudentVO;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.entity.UserDTO;
import com.zw.cloud.tools.entity.poem.Poem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class BeanCopyTest {

    public static void main(String[] args){
        System.out.println("---------- testDeepCopy ----------");
        testDeepCopy();
        System.out.println("---------- swap ----------");
        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");
        // Java 中只有值传递，是没有引用传递的
        // person1 和 person2 的互换只是拷贝的两个地址的互换罢了，并不会影响到实参 xiaoZhang 和 xiaoLi
        swap(xiaoZhang, xiaoLi);
        System.out.println("person1:" + xiaoZhang.getName()); // 小张
        System.out.println("person2:" + xiaoLi.getName()); // 小李
        System.out.println("---------- copyField ----------");
        copyField();
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
        System.out.println("testDeepCopy:" + JSON.toJSONString(target));
        System.out.println(source == target); // false
        System.out.println(source.getPoem() == target.getPoem()); // true
        // 转为JSON 后重新转换
        target = JSON.parseObject(JSON.toJSONString(source), UserDTO.class);
        System.out.println(source == target); // false
        System.out.println(source.getPoem() == target.getPoem()); // false
    }


    public static void swap(Person person1, Person person2) {
        System.out.println(person1 + ":" + person2);
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println(person1 + ":" + person2);
        System.out.println("swap person1:" + person1.getName()); // 小李
        System.out.println("swap person2:" + person2.getName()); // 小张
    }

    public static void copyField() {
        StudentVO studentVO = new StudentVO();
        studentVO.setNm("zw");
        studentVO.setId(1L);
        studentVO.setBir(LocalDateTime.now());
        Student student = new Student();
        com.zw.cloud.common.utils.bean.BeanUtils.copyByCopyField(studentVO, student);
        System.out.println("copyField:" + JSON.toJSONString(student));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Person {
        private String name;
    }


}
