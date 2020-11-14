package com.zw.cloud.impl;

import com.zw.cloud.entity.UserPlus;
import com.zw.cloud.dao.UserPlusMapper;
import com.zw.cloud.service.IUserPlusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zw
 * @since 2020-11-14
 */
@Service
public class UserPlusServiceImpl extends ServiceImpl<UserPlusMapper, UserPlus> implements IUserPlusService {

}
