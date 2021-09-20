package com.zw.cloud.mybatis.plus.entity.dto;

import lombok.*;

import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {

    private String name;

    private Long id;

    private LocalDateTime bir;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String description;

    private Object other;

    @Digits(integer = 4, fraction = 1)
    private BigDecimal value;

    private String orgCode;

    private String clientId;

}
