package com.zw.cloud.elasticsearch.controller;

import com.zw.cloud.elasticsearch.service.impl.ElasticSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ElasticSearchController {

    @Autowired
    private ElasticSearchImpl elasticSearch;

    @GetMapping({"/","/index"})
    //http://localhost:9050/
    public String index(){
        return "index";
    }

    @GetMapping("/parseContentFromJD/{keyword}")
    @ResponseBody
    //http://localhost:9050/parseContentFromJD/java
    public boolean parseContentFromJD(@PathVariable("keyword") String keyword)throws Exception{
        return elasticSearch.parseContentFromJD(keyword);
    }

    @GetMapping("/queryGoods/{keyword}/{pageNo}/{pageSize}")
    @ResponseBody
    //http://localhost:9050/queryGoods/java/1/30
    public List<Map<String, Object>> queryGoods(@PathVariable("keyword") String keyword,
                                                @PathVariable("pageNo") int pageNo,@PathVariable("pageSize") int pageSize)throws Exception{
        return elasticSearch.queryGoods(keyword,pageNo,pageSize);
    }
}
