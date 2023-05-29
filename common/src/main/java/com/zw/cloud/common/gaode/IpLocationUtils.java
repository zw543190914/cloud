package com.zw.cloud.common.gaode;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.entity.vo.LocationCodeVO;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 高德地图
 * https://lbs.amap.com/api/webservice/guide/api/weatherinfo
 */
@Slf4j
public class IpLocationUtils {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(getIpLocation(null)));
    }

    public static LocationCodeVO getIpLocation(String ip) {
        Map<String,String> paramMap = new HashMap<>();
        if (StringUtils.isNoneBlank(ip)) {
            paramMap.put("ip",ip);
        }
        String response = HttpClientUtils.doGet("https://restapi.amap.com/v3/ip?key=de60322325f689139040aae38ecba0d7", paramMap, null);
        log.info("[IpLocationUtils][getIpLocation]ip is {},response is {}",ip,response);
        return JSON.parseObject(response,LocationCodeVO.class);
    }
}
