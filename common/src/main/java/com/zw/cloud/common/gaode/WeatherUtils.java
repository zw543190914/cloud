package com.zw.cloud.common.gaode;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.common.entity.vo.WeatherDTO;
import com.zw.cloud.common.entity.vo.WeatherLiveVO;
import com.zw.cloud.common.entity.vo.WeatherVO;
import com.zw.cloud.common.utils.DingTalkUtils;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WeatherUtils {
    private static String br = " \n\n ";

    public static void main(String[] args) {
        //System.out.println(JSON.toJSONString(getWeatherByGaoDe("310000")));
        System.out.println(JSON.toJSONString(getLiveWeatherByGaoDe("310000")));
        //System.out.println(JSON.toJSONString(getWeatherByXinZhi("上海")));
    }

    public static WeatherLiveVO getLiveWeatherByGaoDe(String adcode) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("city",adcode);
        String response = HttpClientUtils.doGet("https://restapi.amap.com/v3/weather/weatherInfo?key=de60322325f689139040aae38ecba0d7", paramMap, null);
        WeatherLiveVO weatherVO = JSON.parseObject(response, WeatherLiveVO.class);
        List<WeatherLiveVO.ForecastsDTO> lives = weatherVO.getLives();
        if (CollectionUtils.isEmpty(lives)) {
            return weatherVO;
        }
        WeatherLiveVO.ForecastsDTO forecastsDTO = weatherVO.getLives().get(0);
        StringBuffer msg = new StringBuffer("#### " + forecastsDTO.getCity() +" 天气预报\n");

            msg.append("> 实时天气:" + forecastsDTO.getWeather() + br)
                    .append("> 实时气温:" + forecastsDTO.getTemperature() + br)
                    .append("> 风  向:").append(forecastsDTO.getWinddirection()).append(br)
                    .append("> 风力级别:" + forecastsDTO.getWindpower() + br)
                    .append("> 空气湿度:" + forecastsDTO.getHumidity() + br)
                    .append("> ====================="+ br);

        msg.append("> 数据发布时间:" + forecastsDTO.getReporttime());
        DingTalkUtils.sendDingTalkMsgWithSign("天气预报","3a93469afdb2c38e22f0944e7f61a9b4d2e95a0138901d813ce6fe2c33dd1145","SECd51ae59f656260a2f859e4e149a54e120c8673a9aa789cf60f7ee20f09048a49",msg.toString());
        return weatherVO;
    }

    public static WeatherVO getWeatherByGaoDe(String adcode) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("extensions","all");
        if (StringUtils.isNoneBlank(adcode)) {
            paramMap.put("city",adcode);
        }
        String response = HttpClientUtils.doGet("https://restapi.amap.com/v3/weather/weatherInfo?key=de60322325f689139040aae38ecba0d7", paramMap, null);
        WeatherVO weatherVO = JSON.parseObject(response, WeatherVO.class);
        List<WeatherVO.ForecastsDTO> forecasts = weatherVO.getForecasts();
        if (CollectionUtils.isEmpty(forecasts)) {
            return weatherVO;
        }
        WeatherVO.ForecastsDTO forecastsDTO = weatherVO.getForecasts().get(0);
        StringBuffer msg = new StringBuffer("#### " + forecastsDTO.getCity() +" 天气预报\n");
        for (WeatherVO.ForecastsDTO.CastsDTO castsDTO : forecastsDTO.getCasts()) {
            msg.append("> 日期:" + castsDTO.getDate() + br)
                    .append("> 白天天气:" + castsDTO.getDayweather() + br)
                    .append("> 晚间天气:" + castsDTO.getNightweather() + br)
                    .append("> 白天温度:" + castsDTO.getDaytemp() + br)
                    .append("> 晚上温度:" + castsDTO.getNighttemp() + br)
                    .append("> 白天风向:" + castsDTO.getDaywind() + br)
                    .append("> 晚上风向:" + castsDTO.getNightwind() + br)
                    .append("> 白天风力:" + castsDTO.getDaypower() + br)
                    .append("> 晚上风力:" + castsDTO.getNightpower() + br)
                    .append("> ====================="+ br);
        }
        msg.append("> 数据发布时间:" + forecastsDTO.getReporttime());
        DingTalkUtils.sendDingTalkMsgWithSign("天气预报","3a93469afdb2c38e22f0944e7f61a9b4d2e95a0138901d813ce6fe2c33dd1145","SECd51ae59f656260a2f859e4e149a54e120c8673a9aa789cf60f7ee20f09048a49",msg.toString());
        return weatherVO;
    }

    /**
     * 心知天气
     * https://seniverse.yuque.com/hyper_data/datasets/wvfkgq
     */
    public static WeatherDTO getWeatherByXinZhi(String location) {
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("location",location);
        String response = HttpClientUtils.doGet("https://api.seniverse.com/v3/weather/daily.json?key=SFOJbR15C0UeGh7js&language=zh-Hans&unit=c&start=-1&days=5", paramMap, null);
        WeatherDTO weatherDTO = JSON.parseObject(response, WeatherDTO.class);
        log.info("[WeatherUtils][getWeatherByXinZhi] location is {},weatherDTO is {}",location,JSON.toJSONString(weatherDTO));
        StringBuffer msg = new StringBuffer("#### " + location +" 天气预报\n");
        WeatherDTO.ResultsDTO resultsDTO = weatherDTO.getResults().get(0);

        for (WeatherDTO.ResultsDTO.DailyDTO dailyDTO : resultsDTO.getDaily()) {
            msg.append("> 日期:" + dailyDTO.getDate() + br)
                    .append("> 白天天气:" + dailyDTO.getText_day() + br)
                    .append("> 晚间天气:" + dailyDTO.getText_night() + br)
                    .append("> 最高温度:" + dailyDTO.getHigh() + br)
                    .append("> 最低温度:" + dailyDTO.getLow() + br)
                    .append("> 降水量:" + dailyDTO.getRainfall() + br)
                    .append("> 降水概率:" + dailyDTO.getPrecip() + br)
                    .append("> 相对湿度:" + dailyDTO.getHumidity() + br)
                    .append("> 风  向:" + dailyDTO.getWind_direction() + br)
                    .append("> 风  速:" + dailyDTO.getWind_speed() + br)
                    .append("> 风力等级:" + dailyDTO.getWind_direction_degree() + br)
                    .append("> ====================="+ br);

        }
        msg.append("> 数据更新时间:" + resultsDTO.getLast_update());
        DingTalkUtils.sendDingTalkMsgWithSign("天气预报","3a93469afdb2c38e22f0944e7f61a9b4d2e95a0138901d813ce6fe2c33dd1145","SECd51ae59f656260a2f859e4e149a54e120c8673a9aa789cf60f7ee20f09048a49",msg.toString());
        return weatherDTO;
    }
}
