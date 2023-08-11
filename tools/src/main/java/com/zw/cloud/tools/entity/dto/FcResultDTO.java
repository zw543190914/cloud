package com.zw.cloud.tools.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class FcResultDTO {
    private List<FcDTO> result;


    @Getter
    @Setter
    public static class FcDTO{
        private String blue;
        private String red;
        private String code;
    }
}
