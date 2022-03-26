CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `bir` datetime DEFAULT NULL,
                        `username` varchar(255)  NOT NULL,
                        `password` varchar(255)  NOT NULL,
                        `remark` varchar(255) DEFAULT '',
                        `tel` varchar(16) DEFAULT '',
                        `status` tinyint(4) NOT NULL DEFAULT '0',
                        `other` json DEFAULT NULL,
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `create_user` varchar(255) DEFAULT '' COMMENT '创建用户',
                        `update_user` varchar(255) DEFAULT '' COMMENT '修改用户',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_tel` (`tel`)
) ENGINE=InnoDB COMMENT='用户表';

CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) DEFAULT NULL,
                             `role_id` int(11) DEFAULT NULL,
                             `remark` varchar(255) DEFAULT NULL,
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `create_user` varchar(255) DEFAULT '' COMMENT '创建用户',
                             `update_user` varchar(255) DEFAULT '' COMMENT '修改用户',
                             PRIMARY KEY (`id`),
                             KEY `idx_user_id` (`user_id`),
                             KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB COMMENT='用户表角色关联表';

CREATE TABLE `role` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `role_name` varchar(255) DEFAULT NULL,
                        `remark` varchar(255) DEFAULT NULL,
                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `create_user` varchar(255) DEFAULT '' COMMENT '创建用户',
                        `update_user` varchar(255) DEFAULT '' COMMENT '修改用户',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_role_name` (`role_name`)
) ENGINE=InnoDB COMMENT='角色表';

CREATE TABLE `role_resource` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `role_id` int(11) DEFAULT NULL,
                                 `resource_id` int(11) DEFAULT NULL,
                                 `remark` varchar(255) DEFAULT NULL,
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `create_user` varchar(255) DEFAULT '' COMMENT '创建用户',
                                 `update_user` varchar(255) DEFAULT '' COMMENT '修改用户',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB COMMENT='角色资源表';

CREATE TABLE `resource` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            `resource_uri` varchar(255) DEFAULT NULL,
                            `remark` varchar(255) DEFAULT NULL,
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `create_user` varchar(255) DEFAULT '' COMMENT '创建用户',
                            `update_user` varchar(255) DEFAULT '' COMMENT '修改用户',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_resiurce_uri` (`resource_uri`)
) ENGINE=InnoDB COMMENT='资源表';