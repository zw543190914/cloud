package com.zw.cloud.netty.client.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 消息处理工具类
 */
@Slf4j
public class MsgHandlerUtil {

    /**
     * 获取本机ip地址
     *
     * @return IP地址
     */
    public static String getLocalIp() {
        String ip = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            if (address != null) {
                ip = address.getHostAddress();
                //logger.info("主机名：{}，IP地址：{}", address.getHostName(), address.getHostAddress());
            }
        } catch (UnknownHostException e) {
            log.error("获取本机IP地址异常", e);
        }

        if (StringUtils.isEmpty(ip)) {
            log.error("获取本机IP失败，正常执行本次任务");
        }
        return ip;
    }
}
