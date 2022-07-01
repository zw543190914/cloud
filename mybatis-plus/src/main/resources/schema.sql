CREATE TABLE `user_info` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(128) NOT NULL DEFAULT '' COMMENT '姓名',
                             `bir` date DEFAULT NULL COMMENT '生日',
                             `age` tinyint DEFAULT NULL COMMENT '年龄',
                             `description` varchar(255) DEFAULT NULL COMMENT '描述',
                             `other` json DEFAULT NULL COMMENT '其他',
                             `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             `org_code` varchar(255) NOT NULL DEFAULT '' COMMENT '组织id',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='用户表';