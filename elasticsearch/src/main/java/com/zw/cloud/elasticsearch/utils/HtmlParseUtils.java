package com.zw.cloud.elasticsearch.utils;

import com.zw.cloud.elasticsearch.entity.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtils {

    public static void main(String[] args) throws Exception {
        List<Content> contentList = parseJD("化妆");
        contentList.forEach(System.out::println);
    }

    public static List<Content> parseJD(String keyword)throws Exception{
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 60000);
        Element element = document.getElementById("J_goodsList");
        Elements li = element.getElementsByTag("li");
        List<Content> contentList = new ArrayList<>();
        for (Element el : li){
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String name = el.getElementsByClass("p-name").eq(0).text();
            System.out.println("img : " + img);
            System.out.println("price : " + price);
            System.out.println("name : " + name);
            Content content = new Content(name, img, price);
            contentList.add(content);
        }
        return contentList;
    }
}
