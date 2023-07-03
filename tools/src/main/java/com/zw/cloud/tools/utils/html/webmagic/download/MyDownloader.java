package com.zw.cloud.tools.utils.html.webmagic.download;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

public class MyDownloader implements Downloader {
    //声明驱动
    private RemoteWebDriver driver;

    public MyDownloader() {
        System.setProperty("webdriver.chrome.driver","D:\\jsoup\\chromedriver.exe");
        //创建浏览器参数对象
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置为 无界面浏览器 模式,若是不想看到浏览器打开，就可以配置此项
        // chromeOptions.addArguments("--headless");
        // 设置浏览器窗口打开大小
        chromeOptions.addArguments("--window-size=1440,1080");
        //创建驱动
        this.driver = new ChromeDriver(chromeOptions);
    }

    /**
     * 由于selenium的默认域名为data;因此第一次必须跳转到登录页，才能加入对应域名
     */
    @Override
    public Page download(Request request, Task task) {
        try {
            //第一次打开url，跳转到登录页
            driver.get(request.getUrl());
            //等待打开浏览器
            Thread.sleep(3000);
            //需要滚动到页面的底部,获取完整的数据
            driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 1000)");
            //等待滚动完成
            Thread.sleep(2000);
            //获取页面，打包成Page对象，传给PageProcessor 实现类
            Page page = createPage(request.getUrl(), driver.getPageSource());
            //看需要是否关闭浏览器
            //driver.close();
            return page;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setThread(int threadNum) {

    }

    //构建page返回对象
    private Page createPage(String url, String content) {
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(url));
        page.setRequest(new Request(url));
        page.setDownloadSuccess(true);
        return page;
    }
}

