package com.zw.cloud.tools.service.impl;

import com.zw.cloud.tools.dao.UserMapper;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-08-29 21:02:42
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> queryAll() {
        return this.userDao.selectByExample(null);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insertSelective(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.updateByPrimaryKeySelective(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Map<String,Object> queryBySql(Long id) {
        //
        String sql = "select name as '姓名' from user where id = " + id;
        return this.userDao.selectBySql(sql);
    }

}