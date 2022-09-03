package com.zw.cloud.tools.longpolling;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.PrintWriter;

import static com.zw.cloud.tools.longpolling.NoticeListener.SUB_HTTP_CLIENT_MAP;

@WebListener
@Slf4j
public class AppAsyncListener implements AsyncListener {

    private String deviceId;

    public AppAsyncListener(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void onComplete(AsyncEvent asyncEvent) {
        log.info("[AppAsyncListener][onComplete]");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) {
        log.info("[AppAsyncListener][onError] is ",asyncEvent.getThrowable());
        AsyncContext asyncContext = asyncEvent.getAsyncContext();
        try {
            ServletResponse response = asyncEvent.getAsyncContext().getResponse();
            PrintWriter writer = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("data","longPolling error " + asyncEvent.getThrowable());
            result.put("status",501);
            writer.write(result.toJSONString());
            writer.flush();
        } catch (Exception e) {
            log.error("[AppAsyncListener][onError] error is ",e);
        } finally {
            SUB_HTTP_CLIENT_MAP.remove(deviceId);
            asyncContext.complete();
        }
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) {
        log.info("[AppAsyncListener][onStartAsync]");
        //we can log the event here
    }


    @Override
    public void onTimeout(AsyncEvent asyncEvent)  {
        AsyncContext asyncContext = asyncEvent.getAsyncContext();
        try {
            ServletResponse response = asyncEvent.getAsyncContext().getResponse();
            PrintWriter writer = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("data","longPolling timeout");
            result.put("status",201);
            writer.write(result.toJSONString());
            writer.flush();
        } catch (Exception e) {
            log.error("[AppAsyncListener][onTimeout] error is ",e);
        } finally {
            SUB_HTTP_CLIENT_MAP.remove(deviceId);
            asyncContext.complete();
        }

    }
}
