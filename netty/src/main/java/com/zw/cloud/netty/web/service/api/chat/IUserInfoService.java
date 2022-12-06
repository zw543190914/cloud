package com.zw.cloud.netty.web.service.api.chat;

import com.zw.cloud.netty.enums.SearchFriendsStatusEnum;
import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import com.zw.cloud.netty.web.entity.vo.UserVo;

import java.util.List;

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

    UserInfo checkUserNameIsExit(String username);


    /**
     * 搜索好友的前置条件
     */
    SearchFriendsStatusEnum preconditionSearchFriends(String myUserId, String friendUserName);

    /**
     * 发送好友请求
     */
    void sendFriendRequest(String myUserId,String friendUserName);

    /**
     * 好友请求列表查询
     */
    List<FriendsRequestVO> queryFriendRequestList(String acceptUserId);

    /**
     * 添加好友处理
     */
    List<MyFriendsVO> operFriendRequest(String acceptUserId, String sendUserId, Integer operType);

    List<MyFriendsVO> queryMyFriends(String userId);
}
