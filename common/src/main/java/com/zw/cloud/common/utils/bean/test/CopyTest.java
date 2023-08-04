package com.zw.cloud.common.utils.bean.test;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.common.utils.bean.test.entity.Student;
import com.zw.cloud.common.utils.bean.test.entity.StudentVO;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
public class CopyTest {

    public static void main(String[] args) {
        StudentVO studentVO = new StudentVO();
        studentVO.setNm("zw");
        studentVO.setId(1L);
        studentVO.setBir(LocalDateTime.now());

        Student student = new Student();
        BeanUtils.copyByCopyField(studentVO, student);
        System.out.println(JSON.toJSONString(student));

        Student student1 = new Student();
        BeanUtils.copyByCopyFieldOnTarget(studentVO, student1);
        System.out.println(JSON.toJSONString(student1));

    }
}
