package com.zw.cloud.mybatis.plus.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.mybatis.plus.entity.Fc;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @GetMapping("/add")
    //http://localhost:8080/tc/add
    public void add(){
        for (int i = 1; i <= 77; i++) {
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
            List<Tc> tcList = list.stream().map(valueData -> {
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
                return tc;
            }).collect(Collectors.toList());
            tcService.saveBatch(tcList, tcList.size());
        }
    }

    @GetMapping("/queryFcList/{count}")
    //http://localhost:8080/fc/queryFcList/5
    public List<Tc> queryFcList(@PathVariable("count") Integer count) {
        LambdaQueryWrapper<Tc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Tc::getId).last("limit " + count);
        return tcService.list(queryWrapper);
    }


}
