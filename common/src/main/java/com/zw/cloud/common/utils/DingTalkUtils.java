package com.zw.cloud.common.utils;

import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import com.zw.cloud.common.thread.pool.ThreadExecutorPool;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


/**
 * https://open.dingtalk.com/document/orgapp/custom-bot-to-send-group-chat-messages
 * 加签
 * https://open.dingtalk.com/document/robots/customize-robot-security-settings
 */
@Slf4j
public class DingTalkUtils {

    private static String br = " \n\n ";

    public static void main(String[] args) throws Exception {
        //System.out.println(genSign(System.currentTimeMillis(), "SEC1ded33d7e58ef4aa813d074e7e24e7df83d9427ee7496d819bdff256ceda1583"));
        //sendDingTalkMsg("7b66a82f1620672a1f5b2229d536d41cd978fb9f949141df8b40cd3b8bc9dd54",DingTalkUtils.class,"sendDingTalkMsg",null,new RuntimeException("running exception..."));
        sendDingTalkMsgWithSign("标题","3a93469afdb2c38e22f0944e7f61a9b4d2e95a0138901d813ce6fe2c33dd1145","SECd51ae59f656260a2f859e4e149a54e120c8673a9aa789cf60f7ee20f09048a49","<font color=#0000FF>哈哈哈</font> \n\n  嘻嘻");
        TimeUnit.SECONDS.sleep(10);
    }

    public static void sendDingTalkMsg(String token,Class clazz,String methodName,Object args,Exception e) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + token);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        /*request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("132xxxxxxxx"));
        // isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(true);
        at.setAtUserIds(Arrays.asList("109929","32099"));
        request.setAt(at);

        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
        request.setLink(link);*/
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("请注意");
        markdown.setText("#### 程序运行出错\n" +
                "> className : "+ clazz + br +
                "> methodName : "+ methodName + br +
                "> param : "+ JSONUtil.toJsonStr(args) + br +
                "> error : "+ e + br +
                //"> ![screenshot](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091108%2Fvmipqy4caz4vmipqy4caz4.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650772948&t=c175d48d866995d3474b52bd4547972a)\n"  +
                //"> ![screenshot](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmx12%2F0F420115037%2F200F4115037-11.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638372678&t=004b8405f7b0780a5226dbb99b04c924)\n"  +
                "> [百度一下](http://www.baidu.com/) \n");
        request.setMarkdown(markdown);
        sendMsgByClient(client,request);
    }

    public static void sendDingTalkMsgWithSign(String title,String token,String sign,String msg) {
        long timestamp = System.currentTimeMillis();
        String genSign;
        try {
            genSign = genSign(timestamp, sign);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            log.error("[DingTalkUtils][sendDingTalkMsgWithSign] token is {}, sign is {},error is ", token,sign,e);
            return;
        }
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + token + "&timestamp=" + timestamp + "&sign=" + genSign);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        // <font color=#0000FF>蓝色字体</font>
        markdown.setText(msg);
        request.setMarkdown(markdown);
        sendMsgByClient(client,request);
    }

    public static void sendDingTalkMsg(String token,String userId,String msg) {
        if (StringUtils.isBlank(token)) {
            token = "7b66a82f1620672a1f5b2229d536d41cd978fb9f949141df8b40cd3b8bc9dd54";
        }
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + token);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("意见&建议");
        markdown.setText("#### 意见&建议\n" +
                "> userId : "+ userId + br +
                "> 意见&建议 : "+ msg + br +
                "> ![screenshot](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-18%2F59e733be24fec.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1673529052&t=5763d086e9fd67b4625a3699cde8229f)\n"  +
                "> [百度一下](http://www.baidu.com/) \n");
        request.setMarkdown(markdown);
        sendMsgByClient(client,request);
    }

    public static void sendDingTalkChatMsg(String msg) {
        String token = "";
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + token);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("chatGPT");
        markdown.setText("#### chat\n" +
                "> "+ msg + "\n");
        request.setMarkdown(markdown);
        sendMsgByClient(client,request);
    }

    private static void sendMsgByClient(DingTalkClient client,OapiRobotSendRequest request) {
        CompletableFuture.supplyAsync(() -> {
            try {
                OapiRobotSendResponse execute = client.execute(request);
                log.info("[DingTalkUtils][sendMsgByClient] request is {}, execute is {}", JSONUtil.toJsonStr(request),JSONUtil.toJsonStr(execute));
            } catch (ApiException e) {
                log.error("[DingTalkUtils][sendMsgByClient] request is {}, error is ", JSONUtil.toJsonStr(request),e);
            }
            return null;
        },ThreadExecutorPool.msgThreadPoolExecutor).whenComplete((result,ex) -> {
            if (Objects.nonNull(ex)) {
                log.error("[DingTalkUtils][sendMsgByClient] request is {}, error is ", JSONUtil.toJsonStr(request),ex);
            }
        });
    }

    private static String genSign(long timestamp,String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
    }
}
