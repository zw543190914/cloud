package com.zw.cloud.tools.mtop;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.base.ThreadContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.*;

@Service
public class MtopService {

    @Autowired
    private WebApplicationContext webApplicationConnect;
    @Autowired
    private ThreadContext threadContext;

    private static Map<String, HandlerMethod> methodCache = new HashMap<>(255);
    private static Map<String, Object> serviceCache = new HashMap<>(255);


    private Logger logger = LoggerFactory.getLogger(MtopService.class);

    @PostConstruct
    public void init() {
        Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationConnect,
                HandlerMapping.class, true, false);

        for (HandlerMapping handlerMapping : allRequestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                try {
                    for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
                        RequestMappingInfo requestMappingInfo = entry.getKey();
                        HandlerMethod handlerMethod = entry.getValue();
                        PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                        String uri = null;
                        if (CollectionUtils.isNotEmpty(patternsCondition.getPatterns())) {
                            uri = patternsCondition.getPatterns().iterator().next();
                        }
                        if (StringUtils.isNotBlank(uri)) {
                            if (uri.contains("/user/plus")) {
                                RequestMethodsRequestCondition methodsCondition = requestMappingInfo
                                        .getMethodsCondition();
                                Set<RequestMethod> methods = methodsCondition.getMethods();
                                uri = uri + "_" + methods.iterator().next();
                            }
                            methodCache.put(uri, handlerMethod);
                            serviceCache.put(uri, webApplicationConnect.getBean(handlerMethod.getBeanType()));
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                break;
            }
        }
        logger.info("init mtop uri beans done!");
        logger.info("serviceCache uri is " + serviceCache.keySet().toString());
    }

    public Object mtopAdaptor(String uri, List args, String workId, String workName) throws Exception {
        logger.info("[MtopService][mtopAdaptor]uri is {},args is {},workerId is {},workerName is {}", uri, JSON.toJSONString(args), workId, workName);
        HandlerMethod handlerMethod = methodCache.get(uri);
        Preconditions.checkArgument(!Objects.isNull(handlerMethod) || !Objects.isNull(serviceCache.get(uri)), "方法不存在，请核对uri");
        //logger.info("[mtopAdaptor]handlerMethod is {}",JsonUtils.toJsonString(handlerMethod));
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Preconditions.checkArgument(methodParameters.length == args.size(), "参数数量不正确，请核对参数");

        //写入用户信息
        threadContext.getWorkIdThreadLocal().set(workId);
        threadContext.getWorkNameThreadLocal().set(workName);

        Object value;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (CollectionUtils.isNotEmpty(args)) {
                Object[] argsWithType = new Object[args.size()];
                for (int i = 0; i < args.size(); i++) {
                    String paramJson = args.get(i) != null ? args.get(i).toString() : "";
                    try {
                        if (!"java.lang.String".equals(methodParameters[i].getParameterType().getName())) {
                            argsWithType[i] = JSON.parseObject(paramJson, methodParameters[i].getParameterType());
                        } else {
                            argsWithType[i] = paramJson;
                        }
                    } catch (Exception e) {
                        logger.info("[MtopService][mtopAdaptor] is error , e is " + e + " paramJson is  " + paramJson + " Type is" + methodParameters[i].getParameterType().getName());
                        throw new RuntimeException("参数转换失败，请核对参数");
                    }
                }

                value = ReflectionUtils.invokeMethod(handlerMethod.getMethod(), serviceCache.get(uri), argsWithType);

            } else {
                value = ReflectionUtils.invokeMethod(handlerMethod.getMethod(), serviceCache.get(uri));
            }
        } catch (Exception e) {
            logger.error("[MtopService][mtopAdaptor] is error : {}", e.getMessage(), e);
            String message = e.getMessage();
            if (e instanceof UndeclaredThrowableException) {
                Throwable cause = e.getCause();
                message = cause.getMessage();
            }
            resultMap.put("success", false);
            resultMap.put("errorMsg", message);
            return resultMap;
        }
        if (null == value) {
            resultMap.put("success", true);
            return resultMap;
        }
        if (value instanceof WebResult) {
            WebResult webResult = (WebResult) value;
            logger.info("[MtopService][mtopAdaptor] return webResult is {}", JSON.toJSONString(webResult));
            if (StringUtils.isNotBlank(webResult.getErrorMsg())) {
                resultMap.put("success", false);
                resultMap.put("errorMsg", webResult.getErrorMsg());
                return resultMap;
            }
            if (null == webResult.getData()) {
                resultMap.put("success", true);
                return resultMap;
            }
        }
        logger.info("[MtopService][mtopAdaptor] return value is {},uri is {},workId is {},workName is {}", JSON.toJSONString(value), uri, workId, workName);
        return value;
    }
}
