package com.zw.cloud.websocket.web.service.impl.chat;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.common.utils.EncryptUtils;
import com.zw.cloud.websocket.web.entity.chat.UserInfo;
import com.zw.cloud.websocket.web.entity.chat.UserVo;
import com.zw.cloud.websocket.web.mapper.chat.UserInfoMapper;
import com.zw.cloud.websocket.web.service.api.chat.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    @Override
    @Transactional
    public UserVo registerOrLogin(UserInfo user) {
        log.info("[websocket][UserInfoServiceImpl][registerOrLogin]user is {}", JSON.toJSONString(user));

        UserInfo userResult = checkUserNameIsExit(user.getUsername());
        UserVo userVo = new UserVo();
        if (Objects.nonNull(userResult)) {
            // 登录
            if (userResult.getStatus() == 0) {
                throw new BizException("账号已经被锁定");
            }
            //此用户存在，可登录
            if (!StringUtils.equals(userResult.getPassword(), EncryptUtils.encrypted(user.getPassword()))) {
                throw new BizException("密码错误 或者 用户名已经被注册");
            }
            BeanUtils.copyProperties(userResult, userVo);
            log.info("[websocket][UserInfoServiceImpl][registerOrLogin]userVo is {}", JSON.toJSONString(userVo));
            return userVo;
        }

        //注册

        user.setNickname(user.getUsername());
        user.setPassword(EncryptUtils.encrypted(user.getPassword()));
        save(user);
        BeanUtils.copyProperties(user, userVo);
        log.info("[websocket][UserInfoServiceImpl][registerOrLogin]userVo is {}", JSON.toJSONString(userVo));
        return userVo;
    }

    @Override
    public UserVo getUserByUsername(String username) {
        UserInfo userInfo = checkUserNameIsExit(username);
        if (Objects.isNull(userInfo)) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo,userVo);
        return userVo;
    }

    private UserInfo checkUserNameIsExit(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));
    }

}
