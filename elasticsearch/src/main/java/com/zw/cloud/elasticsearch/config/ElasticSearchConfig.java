package com.zw.cloud.elasticsearch.config;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

   /* @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }*/

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        HttpHost host1 = new HttpHost("192.168.22.7", 9200, "http");
        HttpHost host2 = new HttpHost("192.168.22.8", 9200, "http");
        HttpHost host3 = new HttpHost("192.168.22.9", 9200, "http");
        return new RestHighLevelClient(
                RestClient.builder(host1,host2,host3));
    }

}
