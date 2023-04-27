package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.entity.dto.FeishuMsgDTO;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * https://open.feishu.cn/document/ukTMukTMukTM/ucTM5YjL3ETO24yNxkjN
 */
public class FeishuMsgUtils {

    //这里就是刚才拿到的Webhook的值
    public static final String WEB_HOOK_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/819420bb-303a-4e26-8dc9-1850d86ebc2f";

    public static final String SECRET = "ADUmsMcaBv5eQj7lakis8e";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(sendMessage("这里是消息内容"));
    }

    public static String sendMessage(String msg) throws InvalidKeyException, NoSuchAlgorithmException {
        FeishuMsgDTO feishuMsgDTO = new FeishuMsgDTO();
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        feishuMsgDTO.setTimestamp(timestamp);
        feishuMsgDTO.setSign(genSign());
        feishuMsgDTO.setMsg_type("text");
        FeishuMsgDTO.MsgContent msgContent = new FeishuMsgDTO.MsgContent();
        msgContent.setText(msg);
        feishuMsgDTO.setContent(msgContent);
        //发送post请求
        return HttpClientUtils.doPostJson(WEB_HOOK_URL,JSON.toJSONString(feishuMsgDTO),null);
        //return HttpRequest.post(WEB_HOOK_URL).body(JSON.toJSONString(feishuMsgDTO), "application/json;charset=UTF-8").execute().body();
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
