package com.zw.cloud.elasticsearch.test;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.entity.User;
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
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    public void addIndex() throws IOException {
        CreateIndexRequest indexRequest = new CreateIndexRequest("test");
        CreateIndexResponse createIndexResponse = client.indices().create(indexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(createIndexResponse));
    }

    @Test
    public void checkIndexExit() throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest("test");
        boolean exists = client.indices().exists(indexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest indexRequest = new DeleteIndexRequest("ilm-history-2-000001");
        AcknowledgedResponse delete = client.indices().delete(indexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(delete));
    }

    // =========================文档操作==============

    @Test
    public void addDoc() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("zw");
        user.setAge((byte) 18);
        user.setDescription("这里是描述信息");

        IndexRequest indexRequest = new IndexRequest("test");
        indexRequest.id(String.valueOf(user.getId()));
        //indexRequest.timeout("1s");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(index));

    }

    @Test
    public void checkExit() throws IOException {
        GetRequest request = new GetRequest("test", "1");
        // 不设置上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        //request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    public void queryDoc() throws IOException {
        GetRequest request = new GetRequest("test", "1");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
        System.out.println(JSON.toJSONString(getResponse));
    }

    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test", "1");
        updateRequest.timeout("1s");

        User user = new User();
        user.setId(1L);
        user.setName("zhengwei");
        user.setAge((byte) 18);
        user.setDescription("这里是描述信息,hahaha");
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);

        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(update.status()));
    }

    @Test
    public void delDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("test", "1");
        deleteRequest.timeout("1s");
        DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(delete.status()));

    }

    //批量处理
    @Test
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
    @Test
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
