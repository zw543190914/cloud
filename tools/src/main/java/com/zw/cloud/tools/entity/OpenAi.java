package com.zw.cloud.tools.entity;

import lombok.Data;

import java.util.List;


@Data
public class OpenAi {
    private String model;
    private String prompt;
    private Double temperature;
    private Double topP;
    private List<String> stop;
    private Double presencePenalty;
    private Double frequencyPenalty;

}
