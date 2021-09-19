package com.zw.cloud.tools.service.impl;

import com.zw.cloud.tools.entity.Code;
import com.zw.cloud.tools.dao.CodeDao;
import com.zw.cloud.tools.service.CodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Code)表服务实现类
 *
 * @author makejava
 * @since 2021-08-29 20:29:48
 */
@Service
public class CodeServiceImpl implements CodeService {
    @Resource
    private CodeDao codeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Code queryById(Integer id) {
        return this.codeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Code> queryAllByLimit(int offset, int limit) {
        return this.codeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param code 实例对象
     * @return 实例对象
     */
    @Override
    public void insert(Code code) {
        this.codeDao.insert(code);
    }

    /**
     * 修改数据
     *
     * @param code 实例对象
     * @return 实例对象
     */
    @Override
    public Code update(Code code) {
        this.codeDao.update(code);
        return this.queryById(code.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.codeDao.deleteById(id) > 0;
    }
}