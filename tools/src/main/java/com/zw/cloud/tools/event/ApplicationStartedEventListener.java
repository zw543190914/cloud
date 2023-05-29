package com.zw.cloud.tools.event;

import com.zw.cloud.common.utils.DingTalkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    Environment environment;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("[ApplicationStartedEvent][onApplicationEvent]......");
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>(methodMap.size());
        for (RequestMappingInfo info : methodMap.keySet()){
            PathPatternsRequestCondition pathPatternsCondition = info.getPathPatternsCondition();
            if (Objects.nonNull(pathPatternsCondition)) {
                Set<PathPattern> patterns = pathPatternsCondition.getPatterns();
                if (CollectionUtils.isEmpty(patterns)) {
                    continue;
                }
                urlList.addAll(patterns.stream().map(PathPattern::getPatternString).collect(Collectors.toList()));
            }

            // 获取全部请求方式
            /*Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
            System.out.println(Methods.toString());*/

            /*for (String url : urlSet){
                // 加上自己的域名和端口号，就可以直接调用
                urlList.add(url);
            }*/
        }
        String osName = environment.getProperty("os.name");
        if (StringUtils.isNotBlank(osName) && !osName.startsWith("Windows")) {
            StringBuffer msg = new StringBuffer("#### 接口url\n");
            for (String url : urlList) {
                msg.append("> 接口url:" + url + " \n\n ");
            }
            DingTalkUtils.sendDingTalkMsgWithSign("现有接口","3a93469afdb2c38e22f0944e7f61a9b4d2e95a0138901d813ce6fe2c33dd1145","SECd51ae59f656260a2f859e4e149a54e120c8673a9aa789cf60f7ee20f09048a49",msg.toString());
        }
        log.info("[ApplicationStartedEvent][onApplicationEvent]urlList is {}",urlList);
    }


}

