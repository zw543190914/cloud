package com.zw.cloud.tools.controller;

import com.github.pagehelper.PageHelper;
import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cp")
public class CpController {

    @Autowired
    private TcMapper tcMapper;

    @GetMapping("/index")
    //http://localhost:9040/cp/index
    public ModelAndView index(Model model){

        PageHelper.startPage(1,100);
        TcExample tcExample = new TcExample();
        tcExample.setOrderByClause("id desc");
        List<Tc> tcList = tcMapper.selectByExample(tcExample);
        List<TcVO> tcVOList = tcList.stream().map(tc -> {
            TcVO tcVO = new TcVO();
            BeanUtils.copyProperties(tc, tcVO);
            return tcVO;
        }).collect(Collectors.toList());
        model.addAttribute("cpList",tcVOList);
        model.addAttribute("tc",new TcVO());

        return new ModelAndView("index3","cpModel",model);
    }

    @PostMapping("/query")
    public ModelAndView query(TcVO tc,Model model){
        List<Tc> tcList = new ArrayList<>();
        if (null != tc){
            TcExample tcExample = new TcExample();
            TcExample.Criteria criteria = tcExample.createCriteria();
            if (null != tc.getId()){
                criteria.andIdEqualTo(tc.getId());
            }
            if (null != tc.getOne()){
                criteria.andOneEqualTo(tc.getOne());
            }
            if (null != tc.getTwo()){
                criteria.andTwoEqualTo(tc.getTwo());
            }
            if (null != tc.getThree()){
                criteria.andThreeEqualTo(tc.getThree());
            }
            if (null != tc.getFour()){
                criteria.andFourEqualTo(tc.getFour());
            }
            if (null != tc.getFive()){
                criteria.andFiveEqualTo(tc.getFive());
            }
            if (null != tc.getSix()){
                criteria.andSixEqualTo(tc.getSix());
            }
            if (null != tc.getSeven()){
                criteria.andSevenEqualTo(tc.getSeven());
            }
            tcExample.setOrderByClause("id desc");
            PageHelper.startPage(Optional.ofNullable(tc.pageNo).orElse(1),Optional.ofNullable(tc.getPageSize()).orElse(50));
            tcList = tcMapper.selectByExample(tcExample);
        }
        List<TcVO> tcVOList = tcList.stream().map( s -> {
            TcVO tcVO = new TcVO();
            BeanUtils.copyProperties(s, tcVO);
            return tcVO;
        }).collect(Collectors.toList());
        model.addAttribute("cpList",tcVOList);
        model.addAttribute("tc",new TcVO());

        return new ModelAndView("index3","cpModel",model);
    }

    @PostMapping("/queryHot")
    //http://localhost:9040/cp/queryHot
    public ModelAndView queryHot(TcVO tc,Model model){
        TcExample tcExample = new TcExample();
        tcExample.createCriteria().andIdGreaterThan(Optional.ofNullable(tc.getOne()).orElse(20000));
        List<Tc> tcList = tcMapper.selectByExample(tcExample);
        Map<String, Map<Integer,Integer>> result = new HashMap<>();
        Map<Integer,Integer> map = new HashMap<>();
        Map<Integer,Integer> blueMap = new HashMap<>();

        tcList.forEach(s -> {
            map.merge(s.getOne(), 1, Integer::sum);
            map.merge(s.getTwo(), 1, Integer::sum);
            map.merge(s.getThree(), 1, Integer::sum);
            map.merge(s.getFour(), 1, Integer::sum);
            map.merge(s.getFive(), 1, Integer::sum);
            blueMap.merge(s.getSix(), 1, Integer::sum);
            blueMap.merge(s.getSeven(), 1, Integer::sum);
        });
        List<TcVO> tcVOList = new ArrayList<>();
        int value = tc.getTwo() == null ? 1 : tc.getTwo();
        if (1 == value){
            blueMap.forEach((k,v) -> {
                TcVO tcVO = new TcVO();
                tcVO.setCode(k);
                tcVO.setCount(v);
                tcVOList.add(tcVO);
            });
        } else {
            map.forEach((k,v) -> {
                TcVO tcVO = new TcVO();
                tcVO.setCode(k);
                tcVO.setCount(v);
                tcVOList.add(tcVO);
            });
        }

        List<TcVO> sortedVOList = tcVOList.stream().sorted(Comparator.comparing(TcVO::getCount).reversed()).collect(Collectors.toList());
        /*result.put("before",map);
        result.put("blueMap",blueMap);
        return result;*/

        model.addAttribute("cpList",sortedVOList);
        model.addAttribute("tc",new TcVO());

        return new ModelAndView("index3","cpModel",model);
    }

    @GetMapping("/add")
    @ResponseBody
    //http://localhost:9040/tc/add?one=1&two=11&three=19&four=29&five=35&six=1&seven=12&id=
    public void add(Integer id,Integer one,Integer two,Integer three,Integer four,Integer five,Integer six,Integer seven){
        Tc tc = new Tc();
        tc.setId(id);
        tc.setOne(one);
        tc.setTwo(two);
        tc.setThree(three);
        tc.setFour(four);
        tc.setFive(five);
        tc.setSix(six);
        tc.setSeven(seven);
        tcMapper.insert(tc);
    }

    class TcVO extends Tc{
        private Integer pageNo ;
        private Integer pageSize;
        private Integer code;
        private Integer count;

        public TcVO() {
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
    }
}
