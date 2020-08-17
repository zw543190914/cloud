package com.zw.cloud.tools.controller;

import com.github.pagehelper.PageHelper;
import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tc")
public class TcController {

    @Autowired
    private TcMapper tcMapper;

    @GetMapping("/main")
    //http://localhost:9040/tc/main
    public String index(){
        System.out.println("index");
        return "index";
    }

    @GetMapping("/queryGoods")
    @ResponseBody
    //http://localhost:9040/tc/queGoods/java/1/30
    public List<Tc> queryGoods(Integer keyword,
                               @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") int pageSize){
        TcExample tcExample = new TcExample();
        TcExample.Criteria criteria = tcExample.createCriteria();
        if (null != keyword){
            criteria.andIdEqualTo(keyword);
        }
        tcExample.setOrderByClause("id desc");
        PageHelper.startPage(pageNo,pageSize);
        return tcMapper.selectByExample(tcExample);
    }

    @GetMapping("/add")
    @ResponseBody
    //http://localhost:9040/tc/add?one=1&two=11&three=19&four=29&five=35&six=1&seven=12&id=
    public void add(Integer id,Integer one,Integer two,Integer three,Integer four,Integer five,Integer six,Integer seven){
        Tc tc = new Tc();
        tc.setId(id);
        tc.setFirst(one);
        tc.setSecond(two);
        tc.setThird(three);
        tc.setFour(four);
        tc.setFive(five);
        tc.setBlueFirst(six);
        tc.setBlueSecond(seven);
        tcMapper.insert(tc);
    }
    @GetMapping("/query")
    @ResponseBody
    //http://localhost:9040/tc/query?one=1&two=11&three=19&four=29&five=35&six=1&seven=12
    public List<Tc> query(Integer one,Integer two,Integer three,Integer four,Integer five,Integer six,Integer seven){
        TcExample tcExample = new TcExample();
        TcExample.Criteria criteria = tcExample.createCriteria();
        if (null != one){
            criteria.andFirstEqualTo(one);
        }
        if (null != two){
            criteria.andSecondEqualTo(two);
        }
        if (null != three){
            criteria.andThirdEqualTo(three);
        }
        if (null != four){
            criteria.andFourEqualTo(four);
        }
        if (null != five){
            criteria.andFiveEqualTo(five);
        }
        if (null != six){
            criteria.andBlueFirstEqualTo(six);
        }
        if (null != seven){
            criteria.andBlueSecondEqualTo(seven);
        }
        tcExample.setOrderByClause("id desc");
        return tcMapper.selectByExample(tcExample);
    }

    @GetMapping("/queryFirst")
    @ResponseBody
    //http://localhost:9040/tc/queryFirst
    public List<TcVO> queryFirst(){
        List<Integer> first = tcMapper.queryFirst();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }

    @GetMapping("/querySecond")
    @ResponseBody
    //http://localhost:9040/tc/querySecond
    public List<TcVO> querySecond(){
        List<Integer> first = tcMapper.querySecond();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }
    @GetMapping("/queryThird")
    @ResponseBody
    //http://localhost:9040/tc/queryThird
    public List<TcVO> queryThird(){
        List<Integer> first = tcMapper.queryThird();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }
    @GetMapping("/queryFour")
    @ResponseBody
    //http://localhost:9040/tc/queryFour
    public List<TcVO> queryFour(){
        List<Integer> first = tcMapper.queryFour();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }
    @GetMapping("/queryFive")
    @ResponseBody
    //http://localhost:9040/tc/queryFive
    public List<TcVO> queryFive(){
        List<Integer> first = tcMapper.queryFive();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }
    @GetMapping("/queryBlueFirst")
    @ResponseBody
    //http://localhost:9040/tc/queryBlueFirst
    public List<TcVO>queryBlueFirst(){
        List<Integer> first = tcMapper.queryBlueFirst();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }
    @GetMapping("/queryBlueSecond")
    @ResponseBody
    //http://localhost:9040/tc/queryBlueSecond
    public List<TcVO> queryBlueSecond(){
        List<Integer> first = tcMapper.queryBlueSecond();
        Map<Integer, Long> result = first.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        List<TcVO> tcVOList = new ArrayList<>();
        result.forEach((k,v) -> {
            TcVO tcVO = new TcVO();
            tcVO.setCode(k);
            tcVO.setCount(v);
            tcVOList.add(tcVO);
        });
        return tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList()).subList(0,5);
    }

    class TcVO{
        private Integer code;
        private Long count;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }
}
