package com.zw.cloud.common.entity.dto.jsoup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class BaiduImgDTO implements Serializable {
    private static final long serialVersionUID = 7152581025900070424L;
    private String title;
    private String url;
}
