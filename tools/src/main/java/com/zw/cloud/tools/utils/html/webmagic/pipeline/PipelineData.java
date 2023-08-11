package com.zw.cloud.tools.utils.html.webmagic.pipeline;



import com.zw.cloud.tools.entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class PipelineData implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<User> userList = resultItems.get("userList");
        if (CollectionUtils.isNotEmpty(userList)){
            // todo 录入数据库
        }
    }
}
