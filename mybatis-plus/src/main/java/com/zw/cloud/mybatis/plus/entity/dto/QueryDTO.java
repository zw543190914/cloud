package com.zw.cloud.mybatis.plus.entity.dto;


import com.zw.cloud.mybatis.plus.db.annotations.MpOperator;
import com.zw.cloud.mybatis.plus.db.entity.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDTO extends PageRequest {

    @MpOperator
    private String name;
}
