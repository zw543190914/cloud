package com.zw.cloud.tools.controller;

import com.github.pagehelper.PageHelper;
import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import org.apache.commons.lang3.StringUtils;
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
        return "index";
    }

    @GetMapping("/queryGoods")
    @ResponseBody
    //http://localhost:9040/tc/queGoods/java/1/30
    public List<Tc> queryGoods(String keyword,
                               @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") int pageSize){
        TcExample tcExample = new TcExample();
        TcExample.Criteria criteria = tcExample.createCriteria();
        if (StringUtils.isNotBlank(keyword)){
            String[] nums = keyword.split(",");
            criteria.andOneEqualTo(Integer.valueOf(nums[0]));
            if (nums.length > 1){
                criteria.andTwoEqualTo(Integer.valueOf(nums[1]));
            }
            if (nums.length > 2){
                criteria.andThreeEqualTo(Integer.valueOf(nums[2]));
            }
            if (nums.length > 3){
                criteria.andFourEqualTo(Integer.valueOf(nums[3]));
            }
            if (nums.length > 4){
                criteria.andFiveEqualTo(Integer.valueOf(nums[4]));
            }
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
        tc.setOne(one);
        tc.setTwo(two);
        tc.setThree(three);
        tc.setFour(four);
        tc.setFive(five);
        tc.setSix(six);
        tc.setSeven(seven);
        tcMapper.insert(tc);
    }
    @GetMapping("/query")
    @ResponseBody
    //http://localhost:9040/tc/query?one=5&two=17&three=19&four=21&five=35&six=3&seven=13
    public List<Tc> query(Integer one,Integer two,Integer three,Integer four,Integer five,Integer six,Integer seven){
        TcExample tcExample = new TcExample();
        TcExample.Criteria criteria = tcExample.createCriteria();
        if (null != one){
            criteria.andOneEqualTo(one);
        }
        if (null != two){
            criteria.andTwoEqualTo(two);
        }
        if (null != three){
            criteria.andThreeEqualTo(three);
        }
        if (null != four){
            criteria.andFourEqualTo(four);
        }
        if (null != five){
            criteria.andFiveEqualTo(five);
        }
        if (null != six){
            criteria.andSixEqualTo(six);
        }
        if (null != seven){
            criteria.andSevenEqualTo(seven);
        }
        tcExample.setOrderByClause("id desc");
        return tcMapper.selectByExample(tcExample);
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
