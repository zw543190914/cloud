package com.zw.cloud.tools.controller.html;

import com.google.common.collect.Lists;
import com.zw.cloud.common.entity.vo.ImgAttachmentVO;
import com.zw.cloud.common.utils.file.FileUtils;
import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.common.utils.selenium.BaiduImgSeleniumHtmlUtils;
import com.zw.cloud.tools.entity.img.ImgAttachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/html/selenium")
public class BaiduImgSeleniumHtmlController {


    @GetMapping("/queryImg/{key}")
    //http://localhost:9040/html/selenium/queryImg/青春校园
    public void queryImg(@PathVariable String key) throws IOException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("t1");
        String url = "https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1669299978381_R&pv=&ic=0&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&dyTabStr=MCwzLDYsNCw1LDEsOCwyLDcsOQ%3D%3D&ie=utf-8&sid=&word=" + key;
        List<ImgAttachmentVO> imgDTOList = BaiduImgSeleniumHtmlUtils.parseHtml(url);
        stopWatch.stop();
        stopWatch.start("t2");
        if (CollectionUtils.isEmpty(imgDTOList)) {
            return;
        }
        List<ImgAttachment> attachmentList = imgDTOList.stream().map(imgAttachment -> {
            ImgAttachment attachment = new ImgAttachment();
            BeanUtils.copyIgnoreNullValue(imgAttachment, attachment);
            imgAttachment.setType(key);
            return attachment;
        }).collect(Collectors.toList());
        stopWatch.stop();
        stopWatch.start("t3");
        imgDTOList.parallelStream().forEach(baiduImgDTO -> FileUtils.downLoadImage(baiduImgDTO.getUrl(),"D:\\img\\",baiduImgDTO.getTitle()));
        stopWatch.stop();
        log.info("[BaiduImgSeleniumHtmlController][parseHtml] stopWatch : {}",stopWatch.prettyPrint());
    }

    @GetMapping("/downloadImg")
    //http://localhost:9040/html/selenium/downloadImg
    public void downloadImg() {
        //List<ImgAttachment> imgAttachmentList = imgAttachmentService.list();
        List<ImgAttachment> imgAttachmentList = Lists.newArrayList();
        imgAttachmentList.parallelStream().forEach(baiduImgDTO -> FileUtils.downloadFileFromNet(baiduImgDTO.getUrl(),"D:\\img\\",baiduImgDTO.getTitle()));
    }
}
