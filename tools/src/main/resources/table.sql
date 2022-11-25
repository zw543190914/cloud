CREATE TABLE `img_attachment` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '下载地址',
                                  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
                                  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '类型',
                                  `remark` varchar(255)NOT NULL DEFAULT '' COMMENT '备注',
                                  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='图片表';