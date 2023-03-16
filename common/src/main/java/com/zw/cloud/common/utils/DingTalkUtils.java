package com.zw.cloud.common.utils;

import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DingTalkUtils {

    private static String br = " \n\n ";

    public static void main(String[] args) throws Exception {
        sendDingTalkMsg("7b66a82f1620672a1f5b2229d536d41cd978fb9f949141df8b40cd3b8bc9dd54",DingTalkUtils.class,"sendDingTalkMsg",null,new RuntimeException("running exception..."));
    }

    public static void sendDingTalkMsg(String token,Class clazz,String methodName,Object args,Exception e) throws Exception{
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
        OapiRobotSendResponse response = client.execute(request);
        log.info("[DingTalkUtils][sendDingTalkMsg] response is {}", JSONUtil.toJsonStr(response));
    }

    public static void sendDingTalkMsg(String token,String userId,String msg) throws Exception{
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
        OapiRobotSendResponse response = client.execute(request);
        log.info("[DingTalkUtils][sendDingTalkMsg] response is {}", JSONUtil.toJsonStr(response));
    }

    public static void sendDingTalkChatMsg(String msg) throws Exception {
        String token = "";
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + token);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("chatGPT");
        markdown.setText("#### chat\n" +
                "> "+ msg + "\n");
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);
        log.info("[DingTalkUtils][sendDingTalkChatMsg] response is {}", JSONUtil.toJsonStr(response));
    }
}
