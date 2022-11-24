package com.zw.cloud.tools.utils.html;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.common.entity.dto.jsoup.BaiduImgDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JsoupUtils {
    public static void main(String[] args) throws Exception {

        //List<BaiduImgDTO> baiduImgDTOS = jsoupParseBaiduImg("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1669299978381_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&dyTabStr=MCwzLDYsNCw1LDEsOCwyLDcsOQ%3D%3D&ie=utf-8&sid=&word=%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD");
        getHtml("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1669299978381_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&dyTabStr=MCwzLDYsNCw1LDEsOCwyLDcsOQ%3D%3D&ie=utf-8&sid=&word=%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD");
        /*ArrayList<String> pags1 = Lists.newArrayList("http://www.mmonly.cc/wmtp/wmxz/list_27_1.html", "http://www.mmonly.cc/wmtp/qltp/list_22_2.html");
        jsoupHtmlParse(pags1);*/
    }

    public static void downloadFileFromNet(String url, String targetFilePath, String fileName) throws Exception {
       /* int i = url.lastIndexOf(".");
        String fileType = url.substring(i + 1);*/
        HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
        httpUrl.connect();
        InputStream inputStream = httpUrl.getInputStream();
        ReadableByteChannel inChannel = Channels.newChannel(inputStream);

        //FileChannel outChannel = new FileOutputStream("d:/test." + fileType).getChannel();
        FileChannel outChannel = new FileOutputStream(targetFilePath + "/" + fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
    }

    public static void getHtml(String url) throws IOException, InterruptedException {
        //参数配置
        //安装 ttps://blog.csdn.net/qq_22003641/article/details/79137327
        //Selenium常用API https://blog.csdn.net/qq_22003641/article/details/79137327
        System.setProperty("webdriver.chrome.driver", "D:\\jsoup\\chromedriver.exe");
        WebDriver driver;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("no-sandbox");//禁用沙盒
        //通过ChromeOptions的setExperimentalOption方法，传下面两个参数来禁止掉谷歌受自动化控制的信息栏
        option.setExperimentalOption("useAutomationExtension", false);
        option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(option);
        /*HashMap<String, String> cookieMap = convertCookie("BIDUPSID=2AFB07AAF432E35E16E9DF2B2EE6194D; PSTM=1632027488; __yjs_duid=1_2c350862ef0197d857266345b41bae141632059885908; BAIDUID=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BDUSS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; BDUSS_BFESS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; H_WISE_SIDS_BFESS=107317_110085_127969_164869_178384_178604_179345_179466_181484_181589_182273_182530_183327_184012_184321_184793_184893_184893_185268_185517_186052_186159_186317_186597_186636_186664_186716_186840_186844_187062_187091_187205_187282_187292_187356_187450_187485_187543_187567_187669_187726_187819_187928_188182_188295_188331_188427_188591_188614_188731_188746_188841_188935_188995_189058_189104_189325_189390_189415_189468_189625_189680_189716_189731_189755_190033_190048_190113_190169_190189_190297_190506_190521_190624_190684; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; firstShowTip=1; cleanHistoryStatus=0; H_PS_PSSID=36545_37559_37519_37732_37771_37777_37721_37715_37741_26350_37790; delPer=0; PSINO=5; BAIDUID_BFESS=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BA_HECTOR=0k2l2ga5050g8l848l85896g1hnuvat1e; ZFY=xc:AUTxDt8gDypgjy793e5NCjhUCKkdRhRnFzroB9:BJA:C; userFrom=null; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; BDRCVFR[X_XKQks0S63]=mk3SLVN4HKm; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm; indexPageSugList=%5B%22%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD%22%2C%22%E9%9D%92%E6%98%A5%E5%9B%BE%E7%89%87%22%2C%22java%22%2C%22%E5%94%AF%E7%BE%8E%E5%9B%BE%E7%89%87%20%E6%84%8F%E5%A2%83%22%2C%22%E5%BE%AE%E4%BF%A1%E5%B0%8F%E7%A8%8B%E5%BA%8F%E8%B0%83%E7%94%A8%E6%89%8B%E6%9C%BA%E6%91%84%E5%83%8F%E5%A4%B4%E6%89%AB%E4%B8%80%E4%B8%8B%22%2C%22%E6%B8%85%E7%BA%AF%E6%A0%A1%E5%9B%AD%22%2C%22%E8%AF%9B%E4%BB%99%E5%8A%A8%E6%BC%AB%22%2C%22%E8%AF%9B%E4%BB%99%22%2C%22%E7%A2%A7%E7%91%B6%E5%8A%A8%E6%BC%AB%22%5D; ab_sr=1.0.1_YmQwMGY4NzczNmQ5ODIxNTYwMDhmNDhkYmFlYzdlMzNjYTA1OTFlMGVlMTgwZmJjNTBjNGJhNDEwYjAyMzA0Y2Q4NTc5NDliM2Q2OGVlMzI0Zjc4NzhhMTQ1ZjY4NGYzMzBlZDQ2OGVhODYzMmU1OTk3YWI0YmQxZjczZDIyZWRiNmFjYjEzMGJhODJhZjE2MDM2Y2U5MDVlM2MwZjNlYQ==");
        cookieMap.forEach((k,v) -> {
            driver.manage().addCookie(new Cookie(k,v));
        });*/

        driver.get(url);
        // 滚动鼠标
        // 实例化Actions类对象:actions,并将driver传给actions
        for (int i = 0; i < 15; i++) {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.DOWN);
            actions.perform();
            TimeUnit.MILLISECONDS.sleep(50);
        }

        String html = driver.getPageSource();
        log.info("[getHtml] html:{}", html);
        List<BaiduImgDTO> baiduImgDTOS = jsoupParseHtml(html);
        log.info("[getHtml] baiduImgDTOS.size = {},baiduImgDTOS is {}", baiduImgDTOS.size(),JSON.toJSONString(baiduImgDTOS));
        driver.close();
    }

    public static List<BaiduImgDTO> jsoupParseBaiduImg(String originalUrl) throws IOException, InterruptedException {
        List<BaiduImgDTO> result = new ArrayList<>(200);

        HashMap<String, String> cookieMap = convertCookie("BIDUPSID=2AFB07AAF432E35E16E9DF2B2EE6194D; PSTM=1632027488; __yjs_duid=1_2c350862ef0197d857266345b41bae141632059885908; BAIDUID=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BDUSS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; BDUSS_BFESS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; H_WISE_SIDS_BFESS=107317_110085_127969_164869_178384_178604_179345_179466_181484_181589_182273_182530_183327_184012_184321_184793_184893_184893_185268_185517_186052_186159_186317_186597_186636_186664_186716_186840_186844_187062_187091_187205_187282_187292_187356_187450_187485_187543_187567_187669_187726_187819_187928_188182_188295_188331_188427_188591_188614_188731_188746_188841_188935_188995_189058_189104_189325_189390_189415_189468_189625_189680_189716_189731_189755_190033_190048_190113_190169_190189_190297_190506_190521_190624_190684; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; firstShowTip=1; cleanHistoryStatus=0; H_PS_PSSID=36545_37559_37519_37732_37771_37777_37721_37715_37741_26350_37790; delPer=0; PSINO=5; BAIDUID_BFESS=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BA_HECTOR=0k2l2ga5050g8l848l85896g1hnuvat1e; ZFY=xc:AUTxDt8gDypgjy793e5NCjhUCKkdRhRnFzroB9:BJA:C; userFrom=null; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; BDRCVFR[X_XKQks0S63]=mk3SLVN4HKm; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm; indexPageSugList=%5B%22%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD%22%2C%22%E9%9D%92%E6%98%A5%E5%9B%BE%E7%89%87%22%2C%22java%22%2C%22%E5%94%AF%E7%BE%8E%E5%9B%BE%E7%89%87%20%E6%84%8F%E5%A2%83%22%2C%22%E5%BE%AE%E4%BF%A1%E5%B0%8F%E7%A8%8B%E5%BA%8F%E8%B0%83%E7%94%A8%E6%89%8B%E6%9C%BA%E6%91%84%E5%83%8F%E5%A4%B4%E6%89%AB%E4%B8%80%E4%B8%8B%22%2C%22%E6%B8%85%E7%BA%AF%E6%A0%A1%E5%9B%AD%22%2C%22%E8%AF%9B%E4%BB%99%E5%8A%A8%E6%BC%AB%22%2C%22%E8%AF%9B%E4%BB%99%22%2C%22%E7%A2%A7%E7%91%B6%E5%8A%A8%E6%BC%AB%22%5D; ab_sr=1.0.1_YmQwMGY4NzczNmQ5ODIxNTYwMDhmNDhkYmFlYzdlMzNjYTA1OTFlMGVlMTgwZmJjNTBjNGJhNDEwYjAyMzA0Y2Q4NTc5NDliM2Q2OGVlMzI0Zjc4NzhhMTQ1ZjY4NGYzMzBlZDQ2OGVhODYzMmU1OTk3YWI0YmQxZjczZDIyZWRiNmFjYjEzMGJhODJhZjE2MDM2Y2U5MDVlM2MwZjNlYQ==");
        Document doc = Jsoup.connect(originalUrl).cookies(cookieMap)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .timeout(10000).get();

        for (int i = 1; i <= 20; i++) {
            Elements ul = doc.getElementsByClass("imglist clearfix pageNum" + i);
            Elements a = ul.select("a");
            for (Element e : a) {
                String href = e.attr("href");
                String title = e.text();
                log.info("[jsoupParseBaiduImg] href:{},title:{}", href, title);
                Document imgDoc = Jsoup.connect(href)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                        .timeout(10000).get();
                Element srcPic = imgDoc.getElementById("srcPic");
                Elements img = srcPic.select("img");
                String src = img.attr("src");
                if (StringUtils.isBlank(src) || !StringUtils.startsWith(src, "http")) {
                    continue;
                }
                log.info("[jsoupParseBaiduImg] src:{}", src);
                BaiduImgDTO imgDTO = new BaiduImgDTO();
                imgDTO.setTitle(title).setUrl(src);
                result.add(imgDTO);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
        return result;

    }

    public static List<BaiduImgDTO> jsoupParseHtml(String html) throws IOException, InterruptedException {
        List<BaiduImgDTO> result = new ArrayList<>(20);

        Document doc = Jsoup.parse(html);
        Elements ul = doc.getElementsByClass("imglist clearfix pageNum0");
        Elements li = ul.select("li");
        for (Element l : li) {
            Elements a = l.select("a");
            Element e = a.get(1);


            String href = e.attr("href");
            String title = e.ownText();
            log.info("[jsoupParseBaiduImg] href:{},title:{}", href, title);
            Document imgDoc = Jsoup.connect("https://image.baidu.com" + href)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36")
                    .timeout(10000).get();
            Element srcPic = imgDoc.getElementById("srcPic");
            if (Objects.isNull(srcPic)) {
                continue;
            }
            Elements img = srcPic.select("img");

            String src = img.attr("src");
            if (StringUtils.isBlank(src) || !StringUtils.startsWith(src, "http")) {
                continue;
            }
            log.info("[jsoupParseBaiduImg] src:{}", src);
            BaiduImgDTO imgDTO = new BaiduImgDTO();
            imgDTO.setTitle(title).setUrl(src);
            result.add(imgDTO);
        }
        return result;
    }

    public static List<String> jsoupHtmlParse(List<String> pags) throws IOException {
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
                log.info("[jsoup] href:{}", href);
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
                            log.info("[jsoup] url:{},totalpage is {}", url, totalpage);
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
                                log.info("[jsoup] src:{}", src);
                                srcList.add(src);
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (IOException ioException) {
                                log.warn("[jsoup] url:{} exception ", url, ioException);
                            }
                        }
                    }
                } catch (Exception exception) {
                    log.warn("[jsoup]out href:{},exception ", href, exception);
                }
            }
        }
        return srcList;
    }

    public static HashMap<String, String> convertCookie(String cookie) {
        HashMap<String, String> cookiesMap = new HashMap<>();
        String[] items = cookie.trim().split(";");
        for (String item : items) {
            cookiesMap.put(item.split("=")[0], item.split("=")[1]);
        }
        return cookiesMap;
    }


}
