package com.zw.cloud.netty.web.controller.tc;


import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import com.zw.cloud.global.response.wrapper.entity.WebResult;
import com.zw.cloud.netty.entity.dto.QueryDTO;
import com.zw.cloud.netty.entity.vo.TcResultVO;
import com.zw.cloud.netty.web.entity.tc.Tc;
import com.zw.cloud.netty.web.service.api.tc.ITcService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/tc")
public class TcController {

    @Autowired
    private ITcService tcService;

    @GetMapping("queryById")
    public Tc queryById(@RequestParam Integer id) {
        return tcService.getById(id);
    }

    @PostMapping("pageQuery")
    //http://localhost:18092/tc/pageQuery
    public WebResult pageQuery(@RequestBody QueryDTO queryDTO) {
        //int offset = (queryDTO.getPageNo() - 1) * queryDTO.getPageSize();
        IPage<Tc> page = new Page<>(queryDTO.getPageNo(), queryDTO.getPageSize());
        LambdaQueryWrapper<Tc> queryWrapper = new LambdaQueryWrapper<Tc>().orderByDesc(Tc::getId);

        IPage<Tc> pageResult = tcService.page(page, queryWrapper);
        List<Tc> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return WebResult.success().withData(pageResult);
        }
        List<Tc> sorted = records.stream().sorted(Comparator.comparingInt(Tc::getId).reversed()).collect(Collectors.toList());
        pageResult.setRecords(sorted);
        return WebResult.success().withData(pageResult);
    }

    @GetMapping("/add")
    //http://localhost:18092/tc/add
    public void add(){
        for (int i = 1; i <= 10; i++) {
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
            list.forEach(valueData -> {
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
                tc.setUpdateTime(new Date());
                tcService.save(tc);
            });
        }

    }
}

