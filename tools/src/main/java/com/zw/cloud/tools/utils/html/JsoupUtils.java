package com.zw.cloud.tools.utils.html;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JsoupUtils {
    public static void main(String[] args)throws Exception {
        String[] pags = new String[] {
                "http://www.mmonly.cc/wmtp/wmxz/list_27_1.html",  //唯美写真
                /*"http://www.mmonly.cc/wmtp/wmxz/list_27_2.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_3.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_4.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_5.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_6.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_7.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_8.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_9.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_10.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_11.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_12.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_13.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_14.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_15.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_16.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_17.html",  //唯美写真
                "http://www.mmonly.cc/wmtp/wmxz/list_27_18.html",  //唯美写真

                "http://www.mmonly.cc/wmtp/qltp/list_22_2.html",   //情侣图片*/
        };
        List<String> srcList = new ArrayList<>();
        for (String pag : pags) {
            //创建页面对象
            Document doc = Jsoup.connect(pag)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                    .timeout(10000).get();
            //document.getElementById(id);//通过ID获取元素，返回Element
            //document.getElementsByClass(className);//通过类名获取元素,返回Elements
            //document.getElementsByTag(tagName);//通过标签获取元素,返回Elements
            //根据标签和class id获取元素
            // div class="ABox"
            Elements div = doc.select("div.ABox");
            //根据标签获取元素
            Elements pages = div.select("a");
            for (Element e : pages) {
                String href = e.attr("href");
                // https://www.mmonly.cc/wmtp/wmxz/341898.html
                log.info("[jsoup] href:{}",href);
                try {
                    Document imgdoc = Jsoup.connect(href)
                            .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                            .timeout(10000)
                            .get();
                    // span class="totalpage"
                    Elements div22 = imgdoc.select(".totalpage");
                    String totalpageStr = div22.get(0).text();
                    int totalpage = Integer.parseInt(totalpageStr);
                    if (totalpage > 0) {
                        for (int i = 1; i <= totalpage; i++) {
                            String url = href.substring(0, href.length() - 5) + "_" + i + ".html";
                            log.info("[jsoup] url:{},totalpage is {}",url,totalpage);
                            try {
                                // https://www.mmonly.cc/wmtp/wmxz/341898_1.html
                                Document imgpage = Jsoup.connect(url)
                                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                                        .timeout(100000).get();
                                // id="big-pic"
                                Elements div2 = imgpage.select("#big-pic");
                                // big-pic 下面的 p 标签 <p align="center">
                                Elements p = div2.select("p");
                                // p -> a -> img
                                Elements img = p.select("img");
                                // <img src="https://t1.mmonly.cc/uploads/tu/202208/147/001x2fINgy1h5glsxy7sqj60r80shtf202.jpg">
                                String src = img.attr("src");

                                if (StringUtils.isBlank(src) || !StringUtils.startsWith(src, "http")) {
                                    continue;
                                }
                                log.info("[jsoup] src:{}",src);
                                srcList.add(src);
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (IOException ioException) {
                                log.warn("[jsoup] url:{} exception ",url,ioException);
                            }
                        }
                    }
                } catch (Exception exception) {
                    log.warn("[jsoup]out href:{},exception ",href,exception);
                }
            }
        }
        String url = srcList.get(0);
        int i = url.lastIndexOf(".");
        String type = url.substring(i + 1);
        HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
        httpUrl.connect();
        InputStream inputStream = httpUrl.getInputStream();
        File file = new File("d:/test." + type);
        FileOutputStream fileOutputStream=new FileOutputStream(file);


    }
}
