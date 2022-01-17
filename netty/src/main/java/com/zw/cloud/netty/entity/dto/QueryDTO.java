package com.zw.cloud.netty.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QueryDTO implements Serializable {
    private int pageNo = 1;
    private int pageSize = 10;
}
