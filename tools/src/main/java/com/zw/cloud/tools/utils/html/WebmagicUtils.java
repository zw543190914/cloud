package com.zw.cloud.tools.utils.html;

import com.zw.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebmagicUtils implements PageProcessor {

    public String url = "https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=317e5dd2e1eb413f842e2aff50c95286";


    @Autowired
    private PipelineData pipelineData;

    private Site site = new Site()
            .setCharset("utf-8")
            .setTimeOut(10000)
            .setRetrySleepTime(10000)
            .setRetryTimes(3);

    @Override
    public void process(Page page) {
        /*List<Selectable> list = page.getHtml().css("div#jd-price").nodes();
        if (CollectionUtils.isEmpty(list)){
            // 列表页
            List<Selectable> nodes = page.getHtml().css("div#J_goodsList div.p-name").nodes();
            for (Selectable node : nodes) {
                String link = node.links().toString();
                page.addTargetRequest(link);
            }
            // 获取下一页按钮

        } else {
            // 详情页获取信息
            saveDetail(page);
        }*/
        saveDetail(page);
        page.addTargetRequest("https://search.jd.com/Search?keyword=java&wq=java&page=3&s=51&click=0");

    }

    private void saveDetail(Page page){
        List<User> userList = new ArrayList<>();
       /* String price = page.getHtml().css("div#jd-price","text").get();
        if (null == price){
            return;
        }
        String url = page.getHtml().css("div#name div.sku-name").links().get();
        String name = page.getHtml().css("div#name div.sku-name","text").smartContent().get();
        user.setName(name);
        user.setImage(url);*/
        List<Selectable> nodes = page.getHtml().css("div#J_goodsList div.gl-i-wrap").nodes();
        for (Selectable node : nodes) {
            User user = new User();
            String name = node.css("div.p-name em","text").toString();
            user.setName(name);
            String description = node.css("div.p-shop span a","text").toString();
            user.setDescription(description);
            String price = node.css("div.p-price i","text").toString();
            System.out.println(price);
            userList.add(user);
        }
        page.putField("user", userList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    //@Scheduled(initialDelay = 1000,fixedDelay = 60000)
    public void process(){
        Spider.create(new WebmagicUtils())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .addPipeline(pipelineData)
                //.addPipeline(new FilePipeline("E:\\图片"))
                .run();

    }

}
