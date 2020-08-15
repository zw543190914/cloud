package com.zw.cloud.tools.utils.html;

import com.zw.cloud.db.dao.TcMapper;
import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.Tc;
import com.zw.cloud.db.entity.TcExample;
import com.zw.cloud.db.entity.User;
import org.apache.commons.collections4.CollectionUtils;
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
    @Autowired
    private TcMapper tcMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Tc> tcList = resultItems.get("tcList");
        if (CollectionUtils.isNotEmpty(tcList)){
            // todo 录入数据库
            tcMapper.batchInsert(tcList);
        }
    }
}
