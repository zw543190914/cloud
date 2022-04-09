package com.zw.cloud.tools.modle.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OcrVO implements Serializable {
    private String image_status;
    private List<KeyWord> words_result;


    @Getter
    @Setter
    public static class KeyWord {
        private String words;
    }
}
