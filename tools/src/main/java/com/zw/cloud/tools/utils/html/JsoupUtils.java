package com.zw.cloud.tools.utils.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class JsoupUtils {
    public static void main(String[] args)throws Exception {
        Document document = Jsoup.parse(new URL("https://item.jd.com/12642177.html"), 60000);
    }
}
