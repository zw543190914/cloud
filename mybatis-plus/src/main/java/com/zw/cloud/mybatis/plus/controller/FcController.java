package com.zw.cloud.mybatis.plus.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.mybatis.plus.entity.Fc;
import com.zw.cloud.mybatis.plus.entity.Tc;
import com.zw.cloud.mybatis.plus.entity.dto.FcResultDTO;
import com.zw.cloud.mybatis.plus.service.api.IFcService;
import com.zw.cloud.mybatis.plus.service.api.ITcService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/insertFcList/{start}/{end}")
    //http://localhost:8080/fc/insertFcList/22093/22095
    public void insertFcList(@PathVariable("start") String start,@PathVariable("end") String end) {
        //String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart="+ dayStart +"&dayEnd=" + dayEnd;
        String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=" + start + "&issueEnd=" + end + "&dayStart=&dayEnd=";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("Accept","application/json, text/javascript, */*; q=0.01");
        uriVariables.put("Accept-Encoding","gzip, deflate");
        uriVariables.put("Accept-Language","zh-CN,zh;q=0.9");
        uriVariables.put("Connection","keep-alive");
        uriVariables.put("Cookie","HMF_CI=3859b88291680b8738a6027765d9d685e6bb77b035c00b8065e85a6628fbc8381fac338e9f474f312a9a66c7c4fb6e978aef6fcf3ba919cdda44a9597fed1c2155; 21_vq=18");
        uriVariables.put("Host","www.cwl.gov.cn");
        uriVariables.put("Referer","http://www.cwl.gov.cn/ygkj/wqkjgg/ssq/");
        uriVariables.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        uriVariables.put("X-Requested-With","XMLHttpRequest");
        String result = restTemplate.getForObject(url, String.class,uriVariables);
        FcResultDTO fcResultDTO = JSON.parseObject(result, FcResultDTO.class);
        System.out.println(JSON.toJSONString(fcResultDTO));
        if (Objects.isNull(fcResultDTO) || CollectionUtils.isEmpty(fcResultDTO.getResult())) {
            return;
        }
        List<FcResultDTO.FcDTO> fcDTOList = fcResultDTO.getResult();
        List<Fc> fcList = fcDTOList.stream().map(fcDTO -> {
            Fc.FcBuilder builder = Fc.builder();
            String[] split = fcDTO.getRed().split(",");
            builder.id(Integer.parseInt(fcDTO.getCode())).one(Integer.parseInt(split[0]))
                    .two(Integer.parseInt(split[1])).three(Integer.parseInt(split[2]))
                    .four(Integer.parseInt(split[3])).five(Integer.parseInt(split[4]))
                    .six(Integer.parseInt(split[5])).seven(Integer.parseInt(fcDTO.getBlue()));
            return builder.build();
        }).collect(Collectors.toList());
        fcService.saveBatch(fcList,fcList.size());
    }

    @GetMapping("/queryFcList/{count}")
    //http://localhost:8080/fc/queryFcList/5
    public List<Fc> queryFcList(@PathVariable("count") Integer count) {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Fc::getId).last("limit " + count);
        return fcService.list(queryWrapper);
    }

    @GetMapping("/queryMaxCountFcList/{count}")
    //http://localhost:8080/fc/queryMaxCountFcList/5
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
    //http://localhost:8080/fc/queryFcCountList/60/5
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

    @GetMapping("/export/{count}")
    //http://localhost:8080/fc/export/100
    public void export(@PathVariable("count") Integer count,HttpServletResponse response) throws Exception {
        LambdaQueryWrapper<Fc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Fc::getId).last("limit " + count);
        List<Fc> fcList = fcService.list(queryWrapper);
        String title = "cp.xlsx";

        // 单个sheet 导出
        //Workbook workbook = ExcelExportUtils.export(null, "发货通知单", 0, shipmentOrderDTOS, ShipmentOrderDTO.class, null);

        // https://blog.csdn.net/baidu_36821021/article/details/85216855
        ExportParams sheet1Params = new ExportParams();
        // 设置sheet得名称
        sheet1Params.setSheetName("fc");
        // 创建sheet1使用得map
        Map<String, Object> sheet1ExportMap = new HashMap<>();
        // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
        sheet1ExportMap.put("title", sheet1Params);
        // 模版导出对应得实体类型
        sheet1ExportMap.put("entity", Fc.class);
        // sheet中要填充得数据
        sheet1ExportMap.put("data", fcList);

        LambdaQueryWrapper<Tc> tcQueryWrapper = new LambdaQueryWrapper<>();
        tcQueryWrapper.orderByDesc(Tc::getId).last("limit " + count);
        List<Tc> tcList = tcService.list(tcQueryWrapper);
        ExportParams sheet2Params = new ExportParams();
        sheet2Params.setSheetName("tc");
        // 创建sheet2使用得map
        Map<String, Object> sheet2ExportMap = new HashMap<>();
        sheet2ExportMap.put("title", sheet2Params);
        sheet2ExportMap.put("entity", Tc.class);
        sheet2ExportMap.put("data", tcList);

        // 将sheet1、sheet2、sheet3使用得map进行包装
        List<Map<String, Object>> sheetsList = new ArrayList<>();
        sheetsList.add(sheet1ExportMap);
        sheetsList.add(sheet2ExportMap);

        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(title, String.valueOf(StandardCharsets.UTF_8)));
        workbook.write(response.getOutputStream());
    }


}
