package com.zw.cloud.common.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengwei
 * @date 2022/4/17 17:16
 */
@Getter
@Setter
public class OcrQrVO implements Serializable {
    private List<Text> codes_result;

    @Getter
    @Setter
    public static class Text{
        private List<String> text;
    }
}
