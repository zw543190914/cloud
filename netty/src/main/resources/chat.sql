CREATE DATABASE `chat` CHARACTER SET 'utf8mb4';

-- ----------------------------
-- Table structure for chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `chat_msg`;
CREATE TABLE `chat_msg` (
                            `id` bigint NOT NULL,
                            `send_user_id` varchar(64) NOT NULL COMMENT '消息发送人',
                            `accept_user_id` varchar(64) DEFAULT NULL COMMENT '消息接收人',
                            `msg` varchar(255) NOT NULL COMMENT '消息内容',
                            `accept_group_id` varchar(64) DEFAULT NULL COMMENT '消息群组',
                            `sign_flag` int NOT NULL COMMENT '消息是否签收(未签收:0,签收:1)',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            PRIMARY KEY (`id`),
                            KEY `idx_` (`accept_user_id`,`sign_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of chat_msg
-- ----------------------------

-- ----------------------------
-- Table structure for friends_request
-- ----------------------------
DROP TABLE IF EXISTS `friends_request`;
CREATE TABLE `friends_request` (
                                   `id` varchar(64) NOT NULL,
                                   `send_user_id` varchar(64) NOT NULL,
                                   `accept_user_id` varchar(64) NOT NULL,
                                   `request_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`id`),
                                   KEY `idx_accept_user_id` (`accept_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of friends_request
-- ----------------------------

-- ----------------------------
-- Table structure for my_friends
-- ----------------------------
DROP TABLE IF EXISTS `my_friend`;
CREATE TABLE `my_friend` (
                              `id` varchar(64) NOT NULL,
                              `my_user_id` varchar(64) NOT NULL COMMENT '用户id',
                              `my_friend_user_id` varchar(64) NOT NULL COMMENT '用户的好友id',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `my_user_id` (`my_user_id`,`my_friend_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of my_friends
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
                             `id` varchar(64) NOT NULL,
                             `username` varchar(20) NOT NULL COMMENT '用户名，账号',
                             `password` varchar(64) NOT NULL COMMENT '密码',
                             `face_image` varchar(255) NOT NULL COMMENT '我的头像，如果没有默认给一张',
                             `face_image_big` varchar(255) NOT NULL,
                             `nickname` varchar(20) NOT NULL COMMENT '昵称',
                             `qrcode` text COMMENT '新用户注册后默认后台生成二维码，并且上传到fastdfs',
                             `cid` varchar(64) DEFAULT NULL COMMENT '设备cid',
                             `status` int(1) NOT NULL DEFAULT 1 COMMENT '用户状态(锁定:0,激活:1)',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
