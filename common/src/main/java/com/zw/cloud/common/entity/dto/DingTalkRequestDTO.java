package com.zw.cloud.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class DingTalkRequestDTO {
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("atUsers")
    private List<AtUsersDTO> atUsers;
    @JsonProperty("chatbotUserId")
    private String chatbotUserId;
    @JsonProperty("msgId")
    private String msgId;
    @JsonProperty("senderNick")
    private String senderNick;
    @JsonProperty("isAdmin")
    private Boolean isAdmin;
    @JsonProperty("sessionWebhookExpiredTime")
    private Long sessionWebhookExpiredTime;
    @JsonProperty("createAt")
    private Long createAt;
    @JsonProperty("conversationType")
    private String conversationType;
    @JsonProperty("senderId")
    private String senderId;
    @JsonProperty("conversationTitle")
    private String conversationTitle;
    @JsonProperty("isInAtList")
    private Boolean isInAtList;
    @JsonProperty("sessionWebhook")
    private String sessionWebhook;
    @JsonProperty("text")
    private TextDTO text;
    @JsonProperty("robotCode")
    private String robotCode;
    @JsonProperty("msgtype")
    private String msgtype;

    @NoArgsConstructor
    @Data
    public static class TextDTO {
        @JsonProperty("content")
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class AtUsersDTO {
        @JsonProperty("dingtalkId")
        private String dingtalkId;
    }
}
