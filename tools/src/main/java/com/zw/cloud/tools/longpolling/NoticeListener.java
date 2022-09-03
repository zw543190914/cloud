package com.zw.cloud.tools.longpolling;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NoticeListener implements ApplicationListener<NoticeEvent> {


    public final static Map<String, AsyncContext> SUB_HTTP_CLIENT_MAP = new ConcurrentHashMap<>();


    @Override
    public void onApplicationEvent(NoticeEvent noticeEvent) {
        String deviceId = noticeEvent.getDeviceId();
        String changeTag = noticeEvent.getChangeTag();
        log.info("[NoticeListener][onApplicationEvent] deviceId is {},changeTag is {},start",deviceId,changeTag);
        AsyncContext asyncContext = SUB_HTTP_CLIENT_MAP.get(deviceId);
        if(Objects.isNull(asyncContext)){
            log.info("[NoticeListener][onApplicationEvent] deviceId is {},changeTag is {},asyncContext is null",deviceId,changeTag);
            return;
        }
        try {
            HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache,no-store");
            response.setStatus(HttpServletResponse.SC_OK);
            JSONObject result = new JSONObject();
            result.put(deviceId,changeTag);
            response.getWriter().print(result.toJSONString());
            asyncContext.complete();
            SUB_HTTP_CLIENT_MAP.remove(deviceId);
        } catch (Exception e) {
            log.error("[EventManager][onEvent]asyncContext is {} error is ", asyncContext,e);
        } finally {
            asyncContext.complete();
        }
    }
}

