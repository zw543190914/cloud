package com.zw.cloud.tools.controller;

import cn.hutool.json.JSONUtil;
import com.zw.cloud.tools.modle.vo.TcResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest/template")
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    //http://localhost:9040/rest/template
    public void testGet(){
        String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=1";
        TcResultVO resultVO = restTemplate.getForObject(url, TcResultVO.class);
        System.out.println(JSONUtil.toJsonStr(resultVO));
    }
}
