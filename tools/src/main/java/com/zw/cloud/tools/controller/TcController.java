package com.zw.cloud.tools.controller;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.utils.HttpClientUtils;
import com.zw.cloud.tools.dao.TcMapper;
import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.modle.vo.TcResultVO;
import com.zw.cloud.tools.service.TcService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * (Tc)表控制层
 *
 * @author makejava
 * @since 2021-08-29 21:10:20
 */
@RestController
@RequestMapping("tc")
public class TcController {
    /**
     * 服务对象
     */
    @Resource
    private TcService tcService;

    @Resource
    private TcMapper tcMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Tc selectOne(Integer id) {
        return this.tcService.queryById(id);
    }

    @GetMapping("/add")
    //http://localhost:9040/tc/add
    public void add(){
        for (int i = 1; i <= 73; i++) {
            String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo="+ i;
            String result = HttpClientUtils.doGet(url, null, null);
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
            tcMapper.batchInsert(tcList);
        }

    }

}