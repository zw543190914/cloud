package com.zw.cloud.tools.utils.html.webmagic;

import com.zw.cloud.common.entity.vo.ImgAttachmentVO;
import com.zw.cloud.common.utils.jsoup.JsoupUtils;
import com.zw.cloud.tools.utils.html.webmagic.pipeline.BaiduImgPipelineData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BaiduImgWebmagicTest implements PageProcessor {

    @Autowired
    private BaiduImgPipelineData baiduImgPipelineData;

    private Site site = new Site()
            .setCharset("utf-8")
            .setTimeOut(3000)
            .setRetrySleepTime(3000)
            .setSleepTime(2000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
            .setRetryTimes(3);

    @Override
    public void process(Page page) {
        /*for (int i = 2; i < 100 ; i ++){
            String s = "https://www.lottery.gov.cn/historykj/history_" + i + ".jspx?_ltype=dlt";
            page.addTargetRequest(s);
        }*/
        Document document = page.getHtml().getDocument();
        List<ImgAttachmentVO> imgDTOList = new ArrayList<>(200);
        try {
            JsoupUtils.jsoupParseDocument(document,imgDTOList);
        } catch (IOException e) {
            log.warn("[BaiduImgWebmagicTest][process] error is ",e);
        }
        page.putField("imgDTOList", imgDTOList);
    }

    @Override
    public Site getSite() {
        /*HashMap<String, String> cookie = JsoupUtils.convertCookie("BIDUPSID=3027CF844698F7FF86A5258BFFAF539A; PSTM=1655713092; BAIDUID=46707C2D54CB50C4DCBCA857C300118B:FG=1; BDUSS=XRNR05zUllIN2RMREJZTTRZN3ZGdTIxUTJtck11dHRBS280UkxPcEU3YXFVRzlqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKrDR2Oqw0djQ; BDUSS_BFESS=XRNR05zUllIN2RMREJZTTRZN3ZGdTIxUTJtck11dHRBS280UkxPcEU3YXFVRzlqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKrDR2Oqw0djQ; BDSFRCVID=uZAOJexroG0r-YbjD6F-2uSfCgKK0gOTDYLEOwXPsp3LGJLVgrUBEG0Pt_U-mEt-J8jwogKK0eOTHvIF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF=tbkD_C-MfIvDqTrP-trf5DCShUFsKhTJB2Q-XPoO3KJKKfTPMxO6KnbX5Jbh3-biWbRM2MbgylRp8P3y0bb2DUA1y4vpKbjP0eTxoUJ2XMKVDq5mqfCWMR-ebPRiJPQ9QgbqalQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0hI0ljj82e5PVKgTa54cbb4o2WbCQLn3T8pcN2b5oQT8XqJraaf3g-KAHLxOD0b3vOIJTXpOUWfAkXpJvQnJjt2JxaqRC5-olEl5jDh3MBpQDhtoJexIO2jvy0hvctn5cShnCyfjrDRLbXU6BK5vPbNcZ0l8K3l02V-bIe-t2XjQhDNtDt60jfn3aQ5rtKRTffjrnhPF3LJTQXP6-hnjy3bRHsxTt2q--MpP45qbtLP-UyN3MWh3RymJ42-39LPO2hpRjyxv4bnDAL4oxJpOJXaILWl52HlFWj43vbURvD-Lg3-7qBx5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoK0hJC-2bKvPKITD-tFO5eT22-usMNIJ2hcHMPoosIJ4qqrDMx3BMHDH-lJh0KviaKJjBMbUoqRHXnJi0btQDPvxBf7p55rP5q5TtUJM_UKzhfoMqfTbMlJyKMnitIT9-pnKWlQrh459XP68bTkA5bjZKxtq3mkjbPbDfn028DKuDTtajj3QeaRabK6aKC5bL6rJabC3ffcqXU6q2bDeQN3Helca2ePqKjC-bxOtEn3oyT3JXp0vWtv4WbbvLT7johRTWqR4ePoaLxonDh83Bn_L2xQJHmLOBt3O5hvvhb5O3M7-yfKmDloOW-TB5bbPLUQF5l8-sq0x0bOte-bQXH_E5bj2qRAq_ID23D; H_PS_PSSID=; BDORZ=FFFB88E999055A3F8A630C64834BD6D0; PSINO=5; BDSFRCVID_BFESS=uZAOJexroG0r-YbjD6F-2uSfCgKK0gOTDYLEOwXPsp3LGJLVgrUBEG0Pt_U-mEt-J8jwogKK0eOTHvIF_2uxOjjg8UtVJeC6EG0Ptf8g0M5; H_BDCLCKID_SF_BFESS=tbkD_C-MfIvDqTrP-trf5DCShUFsKhTJB2Q-XPoO3KJKKfTPMxO6KnbX5Jbh3-biWbRM2MbgylRp8P3y0bb2DUA1y4vpKbjP0eTxoUJ2XMKVDq5mqfCWMR-ebPRiJPQ9QgbqalQ7tt5W8ncFbT7l5hKpbt-q0x-jLTnhVn0MBCK0hI0ljj82e5PVKgTa54cbb4o2WbCQLn3T8pcN2b5oQT8XqJraaf3g-KAHLxOD0b3vOIJTXpOUWfAkXpJvQnJjt2JxaqRC5-olEl5jDh3MBpQDhtoJexIO2jvy0hvctn5cShnCyfjrDRLbXU6BK5vPbNcZ0l8K3l02V-bIe-t2XjQhDNtDt60jfn3aQ5rtKRTffjrnhPF3LJTQXP6-hnjy3bRHsxTt2q--MpP45qbtLP-UyN3MWh3RymJ42-39LPO2hpRjyxv4bnDAL4oxJpOJXaILWl52HlFWj43vbURvD-Lg3-7qBx5dtjTO2bc_5KnlfMQ_bf--QfbQ0hOhqP-jBRIEoK0hJC-2bKvPKITD-tFO5eT22-usMNIJ2hcHMPoosIJ4qqrDMx3BMHDH-lJh0KviaKJjBMbUoqRHXnJi0btQDPvxBf7p55rP5q5TtUJM_UKzhfoMqfTbMlJyKMnitIT9-pnKWlQrh459XP68bTkA5bjZKxtq3mkjbPbDfn028DKuDTtajj3QeaRabK6aKC5bL6rJabC3ffcqXU6q2bDeQN3Helca2ePqKjC-bxOtEn3oyT3JXp0vWtv4WbbvLT7johRTWqR4ePoaLxonDh83Bn_L2xQJHmLOBt3O5hvvhb5O3M7-yfKmDloOW-TB5bbPLUQF5l8-sq0x0bOte-bQXH_E5bj2qRAq_ID23D; delPer=0; BAIDUID_BFESS=46707C2D54CB50C4DCBCA857C300118B:FG=1; BA_HECTOR=05208085ah8k252g0hahaove1hnuhvl1e; ZFY=ZkfOgNUxZG1t0VfpuIkTS36g0D4SRDlUBbCB4F:BgSVI:C; BDRCVFR[GUNHjHUP8an]=mk3SLVN4HKm; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; userFrom=null; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm; indexPageSugList=%5B%22%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD%22%5D; cleanHistoryStatus=0; ab_sr=1.0.1_NWVhYTNkY2JhNDAwNGVhNzk2M2IxYWNlZTQ1YjA1MmFlYjRiOTlmZTc5YmQxMTU5MmI3NTczMTAxZDVlNDJhZGU4YjU0NzM5YWJjOWI1YjIxNTcyM2VjMzJhZjgyMzhhNDI0OGIxNDQ0YmM3N2NiNTk3MWYwMWFmMTc1MWQyOWE0NGZlMDgyNmNmNjczMTA4MGM1NGI4ODJiNmNkNTRlYg==");
        cookie.forEach((k,v) -> site.addCookie(k,v));*/
        return site;
    }

    //@PostConstruct
    public void start(){
        //系统配置文件
        System.setProperty("selenuim_config", "D:\\jsoup\\config.ini");
        Spider.create(new BaiduImgWebmagicTest())
                .addUrl("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1669299978381_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&dyTabStr=MCwzLDYsNCw1LDEsOCwyLDcsOQ%3D%3D&ie=utf-8&sid=&word=%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD")
                //线程
                .thread(2)
                /*
                 * 为 SeleniumDownloader 设置休眠时间：
                 * 当动态加载页面时，可能还存在部分数据没有加载完毕，为它设置休眠时间后，可保证有足够的时间，加载完
                 * https://blog.csdn.net/weixin_53782558/article/details/126594094
                 */
                .setDownloader(new SeleniumDownloader("D:\\jsoup\\chromedriver.exe").setSleepTime(1000))
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000))) // 去重过滤器
                .addPipeline(baiduImgPipelineData)
                .run();
    }

}
