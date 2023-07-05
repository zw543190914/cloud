package com.zw.cloud.mybatis.plus.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Sets;
import com.zw.cloud.mybatis.plus.entity.Tc;
import com.zw.cloud.mybatis.plus.entity.vo.TcResultVO;
import com.zw.cloud.mybatis.plus.service.api.ITcService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/tc")
public class TcController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ITcService tcService;

    @GetMapping
    //http://localhost:8082/tc
    public void add(){
        for (int i = 1; i <= 3; i++) {
            String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=" + i;
            String result = restTemplate.getForObject(url, String.class);
            TcResultVO tcResultVO = JSON.parseObject(result, TcResultVO.class);
            TcResultVO.ResultValue value = tcResultVO.getValue();
            if (Objects.isNull(value)) {
                return;
            }
            List<TcResultVO.ValueData> list = value.getList();
            if (CollectionUtils.isEmpty(list)) {
                return;
            }
            for (TcResultVO.ValueData valueData : list) {
                String lotteryDrawResult = valueData.getLotteryDrawResult();
                String[] values = lotteryDrawResult.split(" ");
                Tc tc = new Tc();
                tc.setId(valueData.getLotteryDrawNum());
                tc.setOne(Integer.valueOf(values[0]));
                tc.setTwo(Integer.valueOf(values[1]));
                tc.setThree(Integer.valueOf(values[2]));
                tc.setFour(Integer.valueOf(values[3]));
                tc.setFive(Integer.valueOf(values[4]));
                tc.setSix(Integer.valueOf(values[5]));
                tc.setSeven(Integer.valueOf(values[6]));
                try {
                    tcService.save(tc);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            //tcService.saveBatch(tcList, tcList.size());
        }
    }

    @GetMapping("/queryFcList/{count}")
    //http://localhost:8080/tc/queryFcList/5
    public Map<String,List<Integer>> queryFcList(@PathVariable("count") Integer count) {
        LambdaQueryWrapper<Tc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Tc::getId).last("limit " + count);
        List<Tc> tcList = tcService.list(queryWrapper);
        Set<Integer> codeSet = new HashSet<>(30);
        tcList.forEach(tc -> {
            codeSet.add(tc.getOne());
            codeSet.add(tc.getTwo());
            codeSet.add(tc.getThree());
            codeSet.add(tc.getFour());
            codeSet.add(tc.getFive());
        });
        Set<Integer> allCodeSet = new HashSet<>(35);
        for (int i = 1; i <= 35; i++) {
            allCodeSet.add(i);
        }
        List<Integer> exitList = codeSet.stream().sorted().collect(Collectors.toList());
        Sets.SetView<Integer> difference = Sets.difference(allCodeSet, codeSet);
        Set<Integer> blueSet = tcList.stream().flatMap(tc -> Stream.of(tc.getSix(), tc.getSeven())).collect(Collectors.toSet());
        List<Integer> notExitList = difference.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        List<Integer> blueList = blueSet.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        System.out.println("已存在:" + JSON.toJSONString(exitList));
        System.out.println("不存在:" + JSON.toJSONString(notExitList));
        System.out.println(JSON.toJSONString(blueList));
        Map<String,List<Integer>> result = new HashMap<>();
        result.put("exitList",exitList);
        result.put("notExitList",notExitList);
        result.put("blueList",blueList);
        return result;
    }


}
