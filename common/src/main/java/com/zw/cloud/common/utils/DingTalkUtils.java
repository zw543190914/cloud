package com.zw.cloud.common.utils;

import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingTalkUtils {

    private static String br = " \n\n ";

    public static void main(String[] args) throws Exception {
        sendDingTalkMsg("505e45d4253e2a98976d15de6b98a56c8635ddcab9d8b42de029ebb462c72db6",DingTalkUtils.class,"sendDingTalkMsg",new RuntimeException("running exception..."));
    }

    public static void sendDingTalkMsg(String token,Class clazz,String methodName,Exception e) throws Exception{
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
                "> className is "+ clazz + br +
                "> methodName is "+ methodName + br +
                "> error is "+ e + br +
                //"> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
                //"> ![screenshot](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmx12%2F0F420115037%2F200F4115037-11.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638372678&t=004b8405f7b0780a5226dbb99b04c924)\n"  +
                "> ![screenshot](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2017-10-19%2F59e806207e3e2.jpg%3Fdown&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638372666&t=ea3288d53c577bba50562e8b82460812)\n"  +
                "> [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);
        log.info("[DingTalkUtils][sendDingTalkMsg] response is {}", JSONUtil.toJsonStr(response));
    }
}