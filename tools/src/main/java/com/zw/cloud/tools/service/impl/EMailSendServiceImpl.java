package com.zw.cloud.tools.service.impl;

import com.zw.cloud.tools.service.api.IEMailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Primary
public class EMailSendServiceImpl implements IEMailSendService {

    private Logger logger = LoggerFactory.getLogger(EMailSendServiceImpl.class);


    @Autowired
    private JavaMailSenderImpl javaMailSender;

    /**
     * 配置文件中我的qq邮箱
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 简单文本邮件
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String[] to,String[] cc,String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        if (null != cc && cc.length > 0){
            message.setCc(cc);
        }
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * html邮件
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String[] to,String[] cc, String subject, String content) throws Exception{
        //获取MimeMessage对象
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        //邮件发送人
        messageHelper.setFrom(from);
        //邮件接收人
        messageHelper.setTo(to);
        if (null != cc && cc.length > 0){
            messageHelper.setCc(cc);
        }
        //邮件主题
        message.setSubject(subject);
        //邮件内容，html格式
        messageHelper.setText(content, true);
        try {
            //发送
            javaMailSender.send(message);
            //日志信息
            logger.info("[MailServiceImpl] sendHtmlMail success");
        } catch (Exception e) {
            logger.error("[MailServiceImpl] sendHtmlMail error is ", e);
            throw e;
        }
    }

    /**
     * 带附件的邮件
     * @param to 收件人
     * @param cc 抄送人
     * @param subject 主题
     * @param content 内容
     * @param inputStreamSource 附件
     */
    @Override
    public void sendAttachmentsMail(String[] to, String[] cc, String subject,
                                    String content, String fileName,
                                    InputStreamSource inputStreamSource)throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            if (null != cc && cc.length > 0){
                helper.setCc(cc);
            }
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.addAttachment(fileName, inputStreamSource);
            javaMailSender.send(message);
            //日志信息
            logger.info("[MailServiceImpl] sendAttachmentsMail success");
        } catch (MessagingException e) {
            logger.error("[MailServiceImpl] sendAttachmentsMail error is ", e);
            throw e;
        }
    }
}
