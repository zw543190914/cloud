package com.zw.cloud.common.utils.bean.test;

import cn.hutool.json.JSONUtil;
import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.common.utils.bean.test.entity.Student;
import com.zw.cloud.common.utils.bean.test.entity.StudentVO;

import java.time.LocalDateTime;

public class Test {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("zw");
        student.setId(1L);
        student.setBirthday(LocalDateTime.now());
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);
        System.out.println(JSONUtil.toJsonStr(studentVO));
    }
}
