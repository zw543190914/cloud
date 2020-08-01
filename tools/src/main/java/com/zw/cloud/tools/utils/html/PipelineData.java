package com.zw.cloud.tools.utils.html;

import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class PipelineData implements Pipeline {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<User> user = resultItems.get("user");
        if (null != user){
            // todo 录入数据库
            userMapper.batchInsert(user);
        }
    }
}
