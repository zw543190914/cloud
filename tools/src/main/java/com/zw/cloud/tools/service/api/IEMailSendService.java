package com.zw.cloud.tools.service.api;

import org.springframework.core.io.InputStreamSource;

public interface IEMailSendService {

    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String[] to,String[] cc, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String[] to,String[] cc, String subject, String content)throws Exception;



    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param inputStreamSource 附件
     */
    void sendAttachmentsMail(String[] to, String[] cc, String subject,
                             String content, String fileName,
                             InputStreamSource inputStreamSource)throws Exception;

}
