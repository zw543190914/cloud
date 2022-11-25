package com.zw.cloud.tools.controller.location;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zw.cloud.common.entity.dto.LocationVO;
import com.zw.cloud.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = {"/queryLocation","/queryLocation/{city}"})
    //http://localhost:9040/location/queryLocation
    public List<LocationVO> queryLocation(@PathVariable(required = false) String city) {
        String keywords = StringUtils.isBlank(city) ? "中国" : city;
        JSONObject result = restTemplate.getForObject("https://restapi.amap.com/v3/config/district?keywords=" + keywords + "&subdistrict=1&key=de60322325f689139040aae38ecba0d7",
                JSONObject.class);
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
