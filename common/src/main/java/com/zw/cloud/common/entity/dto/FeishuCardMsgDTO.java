package com.zw.cloud.common.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 飞书 卡片消息
 */
@Getter
@Setter
public class FeishuCardMsgDTO {

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
    private String msg_type = "interactive";

    /**
     * 消息内容
     */
    private Card card;

    @Getter
    @Setter
    public static class Card {
        private List<Elements> elements;
        private Header header;

        @Getter
        @Setter
        public static class Header {
            private Title title;

            @Getter
            @Setter
            public static class Title {
                private String content;
                private String tag = "plain_text";
            }
        }

        @Getter
        @Setter
        public static class Elements {

            private String tag = "div";
            private Text text;

            @Getter
            @Setter
            public static class Text {
                private String content;
                private String tag = "lark_md";
            }
        }
    }
}
