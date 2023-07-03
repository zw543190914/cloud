package com.zw.cloud.common.gaode;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.zw.cloud.common.entity.vo.LocationVO;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class LocationUtils {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(queryLocation(null)));
    }

    public static List<LocationVO> queryLocation(String city) {
        String keywords = StringUtils.isBlank(city) ? "中国" : city;
        String response = HttpClientUtils.doGet("https://restapi.amap.com/v3/config/district?key=de60322325f689139040aae38ecba0d7&subdistrict=1&keywords=" + keywords, null, null);
        JSONObject result = JSON.parseObject(response, JSONObject.class);
        if (Objects.isNull(result) || !StringUtils.equals("1",String.valueOf(result.get("status")))) {
            throw new BizException("服务出错");
        }
        Object districts = result.get("districts");
        if (Objects.isNull(districts)) {
            return List.of();
        }
        return JSON.parseObject(JSON.toJSONString(districts),new TypeReference<List<LocationVO>>() {});
    }
}
