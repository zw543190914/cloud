package com.zw.cloud.tools.service.impl;

import com.zw.cloud.tools.entity.Tc;
import com.zw.cloud.tools.dao.TcDao;
import com.zw.cloud.tools.service.TcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tc)表服务实现类
 *
 * @author makejava
 * @since 2021-08-29 21:10:20
 */
@Service("tcService")
public class TcServiceImpl implements TcService {
    @Resource
    private TcDao tcDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tc queryById(Integer id) {
        return this.tcDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Tc> queryAllByLimit(int offset, int limit) {
        return this.tcDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tc 实例对象
     * @return 实例对象
     */
    @Override
    public Tc insert(Tc tc) {
        this.tcDao.insert(tc);
        return tc;
    }

    /**
     * 修改数据
     *
     * @param tc 实例对象
     * @return 实例对象
     */
    @Override
    public Tc update(Tc tc) {
        this.tcDao.update(tc);
        return this.queryById(tc.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tcDao.deleteById(id) > 0;
    }
}