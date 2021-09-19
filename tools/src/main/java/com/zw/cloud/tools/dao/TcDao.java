package com.zw.cloud.tools.dao;

import com.zw.cloud.tools.entity.Tc;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Tc)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-29 21:10:16
 */
public interface TcDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tc queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tc> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tc 实例对象
     * @return 对象列表
     */
    List<Tc> queryAll(Tc tc);

    /**
     * 新增数据
     *
     * @param tc 实例对象
     * @return 影响行数
     */
    int insert(Tc tc);

    /**
     * 修改数据
     *
     * @param tc 实例对象
     * @return 影响行数
     */
    int update(Tc tc);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}