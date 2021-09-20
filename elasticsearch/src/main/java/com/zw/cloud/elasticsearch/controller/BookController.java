package com.zw.cloud.elasticsearch.controller;

import com.zw.cloud.elasticsearch.entity.Book;
import com.zw.cloud.elasticsearch.respository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookRespository bookRespository;

    @PostMapping
    //http://localhost:9050/book
    public void save(@RequestBody Book book){
        bookRespository.save(book);
    }

    @GetMapping("/{name}")
    //http://localhost:9050/book/java
    public List<Book> queryByName(@PathVariable("name") String name){
        return bookRespository.queryBookByNameLike(name);
    }

    @GetMapping("/queryById/{id}")
    //http://localhost:9050/book/queryById/1
    public Optional<Book> queryById(@PathVariable("id") String id){
        return bookRespository.findById(id);
    }
}
