package com.zw.cloud.tools.controller;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zw.cloud.common.utils.http.HttpClientUtils;
import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.dao.TcMapper;
import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.entity.TcExample;
import com.zw.cloud.tools.entity.dto.QueryDTO;
import com.zw.cloud.tools.modle.vo.TcResultVO;
import com.zw.cloud.tools.service.api.TcService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
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
    @GetMapping("queryById")
    public Tc queryById(@RequestParam Integer id) {
        return this.tcService.queryById(id);
    }

    @PostMapping("pageQuery")
    //http://localhost:9040/tc/pageQuery
    public WebResult<PageInfo<Tc>> pageQuery(@RequestBody QueryDTO queryDTO) {
        //int offset = (queryDTO.getPageNo() - 1) * queryDTO.getPageSize();
        return WebResult.build(this.tcService.pageQuery(queryDTO));
    }

    @GetMapping("/add")
    //http://localhost:9040/tc/add
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
                tcMapper.insertSelective(tc);
            });
        }

    }



    @GetMapping("/query")
    //http://localhost:9040/tc/query
    public void query() throws Exception{
        TcExample example = new TcExample();
        example.setOrderByClause("id desc");
        PageHelper.startPage(1,3);
        List<Tc> tcList = tcMapper.selectByExample(example);
        List<Integer> code = new ArrayList<>(20);
        tcList.forEach(tc -> {
            code.add(tc.getOne());
            code.add(tc.getTwo());
            code.add(tc.getThree());
            code.add(tc.getFour());
            code.add(tc.getFive());
        });
        Collections.sort(code);
        List<Integer> codeList = code.stream().distinct().sorted(Comparator.comparingInt(s -> s)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(codeList));
        codeList.add(0);
        System.out.println("==================");
        for (int i = 0; i < 5; i++) {
            System.out.println(query(codeList));
            Thread.sleep(1000);
        }
    }

    private List<Integer> query(List<Integer> codeList) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            Random random = new Random();
            int i = random.nextInt(36);
            if (!codeList.contains(i)) {
                set.add(i);
            }
            if (set.size() > 4) {
                ArrayList<Integer> result = Lists.newArrayList(set);
                Collections.sort(result);
                return result;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            System.out.println(random.nextInt(36));
        }
    }
}