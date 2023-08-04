package com.zw.cloud.common.utils.bean.test.entity;



import com.zw.cloud.common.utils.bean.annotation.CopyField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Student implements Serializable {
    private Long id;
    @CopyField(sourceFieldName = "nm")
    private String name;
    @CopyField(sourceFieldName = "bir")
    private LocalDateTime birthday;


}
