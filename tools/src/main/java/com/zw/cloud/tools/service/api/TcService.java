package com.zw.cloud.tools.service.api;

import com.github.pagehelper.PageInfo;
import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.entity.dto.QueryDTO;

import java.util.List;

/**
 * (Tc)表服务接口
 *
 * @author makejava
 * @since 2021-08-29 21:10:19
 */
public interface TcService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tc queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tc> queryAllByLimit(int offset, int limit);


    PageInfo<Tc> pageQuery(QueryDTO queryDTO);

    /**
     * 新增数据
     *
     * @param tc 实例对象
     * @return 实例对象
     */
    Tc insert(Tc tc);

    /**
     * 修改数据
     *
     * @param tc 实例对象
     * @return 实例对象
     */
    Tc update(Tc tc);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}