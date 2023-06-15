package com.zw.cloud.tools.controller;

import com.zw.cloud.common.entity.vo.LocationCodeVO;
import com.zw.cloud.common.entity.vo.WeatherDTO;
import com.zw.cloud.common.entity.vo.WeatherLiveVO;
import com.zw.cloud.common.gaode.IpLocationUtils;
import com.zw.cloud.common.gaode.WeatherUtils;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.tools.utils.IpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/util")
public class UtilController {

    @GetMapping("/getWeatherByGaoDe")
    //http://localhost:9040/util/getWeatherByGaoDe
    public WebResult<WeatherLiveVO> getWeatherByGaoDe(HttpServletRequest request) {
        LocationCodeVO ipLocation = IpLocationUtils.getIpLocation(IpUtils.getUserIp(request));
        return WebResult.success(WeatherUtils.getLiveWeatherByGaoDe(ipLocation.getAdcode()));
    }

    @GetMapping("/getWeatherByXinZhi")
    //http://localhost:9040/util/getWeatherByXinZhi
    public WebResult<WeatherDTO> getWeatherByXinZhi(HttpServletRequest request) {
        LocationCodeVO ipLocation = IpLocationUtils.getIpLocation(IpUtils.getUserIp(request));
        return WebResult.success(WeatherUtils.getWeatherByXinZhi(ipLocation.getCity()));
    }

}
