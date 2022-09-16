package com.zw.cloud.netty.web.service.impl.poem;

import com.zw.cloud.netty.web.entity.poem.Poem;
import com.zw.cloud.netty.web.dao.poem.PoemMapper;
import com.zw.cloud.netty.web.service.api.poem.IPoemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
@Slf4j
@Service
public class PoemServiceImpl extends ServiceImpl<PoemMapper, Poem> implements IPoemService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<Poem> queryByTitleOrContent(String title){
        return baseMapper.queryByTitleOrContent(title);
    }

    @Override
    @Transactional
    public Poem testLock(Long id,String title) throws Exception{
        // 加锁应该放到事务之外,否则无法读取最新数据,锁没有起到作用
        RLock lock = redissonClient.getLock(String.valueOf(id));
        lock.lock();
        try {
            Poem poem = baseMapper.selectById(id);
            if (Objects.isNull(poem)) {
                log.info("[PoemServiceImpl][testLock] poem is null");
                poem = new Poem();
                poem.setId(id);
                poem.setTitle(title);
                baseMapper.insert(poem);
            }
            TimeUnit.SECONDS.sleep(3);
            return poem;
        } finally {
            lock.unlock();
        }
    }
}
