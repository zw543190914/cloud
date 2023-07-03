package com.zw.cloud.netty.web.dao.chat;

import com.zw.cloud.netty.web.entity.chat.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zw.cloud.netty.web.entity.vo.FriendsRequestVO;
import com.zw.cloud.netty.web.entity.vo.MyFriendsVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select u.id as sendUserId, u.username as sendUsername, u.face_image as sendFaceImage, u.nickname as sendNickname from friends_request fr\n" +
            " left join user_info u on fr.send_user_id = u.id where fr.accept_user_id = #{acceptUserId};")
    List<FriendsRequestVO> queryFriendRequestList(Long acceptUserId);

    @Select("select u.id as friendUserId, u.username as friendUsername,u.face_image as friendFaceImage,u.nickname as friendNickname\n" +
            "from my_friend mf left join user_info u on u.id = mf.my_friend_user_id\n" +
            "where mf.my_user_id = #{userId}")
    List<MyFriendsVO> queryMyFriends(Long userId);

}
