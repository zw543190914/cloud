package com.zw.cloud.netty.web.controller.chat;

import com.zw.cloud.common.entity.vo.ImgAttachmentVO;
import com.zw.cloud.common.utils.FileUtils;
import com.zw.cloud.common.utils.bean.BeanUtils;
import com.zw.cloud.common.utils.selenium.BaiduImgSeleniumHtmlUtils;
import com.zw.cloud.netty.web.entity.chat.ImgAttachment;
import com.zw.cloud.netty.web.service.api.chat.IImgAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/html/selenium")
public class BaiduImgSeleniumHtmlController {

    @Autowired
    private IImgAttachmentService imgAttachmentService;

    @GetMapping("/queryImg/{key}")
    //http://localhost:18092/html/selenium/queryImg/青春校园
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
        imgAttachmentService.saveBatch(attachmentList);
        stopWatch.stop();
        log.info("[BaiduImgSeleniumHtmlController][parseHtml] stopWatch : {}",stopWatch.prettyPrint());
    }

    @GetMapping("/downloadImg")
    //http://localhost:18092/html/selenium/downloadImg
    public void downloadImg() {
        List<ImgAttachment> imgAttachmentList = imgAttachmentService.list();
        imgAttachmentList.parallelStream().forEach(baiduImgDTO -> FileUtils.downloadFileFromNet(baiduImgDTO.getUrl(),"D:\\img\\",baiduImgDTO.getTitle()));
    }
}
