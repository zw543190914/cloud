package com.zw.cloud.netty.web.service.impl.poem;

import com.zw.cloud.netty.web.entity.poem.Poem;
import com.zw.cloud.netty.web.dao.poem.PoemMapper;
import com.zw.cloud.netty.web.service.api.poem.IPoemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
@Service
public class PoemServiceImpl extends ServiceImpl<PoemMapper, Poem> implements IPoemService {


    @Override
    public List<Poem> queryByTitleOrContent(String title){
        return baseMapper.queryByTitleOrContent(title);
    }
}
