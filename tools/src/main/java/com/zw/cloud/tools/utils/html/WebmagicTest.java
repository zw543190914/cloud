package com.zw.cloud.tools.utils.html;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import com.zw.cloud.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class WebmagicTest implements PageProcessor {

    @Autowired
    private TcMapper tcMapper;

    public String url = "https://www.lottery.gov.cn/historykj/history.jspx?_ltype=dlt";


    @Autowired
    private PipelineData pipelineData;

    private Site site = new Site()
            .setCharset("utf-8")
            .setTimeOut(10000)
            .setRetrySleepTime(10000)
            .setRetryTimes(3);

    @Override
    public void process(Page page) {
        for (int i = 2; i < 100 ; i ++){
            String s = "https://www.lottery.gov.cn/historykj/history_" + i + ".jspx?_ltype=dlt";
            page.addTargetRequest(s);
        }
        saveDetail(page);
    }

    private void saveDetail(Page page){
        List<Tc> tcList = new ArrayList<>();
       /* String price = page.getHtml().css("div#jd-price","text").get();
        if (null == price){
            return;
        }
        String url = page.getHtml().css("div#name div.sku-name").links().get();
        String name = page.getHtml().css("div#name div.sku-name","text").smartContent().get();
        user.setName(name);
        user.setImage(url);*/

        List<Selectable> nodes = page.getHtml().css("div.result table tbody tr").nodes();

        for (Selectable node : nodes) {
            Tc tc = new Tc();
            List<Selectable> selectables = node.css("td").nodes();
            Integer id = Integer.valueOf(selectables.get(0).css("td", "text").toString());
            tc.setId(id);
            tc.setFirst(Integer.valueOf(selectables.get(1).css("td","text").toString()));
            tc.setSecond(Integer.valueOf(selectables.get(2).css("td","text").toString()));
            tc.setThird(Integer.valueOf(selectables.get(3).css("td","text").toString()));
            tc.setFour(Integer.valueOf(selectables.get(4).css("td","text").toString()));
            tc.setFive(Integer.valueOf(selectables.get(5).css("td","text").toString()));
            tc.setBlueFirst(Integer.valueOf(selectables.get(6).css("td","text").toString()));
            tc.setBlueSecond(Integer.valueOf(selectables.get(7).css("td","text").toString()));
            System.out.println(JSON.toJSONString(tc));
            tcList.add(tc);
        }
        page.putField("tcList", tcList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    //@PostConstruct
    public void process(){
        tcMapper.deleteByExample(new TcExample());

        Spider.create(new WebmagicTest())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(4)
                .addPipeline(pipelineData)
                //.addPipeline(new FilePipeline("E:\\图片"))
                .run();

    }

}
