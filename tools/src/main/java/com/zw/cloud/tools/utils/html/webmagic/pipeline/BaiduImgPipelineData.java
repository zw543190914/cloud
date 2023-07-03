package com.zw.cloud.tools.utils.html.webmagic.pipeline;



import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.entity.img.ImgAttachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;


@Component
@Slf4j
public class BaiduImgPipelineData implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        List<ImgAttachment> imgDTOList = resultItems.get("imgDTOList");
        log.info("[BaiduImgPipelineData][process]imgDTOList is {}", JSON.toJSONString(imgDTOList));
        if (CollectionUtils.isNotEmpty(imgDTOList)){
            // todo 录入数据库
        }
    }
}
