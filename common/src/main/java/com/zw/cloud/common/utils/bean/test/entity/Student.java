package com.zw.cloud.common.utils.bean.test.entity;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Student implements Serializable {
    private Long id;
    private String name;
    private LocalDateTime birthday;


}
