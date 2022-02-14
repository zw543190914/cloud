package com.zw.cloud.tools.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.entity.UserDTO;
import com.zw.cloud.tools.entity.poem.Poem;
import org.springframework.beans.BeanUtils;

public class BeanCopyTest {

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
        BeanUtil.copyProperties(source,target);
        System.out.println("===================");
        System.out.println(JSON.toJSONString(target));
        System.out.println(source == target); // false
        System.out.println(source.getPoem() == target.getPoem()); // true
    }
}
