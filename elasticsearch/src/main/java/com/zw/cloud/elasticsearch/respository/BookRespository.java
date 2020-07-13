package com.zw.cloud.elasticsearch.respository;

import com.zw.cloud.elasticsearch.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRespository extends ElasticsearchRepository<Book,String> {
    // 自定义查询
    List<Book> queryBookByNameLike(String name);


}
