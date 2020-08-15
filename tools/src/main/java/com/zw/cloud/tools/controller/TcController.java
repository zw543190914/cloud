package com.zw.cloud.tools.controller;

import com.github.pagehelper.PageHelper;
import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
