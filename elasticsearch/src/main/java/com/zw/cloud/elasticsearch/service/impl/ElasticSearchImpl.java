package com.zw.cloud.elasticsearch.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.elasticsearch.entity.Content;
import com.zw.cloud.elasticsearch.utils.HtmlParseUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ElasticSearchImpl {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    public boolean parseContentFromJD(String keyword)throws Exception{
        List<Content> contentList = HtmlParseUtils.parseJD(keyword);
        if (CollectionUtils.isEmpty(contentList)){
            throw new RuntimeException("contentList is empty");
        }
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("30s");
        for (int i = 0;i < contentList.size();i ++){
            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(contentList.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    public List<Map<String, Object>> queryGoods(String keyword,int pageNo,int pageSize)throws Exception{
        if (pageNo < 1){
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", keyword);
        sourceBuilder.from( (pageNo - 1) * pageSize);
        sourceBuilder.size(pageSize);
        sourceBuilder.query(queryBuilder);
        sourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        // 只高亮一个词
        //highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style = 'color:red'>");
        highlightBuilder.postTags("</span>");

        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> result = new ArrayList<>();
        if (null == searchResponse.getHits() || null == searchResponse.getHits().getHits()){
            return Collections.emptyList();
        }
        for (SearchHit hit : searchResponse.getHits().getHits()){

            // 高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField name = highlightFields.get("name");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (null != name){
                Text[] fragments = name.fragments();
                StringBuilder nameSb = new StringBuilder();
                for (Text text : fragments) {
                    nameSb.append(text);
                }
                // 替换原有 name
                sourceAsMap.put("name",nameSb.toString());
                sourceAsMap.put("score", hit.getScore());
            }
            result.add(sourceAsMap);
        }
        return result;
    }

    public SearchResponse queryLog(String keyword,int pageNo,int pageSize)throws Exception{
        if (pageNo < 1){
            pageNo = 1;
        }
        SearchRequest searchRequest = new SearchRequest("qa-quanbu-dyeing-2022.07.17");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword);
        sourceBuilder.from( (pageNo - 1) * pageSize);
        sourceBuilder.size(pageSize);
        sourceBuilder.query(multiMatchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));
       /* MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("message", keyword);
        sourceBuilder.from( (pageNo - 1) * pageSize);
        sourceBuilder.size(pageSize);
        sourceBuilder.query(queryBuilder);
        sourceBuilder.timeout(new TimeValue(5, TimeUnit.SECONDS));*/

        searchRequest.source(sourceBuilder);
        return client.search(searchRequest, RequestOptions.DEFAULT);
    }
}
