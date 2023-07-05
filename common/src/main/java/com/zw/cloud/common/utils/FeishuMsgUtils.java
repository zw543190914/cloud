package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.common.entity.dto.FeishuCardMsgDTO;
import com.zw.cloud.common.entity.dto.FeishuMsgDTO;
import com.zw.cloud.common.entity.dto.FeishuPostMsgDTO;
import com.zw.cloud.common.thread.pool.ThreadExecutorPool;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * https://open.feishu.cn/document/ukTMukTMukTM/ucTM5YjL3ETO24yNxkjN
 */
@Slf4j
public class FeishuMsgUtils {

    //这里就是刚才拿到的Webhook的值
    public static final String WEB_HOOK_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/819420bb-303a-4e26-8dc9-1850d86ebc2f";

    public static final String SECRET = "ADUmsMcaBv5eQj7lakis8e";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //sendMessage("这里是消息内容 \n 哈哈哈 ");
        sendCardMessage("标题"," <font color='red'> ss  </font>  \n  hh ");
        //sendPostMessage("标题"," {color:#DE350B}ce{color}  \n  hh ");
    }

    public static void sendMessage(String msg) {
        FeishuMsgDTO feishuMsgDTO = new FeishuMsgDTO();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        feishuMsgDTO.setTimestamp(timestamp);
        try {
            feishuMsgDTO.setSign(genSign());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("[FeishuMsgUtils][sendMessage]genSign error is ",e);
        }
        feishuMsgDTO.setMsg_type("text");
        FeishuMsgDTO.MsgContent msgContent = new FeishuMsgDTO.MsgContent();
        msgContent.setText(msg);
        feishuMsgDTO.setContent(msgContent);
        //发送post请求
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return HttpClientUtils.doPostJson(WEB_HOOK_URL, JSON.toJSONString(feishuMsgDTO), null);
            //return HttpRequest.post(WEB_HOOK_URL).body(JSON.toJSONString(feishuMsgDTO), "application/json;charset=UTF-8").execute().body();
        }, ThreadExecutorPool.msgThreadPoolExecutor).whenComplete((result, ex) -> {
            if (Objects.nonNull(ex)) {
                log.error("[FeishuMsgUtils][sendMessage] error is ", ex);
            }
        });
        /*try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

    public static void sendCardMessage(String titleStr,String msg) {
        FeishuCardMsgDTO feishuCardMsgDTO = new FeishuCardMsgDTO();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        feishuCardMsgDTO.setTimestamp(timestamp);
        try {
            feishuCardMsgDTO.setSign(genSign());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("[FeishuMsgUtils][sendCardMessage]genSign error is ",e);
        }
        FeishuCardMsgDTO.Card.Elements.Text text = new FeishuCardMsgDTO.Card.Elements.Text();
        text.setContent(msg);
        FeishuCardMsgDTO.Card.Elements element = new FeishuCardMsgDTO.Card.Elements();
        element.setText(text);

        FeishuCardMsgDTO.Card.Header.Title title = new FeishuCardMsgDTO.Card.Header.Title();
        title.setContent(titleStr);
        FeishuCardMsgDTO.Card.Header header = new FeishuCardMsgDTO.Card.Header();
        header.setTitle(title);

        FeishuCardMsgDTO.Card card = new FeishuCardMsgDTO.Card();
        card.setElements(Lists.newArrayList(element));
        card.setHeader(header);

        feishuCardMsgDTO.setCard(card);
        //发送post请求
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return HttpClientUtils.doPostJson(WEB_HOOK_URL, JSON.toJSONString(feishuCardMsgDTO), null);
            //return HttpRequest.post(WEB_HOOK_URL).body(JSON.toJSONString(feishuMsgDTO), "application/json;charset=UTF-8").execute().body();
        }, ThreadExecutorPool.msgThreadPoolExecutor).whenComplete((result, ex) -> {
            if (Objects.nonNull(ex)) {
                log.error("[FeishuMsgUtils][sendCardMessage] error is ", ex);
            }
        });
        /*try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

    public static void sendPostMessage(String titleStr,String msg) {
        FeishuPostMsgDTO feishuPostMsgDTO = new FeishuPostMsgDTO();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        feishuPostMsgDTO.setTimestamp(timestamp);
        try {
            feishuPostMsgDTO.setSign(genSign());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("[FeishuMsgUtils][sendPostMessage]genSign error is ",e);
        }
        FeishuPostMsgDTO.OutContent.Post.Zh_cn.Content content = new FeishuPostMsgDTO.OutContent.Post.Zh_cn.Content();
        // text  a  at
        content.setTag("text");
        content.setText(msg);
        ArrayList<FeishuPostMsgDTO.OutContent.Post.Zh_cn.Content> contentArrayList = Lists.newArrayList(content);

        FeishuPostMsgDTO.OutContent.Post.Zh_cn zh_cn = new FeishuPostMsgDTO.OutContent.Post.Zh_cn();
        zh_cn.setTitle(titleStr);
        List<List<FeishuPostMsgDTO.OutContent.Post.Zh_cn.Content>> contentList = new ArrayList<>();
        contentList.add(contentArrayList);
        zh_cn.setContent(contentList);

        FeishuPostMsgDTO.OutContent.Post post = new FeishuPostMsgDTO.OutContent.Post();
        post.setZh_cn(zh_cn);

        FeishuPostMsgDTO.OutContent outContent = new FeishuPostMsgDTO.OutContent();
        outContent.setPost(post);

        feishuPostMsgDTO.setContent(outContent);
        //发送post请求
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return HttpClientUtils.doPostJson(WEB_HOOK_URL, JSON.toJSONString(feishuPostMsgDTO), null);
            //return HttpRequest.post(WEB_HOOK_URL).body(JSON.toJSONString(feishuMsgDTO), "application/json;charset=UTF-8").execute().body();
        }, ThreadExecutorPool.msgThreadPoolExecutor).whenComplete((result, ex) -> {
            if (Objects.nonNull(ex)) {
                log.error("[FeishuMsgUtils][sendPostMessage] error is ", ex);
            }
        });
       /* try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

    private static String genSign() throws NoSuchAlgorithmException, InvalidKeyException {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        //把timestamp+"\n"+密钥当做签名字符串
        String stringToSign = timestamp + "\n" + SECRET;

        //使用HmacSHA256算法计算签名
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(stringToSign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(new byte[]{});
        return new String(Base64.encodeBase64(signData));
    }
}
