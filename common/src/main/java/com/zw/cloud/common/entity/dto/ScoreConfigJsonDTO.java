package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ScoreConfigJsonDTO implements Serializable {

    private String name;
    private Integer preciseMatch;
    private Integer includeMatch;
    private Integer allMatch;
    private Integer deviceBrandMatch;
}
