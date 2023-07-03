package com.zw.cloud.netty.web.dao.chat;

import com.zw.cloud.netty.web.entity.chat.FriendsRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zw
 * @since 2022-12-05
 */
public interface FriendsRequestMapper extends BaseMapper<FriendsRequest> {

    @Delete("delete from friends_request\n" +
            "    where send_user_id = #{sendUserId,jdbcType=VARCHAR} and accept_user_id =#{acceptUserId,jdbcType=VARCHAR}")
    void deleteByFriendRequest(FriendsRequest friendsRequest);

}
