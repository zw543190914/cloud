package com.zw.cloud.tools.utils.html;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.List;

public class HutoolHtmlUtils {

    public static void main(String[] args) {
        //请求列表页
        String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
        //使用正则获取所有标题
        // 其中(.*?)表示我们需要的内容，.表示任意字符，*表示0个或多个，?表示最短匹配，整个正则的意思就是。
        // 以<span class=\"text-ellipsis\">开头，</span>结尾的中间所有字符，中间的字符要达到最短。?的作用其实就是将范围限制到最小，不然</span>很可能匹配到后面去了。
        List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
        for (String title : titles) {
            //打印标题
            Console.log(title);
        }
    }
}
