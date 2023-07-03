package com.zw.cloud.elasticsearch.utils;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.elasticsearch.entity.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class ElasticSearchUtils {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    public CreateIndexResponse addIndex(String index) throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
        return client.indices().create(indexRequest, RequestOptions.DEFAULT);
    }

    public boolean checkIndexExit(String index) throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest(index);
        return client.indices().exists(indexRequest, RequestOptions.DEFAULT);
    }

    public AcknowledgedResponse deleteIndex(String index) throws IOException {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest(index);
        return client.indices().delete(indexRequest, RequestOptions.DEFAULT);
    }

    // =========================文档操作==============

    public IndexResponse addDoc(String index,String id,Object obj) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(String.valueOf(id));
        //indexRequest.timeout("1s");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.source(JSON.toJSONString(obj), XContentType.JSON);
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
       return response;

    }

    public boolean checkExit(String index,String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        // 不设置上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        //request.storedFields("_none_");
        return client.exists(request, RequestOptions.DEFAULT);
    }

    public GetResponse queryDocById(String index,String id) throws IOException {
        GetRequest request = new GetRequest(index, id);
        return client.get(request, RequestOptions.DEFAULT);
    }

    public UpdateResponse updateDoc(String index,String id,Object obj) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        updateRequest.timeout("2s");
        updateRequest.doc(JSON.toJSONString(obj),XContentType.JSON);

        return client.update(updateRequest, RequestOptions.DEFAULT);
    }

    public DeleteResponse delDoc(String index,String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index,id);
        deleteRequest.timeout("2s");
        return client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    //批量处理
    public void bulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        User user;
        for (int i = 0 ; i < 10;i ++){
            user = new User();
            user.setId((long)i + 1);
            user.setName("zhengwei"+ i);
            user.setAge((byte) (18 + i));
            user.setDescription("这里是描述信息" + i);
            bulkRequest.add(new IndexRequest("test").id(String.valueOf(i + 1)).source(JSON.toJSONString(user),XContentType.JSON));

        }
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());
    }

    /**
     * QueryBuilders 查询
     * QueryBuilders 高亮
     * @throws IOException
     */
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // term 精确匹配 QueryBuilders.termQuery
        // matchAllQuery 匹配所有
        //fuzzyQuery
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name", "zhengwei");
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        searchSourceBuilder.timeout(new TimeValue(10, TimeUnit.SECONDS));

        //searchSourceBuilder.highlighter()
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
