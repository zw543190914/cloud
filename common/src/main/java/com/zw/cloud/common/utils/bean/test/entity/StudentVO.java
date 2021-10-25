package com.zw.cloud.common.utils.bean.test.entity;

import com.zw.cloud.common.utils.bean.annotation.CopyField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentVO implements Serializable {

    private Long id;
    @CopyField(originName = "name")
    private String nm;
    @CopyField(originName = "birthday")
    private LocalDateTime bir;
}
