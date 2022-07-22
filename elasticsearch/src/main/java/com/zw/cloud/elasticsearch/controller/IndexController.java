package com.zw.cloud.elasticsearch.controller;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @GetMapping(value = {"/getIndex/{indexName}","/getIndex"})
    //http://localhost:9050/index/getIndex/qa-quanbu-dyeing-2022.07.18
    public void getIndex(@PathVariable(value = "indexName",required = false) String indexName) throws IOException {
        //1、构建 获取索引的请求
        GetIndexRequest request = new GetIndexRequest(indexName);
        //2、客户端判断该索引是否存在
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        //3、打印
        System.out.println("该索引是否存在："+exists);
    }

    @GetMapping(value = {"/getDocument/{indexName}/{key}","/getDocument/{indexName}"})
        //http://localhost:9050/index/getDocument/qa-quanbu-dyeing-2022.07.17/
    public void getDocument(@PathVariable(value = "indexName") String indexName,@PathVariable(value = "key",required = false) String key) throws IOException {
        //获取id为1的文档的信息
        GetRequest request = new GetRequest(indexName,key);

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println("文档是否存在："+exists);
        //如果存在，获取文档信息
        if (exists){
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println("文档内容为："+response.getSourceAsString());
        }
    }

}
