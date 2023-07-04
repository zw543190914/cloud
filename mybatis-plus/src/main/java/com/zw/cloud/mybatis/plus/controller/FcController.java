package com.zw.cloud.mybatis.plus.controller;



import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.mybatis.plus.entity.Fc;
import com.zw.cloud.mybatis.plus.entity.Tc;
import com.zw.cloud.mybatis.plus.entity.dto.FcDTO;
import com.zw.cloud.mybatis.plus.entity.dto.FcResultDTO;
import com.zw.cloud.mybatis.plus.service.api.IFcService;
import com.zw.cloud.mybatis.plus.service.api.ITcService;
import com.zw.cloud.mybatis.plus.util.BeanUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 体彩 前端控制器
 * </p>
 *
 * @author zw
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/fc")
public class FcController {

    @Autowired
    private IFcService fcService;
    @Autowired
    private ITcService tcService;
    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    //http://localhost:8082/fc/insertFcList
    public void insertFcList() {

        String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=100&issueStart=&issueEnd=&dayStart=&dayEnd=&pageNo=1&pageSize=30&systemType=PC";
        String result = restTemplate.getForObject(url, String.class);
        FcResultDTO fcResultDTO = JSON.parseObject(result, FcResultDTO.class);
        if (Objects.isNull(fcResultDTO) || CollectionUtils.isEmpty(fcResultDTO.getResult())) {
            return;
        }
        List<FcResultDTO.FcDTO> fcDTOList = fcResultDTO.getResult();
        for (FcResultDTO.FcDTO fcDTO : fcDTOList) {
            Fc.FcBuilder builder = Fc.builder();
            String[] split = fcDTO.getRed().split(",");
            builder.id(Integer.parseInt(fcDTO.getCode())).one(Integer.parseInt(split[0]))
                    .two(Integer.parseInt(split[1])).three(Integer.parseInt(split[2]))
                    .four(Integer.parseInt(split[3])).five(Integer.parseInt(split[4]))
                    .six(Integer.parseInt(split[5])).seven(Integer.parseInt(fcDTO.getBlue()));
            Fc build = builder.build();
            try {
                fcService.save(build);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

    }

    @GetMapping("/queryFcList/{count}")
    //http://localhost:8080/fc/queryFcList/50
    public List<FcDTO> queryFcList(@PathVariable("count") Integer count) {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Fc::getId).last("limit " + count);
        List<Fc> list = fcService.list(queryWrapper);
        return list.stream().map(fc -> {
            FcDTO dto = new FcDTO();
            dto.setOne(fc.getOne());
            dto.setTwo(fc.getTwo());
            dto.setThree(fc.getThree());
            dto.setFour(fc.getFour());
            dto.setFive(fc.getFive());
            dto.setSix(fc.getSix());
            dto.setSeven(fc.getSeven());
            dto.setCount(fc.getOne() + fc.getTwo() + fc.getThree() + fc.getFour() + fc.getFive());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/queryLastCountFcList/{count}")
    //http://localhost:8080/fc/queryLastCountFcList/5
    public Map<String,List<Integer>> queryMaxCountFcList(@PathVariable("count") Integer count) {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Fc::getId).last("limit " + count);
        List<Fc> fcList = fcService.list(queryWrapper);
        Set<Integer> codeSet = new HashSet<>(30);
        fcList.forEach(tc -> {
            codeSet.add(tc.getOne());
            codeSet.add(tc.getTwo());
            codeSet.add(tc.getThree());
            codeSet.add(tc.getFour());
            codeSet.add(tc.getFive());
            codeSet.add(tc.getSix());
        });
        Set<Integer> blueSet = fcList.stream().map(Fc::getSeven).collect(Collectors.toSet());
        List<Integer> list = codeSet.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        List<Integer> blueList = blueSet.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(blueList));
        Map<String,List<Integer>> result = new HashMap<>();
        result.put("list",list);
        result.put("blueList",blueList);
        return result;
    }

    @GetMapping("/queryFcCountList/{count}/{limit}")
    //http://localhost:8080/fc/queryFcCountList/5/1
    public Map<Integer,Integer> queryFcCountList(@PathVariable("count") Integer count,@PathVariable("limit") Integer limit) {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Fc::getId).last("limit " + count);
        List<Fc> fcList = fcService.list(queryWrapper);
        List<Integer> codeList = new ArrayList<>(count * 6);
        List<Integer> blueList = new ArrayList<>(count);
        fcList.forEach(fc -> {
            codeList.add(fc.getOne());
            codeList.add(fc.getTwo());
            codeList.add(fc.getThree());
            codeList.add(fc.getFour());
            codeList.add(fc.getFive());
            codeList.add(fc.getSix());
            blueList.add(fc.getSeven());
            //fc.getOne() + fc.getTwo() + fc.getThree() + fc.getFour() + fc.getFive() + fc.getFive() + fc.getSeven()
        });
        // k->数值，v->出现次数
        Map<Integer,Integer> map = new TreeMap<>();
        for (Integer value : codeList) {
            if (Objects.isNull(value)) {
                continue;
            }
            int intValue = value;
            Integer sum = map.get(intValue);
            if (Objects.isNull(sum)) {
                map.put(intValue,1);
            } else {
                map.put(intValue,sum + 1);
            }
        }
        Map<Integer,Integer> result = new TreeMap<>();
        map.forEach((k,v) -> {
            if (v >= limit) {
                result.put(k,v);
            }
        });
        return result;
    }

    @GetMapping(value = {"/queryFcByCondition/{one}/{two}/{three}/{four}/{five}","/queryFcByCondition/{one}/{two}/{three}/{four}",
            "/queryFcByCondition/{one}/{two}/{three}","/queryFcByCondition/{one}/{two}"})
    //http://localhost:8080/fc/queryFcByCondition/4/5/10
    public List<Fc> queryFcByCondition(@PathVariable(value = "one",required = false) Integer one,
                                                   @PathVariable(value = "two",required = false) Integer two,
                                                   @PathVariable(value = "three",required = false) Integer three,
                                                   @PathVariable(value = "four",required = false) Integer four,
                                                   @PathVariable(value = "five",required = false) Integer five) {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(one),Fc::getOne,one)
                .eq(Objects.nonNull(two),Fc::getTwo,two)
                .eq(Objects.nonNull(three),Fc::getThree,three)
                .eq(Objects.nonNull(four),Fc::getFour,four)
                .eq(Objects.nonNull(five),Fc::getFive,five)
                .orderByDesc(Fc::getId);
        return fcService.list(queryWrapper);
    }

    @GetMapping("/export")
    //http://localhost:8082/fc/export
    public void export(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("fc.xlsx", String.valueOf(StandardCharsets.UTF_8)));
        //新建ExcelWriter
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(),Fc.class).build();

        WriteSheet sheet1 = EasyExcel.writerSheet(0, "fc").build();
        List<Fc> fcList = fcService.list(null);
        excelWriter.write(fcList, sheet1);

        WriteSheet sheet2 = EasyExcel.writerSheet(1, "tc").build();
        List<Tc> tcList = tcService.list(null);
        List<Fc> fcs = BeanUtil.copyList(tcList, Fc.class);
        excelWriter.write(fcs, sheet2);

        //关闭流
        excelWriter.finish();
    }
}
