package com.zw.cloud.tools.controller;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.common.utils.DingTalkUtils;
import com.zw.cloud.tools.entity.Fc;
import com.zw.cloud.tools.entity.dto.FcResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengwei
 * @date 2022/4/12 18:53
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    //http://127.0.0.1:9040/test
    public List<Map<String,Object>> queryList(){
        Map<String,Object> jsonObject = new HashMap<>();
        jsonObject.put("title","新冠疫情");
        jsonObject.put("time", LocalDateTime.now());
        jsonObject.put("url","/images/nick.jpg");

        Map<String,Object> jsonObject2 = new HashMap<>();
        jsonObject2.put("title","商改");
        jsonObject2.put("time",new Date());
        jsonObject2.put("url","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091108%2Fvmipqy4caz4vmipqy4caz4.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650772948&t=c175d48d866995d3474b52bd4547972a");

        Map<String,Object> jsonObject3 = new HashMap<>();
        jsonObject3.put("title","美图");
        jsonObject3.put("time","2022-04-01");
        jsonObject3.put("url","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmx12%2F0F420115037%2F200F4115037-11.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638372678&t=004b8405f7b0780a5226dbb99b04c924");
        return Lists.newArrayList(jsonObject, jsonObject2, jsonObject3);
    }

    @GetMapping(value = {"/sendMsg/{count}"})
    //http://localhost:9040/test/sendMsg/5
    public void sendMsg(@PathVariable("count") Integer count) {
        String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&pageNo=1&pageSize=30&systemType=PC";
        String result = restTemplate.getForObject(url, String.class);
        FcResultDTO fcResultDTO = JSON.parseObject(result, FcResultDTO.class);
        if (Objects.isNull(fcResultDTO) || CollectionUtils.isEmpty(fcResultDTO.getResult())) {
            return;
        }
        List<FcResultDTO.FcDTO> fcDTOList = fcResultDTO.getResult();

        List<Fc> fcList = new ArrayList<>(30);
        for (FcResultDTO.FcDTO fcDTO : fcDTOList) {
            Fc.FcBuilder builder = Fc.builder();
            String[] split = fcDTO.getRed().split(",");
            builder.id(Integer.parseInt(fcDTO.getCode())).one(Integer.parseInt(split[0]))
                    .two(Integer.parseInt(split[1])).three(Integer.parseInt(split[2]))
                    .four(Integer.parseInt(split[3])).five(Integer.parseInt(split[4]))
                    .six(Integer.parseInt(split[5])).seven(Integer.parseInt(fcDTO.getBlue()));
            Fc build = builder.build();
            fcList.add(build);
        }
        fcList = fcList.stream().sorted(Comparator.comparing(Fc::getId).reversed()).collect(Collectors.toList());
        fcList.subList(0,count);
        randomNum(fcList);
    }

    private Map<String,Object> randomNum(List<Fc> fcList) {
        List<Integer> exitList = new ArrayList<>(60);
        List<Integer> blueList = new ArrayList<>();
        fcList.forEach(fc -> {
            exitList.add(fc.getOne());
            exitList.add(fc.getTwo());
            exitList.add(fc.getThree());
            exitList.add(fc.getFour());
            exitList.add(fc.getFive());
            exitList.add(fc.getSix());
            blueList.add(fc.getSeven());
        });
        Map<String,Object> result = new HashMap<>();
        Set<Integer> resultSet = new HashSet<>(8);
        while (resultSet.size() < 6) {
            Random random = new Random();
            int index = random.nextInt(exitList.size());
            resultSet.add(exitList.get(index));
        }
        List<Integer> resultList = resultSet.stream().sorted(Comparator.comparingInt(s -> s)).collect(Collectors.toList());
        result.put("resultList", resultList);
        result.put("blueList",blueList.subList(0,10));
        Fc laster = fcList.get(0);
        StringBuilder msg = new StringBuilder("最新一期: \n\n");
        msg.append(String.format("%02d",laster.getOne())).append(" ")
                .append(String.format("%02d",laster.getTwo())).append(" ")
                .append(String.format("%02d",laster.getThree())).append(" ")
                .append(String.format("%02d",laster.getFour())).append(" ")
                .append(String.format("%02d",laster.getFive())).append(" ")
                .append(String.format("%02d",laster.getSix())).append(" ")
                .append(String.format("%02d",laster.getSeven())).append(" ")
                .append("\n\n")
                .append("<font color=#FF0000>").append(resultList.stream().map(s -> String.format("%02d",s)).collect(Collectors.joining(" ")))
                .append("</font>").append("\n\n")
                .append("<font color=#0000FF>").append(blueList.subList(0,10).stream().map(String::valueOf).collect(Collectors.joining(" ")))
                .append("</font>").append("\n\n")
                .append(DateTimeUtils.parse2Str(laster.getUpdateTime(),DateTimeUtils.DATE_TIME_PATTERN));

        DingTalkUtils.sendDingTalkMsgWithSign("FC",msg.toString());
        return result;
    }
}
