package com.zw.cloud.netty.web.service.api.poem;

import com.zw.cloud.netty.web.entity.poem.Poem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-01-17
 */
public interface IPoemService extends IService<Poem> {

    List<Poem> queryByTitleOrContent(String title);

    Poem testLock(Long id,String title) throws Exception;
}
