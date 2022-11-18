package com.zw.cloud.tools.longpolling;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

import static com.zw.cloud.tools.longpolling.NoticeListener.SUB_HTTP_CLIENT_MAP;

@RequestMapping("/long-polling")
@RestController
public class LongPollingController {

    public static final String LONG_POLLING_HEADER = "Long-Pulling-Timeout";

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/queryChangeData")
    //http://localhost:9040/long-polling/queryChangeData?deviceId=8
    public void queryChangeData(@RequestParam(value = "deviceId") String deviceId, HttpServletRequest req){
        //开启异步支持
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        String headerTimeout = req.getHeader(LONG_POLLING_HEADER);
        long timeout;
        if (StringUtils.isBlank(headerTimeout)) {
            timeout = 30 * 1000;
        } else {
            timeout = Long.parseLong(headerTimeout);
        }
        AsyncContext asyncContext = req.startAsync();
        // 客户端超时之前返回
        asyncContext.setTimeout(timeout - 500);
        asyncContext.addListener(new AppAsyncListener(deviceId));
        SUB_HTTP_CLIENT_MAP.put(deviceId,asyncContext);
    }

    /**
     * 触发事件使用
     */
    @GetMapping("/trigger")
    //http://localhost:9040/long-polling/trigger?deviceId=8&changeTag=tag1
    public void trigger(@RequestParam(value = "deviceId") String deviceId,@RequestParam(value = "changeTag") String changeTag){
        applicationEventPublisher.publishEvent(new NoticeEvent(deviceId,changeTag));
    }

}
