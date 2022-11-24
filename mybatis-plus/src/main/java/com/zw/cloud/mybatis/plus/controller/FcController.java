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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/insertFcList")
    //http://localhost:8080/fc/insertFcList
    public void insertFcList() {
        String url = "http://www.cwl.gov.cn/cwl_admin/front/cwlkj/search/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=2022133&issueEnd=2022134&dayStart=&dayEnd=";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept-Encoding","gzip, deflate");
        requestHeaders.add("Connection","keep-alive");
        requestHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
        // cookie设置
        List<String> cookies = new ArrayList<>();
        cookies.add("HMF_CI=ff631df18ddf701f100c6efc63f9358c20b6b1a87549cb7d8b29d5b232802936152cd60039aa71113334c365d688875ce5d95f14bbb6a2683086a3a8f6c103e902");
        cookies.add("21_vq=73");
        requestHeaders.put(HttpHeaders.COOKIE, cookies);
        //此处加编码格式转换
        //restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(requestHeaders), String.class);
        System.out.println(entity.getBody());
        FcResultDTO fcResultDTO = JSON.parseObject(String.valueOf(entity.getBody()), FcResultDTO.class);
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

    @GetMapping("/insertFc")
    //http://localhost:8080/fc/insertFc
    public void insertFc() {
        FcResultDTO fcResultDTO = JSON.parseObject(fc(), FcResultDTO.class);
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

    @GetMapping("/export/{count}")
    //http://localhost:8080/fc/export/200
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

    private String fc() {
        return "{\n" +
                "    \"state\": 0,\n" +
                "    \"message\": \"查询成功\",\n" +
                "    \"pageCount\": 1,\n" +
                "    \"countNum\": 18,\n" +
                "    \"Tflag\": 1,\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022126\",\n" +
                "            \"detailsLink\": \"/c/2022/11/03/520868.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/11/03/520867.shtml\",\n" +
                "            \"date\": \"2022-11-03(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"01,13,15,17,26,33\",\n" +
                "            \"blue\": \"13\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"436137344\",\n" +
                "            \"poolmoney\": \"1807038683\",\n" +
                "            \"content\": \"内蒙古1注,浙江1注,四川2注,陕西2注,共6注。其中一等奖特别奖为：浙江1注,四川2注,陕西2注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"zj1\": \"5\",\n" +
                "            \"mj1\": \"4000000\",\n" +
                "            \"zj6\": \"6612435\",\n" +
                "            \"mj6\": \"5\",\n" +
                "            \"msg\": \"下期一等奖特别奖派奖金额：20000000元。\\n下期六等奖翻番奖奖金余额：724010720元。\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"6\",\n" +
                "                    \"typemoney\": \"9507372\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"151\",\n" +
                "                    \"typemoney\": \"223876\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1313\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"63365\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1314147\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"9746531\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022125\",\n" +
                "            \"detailsLink\": \"/c/2022/11/01/520651.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/11/01/520654.shtml\",\n" +
                "            \"date\": \"2022-11-01(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"02,03,07,12,20,31\",\n" +
                "            \"blue\": \"16\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"429659904\",\n" +
                "            \"poolmoney\": \"1762667038\",\n" +
                "            \"content\": \"山西1注,山东2注,湖北1注,云南2注,陕西1注,共7注。其中一等奖特别奖为：山西1注,山东1注,云南2注,陕西1注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"zj1\": \"5\",\n" +
                "            \"mj1\": \"4000000\",\n" +
                "            \"zj6\": \"8585421\",\n" +
                "            \"mj6\": \"5\",\n" +
                "            \"msg\": \"下期一等奖特别奖派奖金额：20000000元。\\n下期六等奖翻番奖奖金余额：757072895元。\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"7\",\n" +
                "                    \"typemoney\": \"7965155\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"231\",\n" +
                "                    \"typemoney\": \"112316\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"2009\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"107367\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1746982\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"12356541\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022124\",\n" +
                "            \"detailsLink\": \"/c/2022/10/30/520437.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/30/520436.shtml\",\n" +
                "            \"date\": \"2022-10-30(日)\",\n" +
                "            \"week\": \"日\",\n" +
                "            \"red\": \"05,10,13,18,24,26\",\n" +
                "            \"blue\": \"01\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"383396494\",\n" +
                "            \"poolmoney\": \"1740587803\",\n" +
                "            \"content\": \"北京1注,安徽1注,海南1注,四川1注,共4注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"下期一等奖特别奖派奖金额：20000000元。\\n\\n下期六等奖翻番奖奖金余额：800000000元。\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"4\",\n" +
                "                    \"typemoney\": \"10000000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"124\",\n" +
                "                    \"typemoney\": \"234571\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1161\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"62669\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1294124\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"8511715\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022123\",\n" +
                "            \"detailsLink\": \"/c/2022/10/27/520215.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/27/520214.shtml\",\n" +
                "            \"date\": \"2022-10-27(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"10,13,16,20,21,25\",\n" +
                "            \"blue\": \"05\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"360850820\",\n" +
                "            \"poolmoney\": \"1693327054\",\n" +
                "            \"content\": \"天津1注,内蒙古1注,江苏2注,安徽1注,河南1注,广东1注,广西1注,共8注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"8\",\n" +
                "                    \"typemoney\": \"7557633\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"637\",\n" +
                "                    \"typemoney\": \"40151\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1247\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"93856\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1669819\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"7060237\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022122\",\n" +
                "            \"detailsLink\": \"/c/2022/10/25/519798.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/25/519797.shtml\",\n" +
                "            \"date\": \"2022-10-25(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"06,08,17,19,24,28\",\n" +
                "            \"blue\": \"05\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"350001646\",\n" +
                "            \"poolmoney\": \"1677059125\",\n" +
                "            \"content\": \"北京2注,河北2注,山西1注,辽宁1注,浙江3注,安徽1注,山东3注,湖北3注,湖南1注,四川8注,云南1注,共26注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"26\",\n" +
                "                    \"typemoney\": \"5519812\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"214\",\n" +
                "                    \"typemoney\": \"78943\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"2763\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"104504\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1750138\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"11446800\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022121\",\n" +
                "            \"detailsLink\": \"/c/2022/10/23/519279.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/23/519278.shtml\",\n" +
                "            \"date\": \"2022-10-23(日)\",\n" +
                "            \"week\": \"日\",\n" +
                "            \"red\": \"12,17,22,27,30,31\",\n" +
                "            \"blue\": \"02\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"380066114\",\n" +
                "            \"poolmoney\": \"1769892531\",\n" +
                "            \"content\": \"河北1注,山东1注,湖南1注,云南5注,共8注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"8\",\n" +
                "                    \"typemoney\": \"8069743\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"102\",\n" +
                "                    \"typemoney\": \"300955\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"789\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"50976\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1106522\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"7963048\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022120\",\n" +
                "            \"detailsLink\": \"/c/2022/10/20/518951.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/20/518950.shtml\",\n" +
                "            \"date\": \"2022-10-20(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"02,15,19,26,27,29\",\n" +
                "            \"blue\": \"02\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"355500194\",\n" +
                "            \"poolmoney\": \"1742358177\",\n" +
                "            \"content\": \"上海1注,安徽1注,河南1注,广西40注,共43注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"43\",\n" +
                "                    \"typemoney\": \"5486128\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"288\",\n" +
                "                    \"typemoney\": \"90727\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1042\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"58726\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1132983\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"8695309\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022119\",\n" +
                "            \"detailsLink\": \"/c/2022/10/18/518530.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/18/518529.shtml\",\n" +
                "            \"date\": \"2022-10-18(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"02,05,15,18,26,27\",\n" +
                "            \"blue\": \"04\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"341495720\",\n" +
                "            \"poolmoney\": \"1899873541\",\n" +
                "            \"content\": \"山西2注,黑龙江1注,浙江1注,福建2注,湖北1注,广东2注,云南1注,甘肃1注,深圳1注,共12注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"12\",\n" +
                "                    \"typemoney\": \"6494901\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"309\",\n" +
                "                    \"typemoney\": \"72568\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1455\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"79692\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1409223\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"8648633\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022118\",\n" +
                "            \"detailsLink\": \"/c/2022/10/16/518211.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/16/518210.shtml\",\n" +
                "            \"date\": \"2022-10-16(日)\",\n" +
                "            \"week\": \"日\",\n" +
                "            \"red\": \"02,06,07,11,14,33\",\n" +
                "            \"blue\": \"08\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"374309728\",\n" +
                "            \"poolmoney\": \"1910541782\",\n" +
                "            \"content\": \"江苏1注,福建1注,重庆2注,云南1注,共5注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"5\",\n" +
                "                    \"typemoney\": \"7499223\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"173\",\n" +
                "                    \"typemoney\": \"90289\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1730\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"81876\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1455172\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"16962851\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022117\",\n" +
                "            \"detailsLink\": \"/c/2022/10/13/517782.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/13/517781.shtml\",\n" +
                "            \"date\": \"2022-10-13(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"04,13,17,18,28,29\",\n" +
                "            \"blue\": \"06\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"345578024\",\n" +
                "            \"poolmoney\": \"1901177457\",\n" +
                "            \"content\": \"江苏2注,浙江1注,福建1注,湖南1注,广东1注,贵州1注,陕西2注,甘肃1注,宁夏1注,共11注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"11\",\n" +
                "                    \"typemoney\": \"6083031\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"166\",\n" +
                "                    \"typemoney\": \"89708\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"2284\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"112037\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1760031\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"12581357\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022116\",\n" +
                "            \"detailsLink\": \"/c/2022/10/11/517562.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/11/517561.shtml\",\n" +
                "            \"date\": \"2022-10-11(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"08,14,26,27,30,33\",\n" +
                "            \"blue\": \"01\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"337975208\",\n" +
                "            \"poolmoney\": \"1923415752\",\n" +
                "            \"content\": \"江苏1注,共1注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"1\",\n" +
                "                    \"typemoney\": \"10000000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"93\",\n" +
                "                    \"typemoney\": \"300121\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"977\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"45197\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"979325\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"6439820\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022115\",\n" +
                "            \"detailsLink\": \"/c/2022/10/09/517243.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/09/517242.shtml\",\n" +
                "            \"date\": \"2022-10-09(日)\",\n" +
                "            \"week\": \"日\",\n" +
                "            \"red\": \"06,07,18,20,27,29\",\n" +
                "            \"blue\": \"09\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"359461828\",\n" +
                "            \"poolmoney\": \"1849681926\",\n" +
                "            \"content\": \"辽宁1注,吉林1注,浙江2注,安徽1注,福建1注,山东1注,河南2注,广东12注,四川10注,贵州1注,云南1注,共33注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"33\",\n" +
                "                    \"typemoney\": \"5308918\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"169\",\n" +
                "                    \"typemoney\": \"75401\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"2892\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"119122\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1943075\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"14646730\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022114\",\n" +
                "            \"detailsLink\": \"/c/2022/10/06/516915.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/10/06/516914.shtml\",\n" +
                "            \"date\": \"2022-10-06(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"01,05,15,19,26,29\",\n" +
                "            \"blue\": \"13\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"347069064\",\n" +
                "            \"poolmoney\": \"1986647604\",\n" +
                "            \"content\": \"河北3注,黑龙江1注,上海1注,福建1注,山东1注,广东2注,云南2注,青海1注,共12注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"12\",\n" +
                "                    \"typemoney\": \"6667026\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"241\",\n" +
                "                    \"typemoney\": \"103756\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1227\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"66692\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1292188\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"8020200\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022113\",\n" +
                "            \"detailsLink\": \"/c/2022/09/29/516785.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/09/29/516784.shtml\",\n" +
                "            \"date\": \"2022-09-29(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"13,14,20,24,27,29\",\n" +
                "            \"blue\": \"02\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"363604180\",\n" +
                "            \"poolmoney\": \"1991635746\",\n" +
                "            \"content\": \"云南1注,甘肃1注,共2注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"2\",\n" +
                "                    \"typemoney\": \"10000000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"84\",\n" +
                "                    \"typemoney\": \"294642\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1036\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"61319\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1211708\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"10335443\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022112\",\n" +
                "            \"detailsLink\": \"/c/2022/09/27/516364.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/09/27/516363.shtml\",\n" +
                "            \"date\": \"2022-09-27(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"03,05,08,17,25,31\",\n" +
                "            \"blue\": \"01\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"348893862\",\n" +
                "            \"poolmoney\": \"1937385782\",\n" +
                "            \"content\": \"安徽1注,湖北1注,广西2注,陕西1注,共5注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"5\",\n" +
                "                    \"typemoney\": \"9412037\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"169\",\n" +
                "                    \"typemoney\": \"163167\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1073\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"65301\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1392200\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"6091173\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022111\",\n" +
                "            \"detailsLink\": \"/c/2022/09/25/516145.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/09/25/516144.shtml\",\n" +
                "            \"date\": \"2022-09-25(日)\",\n" +
                "            \"week\": \"日\",\n" +
                "            \"red\": \"02,10,11,13,28,31\",\n" +
                "            \"blue\": \"01\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"385923798\",\n" +
                "            \"poolmoney\": \"1901720272\",\n" +
                "            \"content\": \"上海1注,安徽1注,湖北1注,共3注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"3\",\n" +
                "                    \"typemoney\": \"10000000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"160\",\n" +
                "                    \"typemoney\": \"198653\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"953\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"51921\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1075858\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"7592578\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022110\",\n" +
                "            \"detailsLink\": \"/c/2022/09/22/516017.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/09/22/516016.shtml\",\n" +
                "            \"date\": \"2022-09-22(四)\",\n" +
                "            \"week\": \"四\",\n" +
                "            \"red\": \"09,13,15,18,20,28\",\n" +
                "            \"blue\": \"15\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"352346658\",\n" +
                "            \"poolmoney\": \"1836366779\",\n" +
                "            \"content\": \"上海1注,浙江1注,江西2注,山东2注,贵州1注,共7注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"7\",\n" +
                "                    \"typemoney\": \"8127543\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"237\",\n" +
                "                    \"typemoney\": \"115468\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1517\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"71846\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1358309\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"6136513\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"双色球\",\n" +
                "            \"code\": \"2022109\",\n" +
                "            \"detailsLink\": \"/c/2022/09/20/515697.shtml\",\n" +
                "            \"videoLink\": \"/c/2022/09/20/515696.shtml\",\n" +
                "            \"date\": \"2022-09-20(二)\",\n" +
                "            \"week\": \"二\",\n" +
                "            \"red\": \"04,11,13,19,22,33\",\n" +
                "            \"blue\": \"11\",\n" +
                "            \"blue2\": \"\",\n" +
                "            \"sales\": \"349805376\",\n" +
                "            \"poolmoney\": \"1811161575\",\n" +
                "            \"content\": \"吉林2注,浙江2注,四川1注,共5注。\",\n" +
                "            \"addmoney\": \"\",\n" +
                "            \"addmoney2\": \"\",\n" +
                "            \"msg\": \"\",\n" +
                "            \"z2add\": \"\",\n" +
                "            \"m2add\": \"\",\n" +
                "            \"prizegrades\": [\n" +
                "                {\n" +
                "                    \"type\": 1,\n" +
                "                    \"typenum\": \"5\",\n" +
                "                    \"typemoney\": \"8345394\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 2,\n" +
                "                    \"typenum\": \"78\",\n" +
                "                    \"typemoney\": \"268060\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 3,\n" +
                "                    \"typenum\": \"1475\",\n" +
                "                    \"typemoney\": \"3000\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 4,\n" +
                "                    \"typenum\": \"75543\",\n" +
                "                    \"typemoney\": \"200\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 5,\n" +
                "                    \"typenum\": \"1271773\",\n" +
                "                    \"typemoney\": \"10\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 6,\n" +
                "                    \"typenum\": \"11103687\",\n" +
                "                    \"typemoney\": \"5\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"type\": 7,\n" +
                "                    \"typenum\": \"\",\n" +
                "                    \"typemoney\": \"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

}
