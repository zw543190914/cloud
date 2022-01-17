package com.zw.cloud.tools.service.impl.poem;

import com.zw.cloud.tools.entity.poem.Poem;
import com.zw.cloud.tools.dao.poem.PoemMapper;
import com.zw.cloud.tools.service.api.poem.IPoemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
