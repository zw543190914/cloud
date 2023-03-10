CREATE DATABASE `chat` CHARACTER SET 'utf8mb4';


DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
                             `id` bigint NOT NULL,
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
