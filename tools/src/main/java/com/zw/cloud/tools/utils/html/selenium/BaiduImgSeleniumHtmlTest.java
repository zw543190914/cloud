package com.zw.cloud.tools.utils.html.selenium;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.entity.img.ImgAttachment;
import com.zw.cloud.tools.utils.html.jsoup.JsoupUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class BaiduImgSeleniumHtmlTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        parseHtml("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1669299978381_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&dyTabStr=MCwzLDYsNCw1LDEsOCwyLDcsOQ%3D%3D&ie=utf-8&sid=&word=%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD");

    }
    public static List<ImgAttachment> parseHtml(String url) throws IOException, InterruptedException {

        //参数配置
        //安装 https://blog.csdn.net/weixin_53782558/article/details/126594094
        //Selenium常用API https://blog.csdn.net/qq_22003641/article/details/79137327
        System.setProperty("webdriver.chrome.driver", "D:\\jsoup\\chromedriver.exe");
        RemoteWebDriver driver;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("no-sandbox");//禁用沙盒
        //通过ChromeOptions的setExperimentalOption方法，传下面两个参数来禁止掉谷歌受自动化控制的信息栏
        option.setExperimentalOption("useAutomationExtension", false);
        option.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        // 设置为 无界面浏览器 模式,若是不想看到浏览器打开，就可以配置此项
        // option.addArguments("--headless");
        // 设置浏览器窗口打开大小
        //option.addArguments("--window-size=1440,1080");
        driver = new ChromeDriver(option);
        /*HashMap<String, String> cookieMap = convertCookie("BIDUPSID=2AFB07AAF432E35E16E9DF2B2EE6194D; PSTM=1632027488; __yjs_duid=1_2c350862ef0197d857266345b41bae141632059885908; BAIDUID=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BDUSS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; BDUSS_BFESS=G44MXpPcXdwbE9jbm9oT0ptM1hpdzZ5dkg5OGdIVVBMMHlyb2dxeldoaVAxakZqSVFBQUFBJCQAAAAAAAAAAAEAAAAQbBmwenc1NDMxOTA5MTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI9JCmOPSQpjR; H_WISE_SIDS_BFESS=107317_110085_127969_164869_178384_178604_179345_179466_181484_181589_182273_182530_183327_184012_184321_184793_184893_184893_185268_185517_186052_186159_186317_186597_186636_186664_186716_186840_186844_187062_187091_187205_187282_187292_187356_187450_187485_187543_187567_187669_187726_187819_187928_188182_188295_188331_188427_188591_188614_188731_188746_188841_188935_188995_189058_189104_189325_189390_189415_189468_189625_189680_189716_189731_189755_190033_190048_190113_190169_190189_190297_190506_190521_190624_190684; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; firstShowTip=1; cleanHistoryStatus=0; H_PS_PSSID=36545_37559_37519_37732_37771_37777_37721_37715_37741_26350_37790; delPer=0; PSINO=5; BAIDUID_BFESS=F16E031FAA6029ECC4C24106801BC0C4:FG=1; BA_HECTOR=0k2l2ga5050g8l848l85896g1hnuvat1e; ZFY=xc:AUTxDt8gDypgjy793e5NCjhUCKkdRhRnFzroB9:BJA:C; userFrom=null; BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; BDRCVFR[X_XKQks0S63]=mk3SLVN4HKm; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm; indexPageSugList=%5B%22%E9%9D%92%E6%98%A5%E6%A0%A1%E5%9B%AD%22%2C%22%E9%9D%92%E6%98%A5%E5%9B%BE%E7%89%87%22%2C%22java%22%2C%22%E5%94%AF%E7%BE%8E%E5%9B%BE%E7%89%87%20%E6%84%8F%E5%A2%83%22%2C%22%E5%BE%AE%E4%BF%A1%E5%B0%8F%E7%A8%8B%E5%BA%8F%E8%B0%83%E7%94%A8%E6%89%8B%E6%9C%BA%E6%91%84%E5%83%8F%E5%A4%B4%E6%89%AB%E4%B8%80%E4%B8%8B%22%2C%22%E6%B8%85%E7%BA%AF%E6%A0%A1%E5%9B%AD%22%2C%22%E8%AF%9B%E4%BB%99%E5%8A%A8%E6%BC%AB%22%2C%22%E8%AF%9B%E4%BB%99%22%2C%22%E7%A2%A7%E7%91%B6%E5%8A%A8%E6%BC%AB%22%5D; ab_sr=1.0.1_YmQwMGY4NzczNmQ5ODIxNTYwMDhmNDhkYmFlYzdlMzNjYTA1OTFlMGVlMTgwZmJjNTBjNGJhNDEwYjAyMzA0Y2Q4NTc5NDliM2Q2OGVlMzI0Zjc4NzhhMTQ1ZjY4NGYzMzBlZDQ2OGVhODYzMmU1OTk3YWI0YmQxZjczZDIyZWRiNmFjYjEzMGJhODJhZjE2MDM2Y2U5MDVlM2MwZjNlYQ==");
        cookieMap.forEach((k,v) -> {
            driver.manage().addCookie(new Cookie(k,v));
        });*/

        driver.get(url);
        //等待打开页面
        Thread.sleep(3000);
        // A 滚动鼠标
        // 实例化Actions类对象:actions,并将driver传给actions
        /*for (int i = 0; i < 15; i++) {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.DOWN);
            actions.perform();
            TimeUnit.MILLISECONDS.sleep(50);
        }*/
        // B 直接使用 driver 操作
        for (int i = 0; i < 10; i++) {
            //需要滚动到页面的底部,获取完整的数据
            driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 1000)");
            //等待滚动完成
            Thread.sleep(2000);
        }

        String html = driver.getPageSource();
        //log.info("[BaiduImgSeleniumHtmlTest][getHtml] html:{}", html);
        List<ImgAttachment> result = new ArrayList<>(250);
        List<ImgAttachment> baiduImgDTOS = JsoupUtils.jsoupParseHtml(html,result);
        log.info("[BaiduImgSeleniumHtmlTest][getHtml] baiduImgDTOS.size = {},baiduImgDTOS is {}", baiduImgDTOS.size(), JSON.toJSONString(baiduImgDTOS));
        driver.close();
        return baiduImgDTOS;
    }

}
