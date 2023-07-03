package com.zw.cloud.websocket.web.service.api.chat;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.websocket.web.entity.chat.UserInfo;
import com.zw.cloud.websocket.web.entity.chat.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface IUserInfoService extends IService<UserInfo> {

    UserVo registerOrLogin(UserInfo user);

    UserVo getUserByUsername(String username);
}
