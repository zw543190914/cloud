package com.zw.cloud.elasticsearch.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

//@Document(indexName = "jd_goods",type = "_doc")
@Document(indexName = "jd_goods")
public class Book implements Serializable {
    private String id;
    private String name;
    private String img;
    private String price;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book(String name, String img, String price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
