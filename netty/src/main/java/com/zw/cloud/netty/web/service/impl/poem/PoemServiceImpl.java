package com.zw.cloud.netty.web.service.impl.poem;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.netty.ide.annotation.IdeLock;
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
 * 服务实现类
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
    @Autowired
    private PoemMapper mapper;

    @Override
    public List<Poem> queryByTitleOrContent(String title) {
        return baseMapper.queryByTitleOrContent(title);
    }

    @Override
    @IdeLock(perFix = "netty_lock",timeOutSecond = 4,paramIndex = 0,useTryLock = false)
    @Transactional
    public int updatePoemById(Long id,String title) {
        Poem poem = baseMapper.selectById(id);
        log.info("[PoemServiceImpl][updatePoemById] poem is {}",JSON.toJSONString(poem));
        try {
            TimeUnit.SECONDS.sleep(3);
            poem.setTitle(title);
            return baseMapper.updateById(poem);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional
    public Poem testLock(Long id, String title) throws Exception {
        // 加锁应该放到事务之外,否则无法读取最新数据,锁没有起到作用  ---使用postman测试
        RLock lock = redissonClient.getLock(String.valueOf(id));
        lock.lock(2, TimeUnit.SECONDS);
        log.info("[PoemServiceImpl][testLock] has lock + " + Thread.currentThread().getName());

        Poem poem = mapper.queryByTitle(title);
        log.info("[PoemServiceImpl][testLock] poem is {}", JSON.toJSONString(poem));
        if (Objects.isNull(poem)) {
            poem = new Poem();
            poem.setId(id);
            poem.setTitle(title);
            baseMapper.insert(poem);
        }
        log.info("[PoemServiceImpl][testLock] end");
        TimeUnit.SECONDS.sleep(5);

        return poem;

    }
}
