package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 飞书 富文本 消息
 */
@Getter
@Setter
public class FeishuPostMsgDTO {

    /**
     * 时间戳
     */
    private Integer timestamp;

    /**
     * 签名
     */
    private String sign;

    /**
     * 消息类型
     */
    private String msg_type = "post";

    /**
     * 消息内容
     */
    private OutContent content;

    @Getter
    @Setter
    public static class OutContent {
        private Post post;

        @Getter
        @Setter
        public static class Post {
            private Zh_cn zh_cn;

            @Getter
            @Setter
            public static class Zh_cn {

                private String title;
                private List<List<Content>> content;

                @Getter
                @Setter
                public static class Content {
                    // text  a  at
                    private String tag = "text";
                    private String text;
                }
            }

        }

    }
}
