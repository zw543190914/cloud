
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `username` varchar(32)  NOT NULL COMMENT '用户名称',
                            `password` varchar(128)  NOT NULL COMMENT '密码',
                            `status` int DEFAULT '1' COMMENT '1开启0关闭',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `last_password_reset_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '重置密码时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB;
-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$VplTpXdKocW/jPuNYbeH8u.NdOazZ/2HI/MzcC7B/2MpLfm.AWcQ.', 1,now(),now(),now());
INSERT INTO `sys_user` VALUES (2, 'zw', '$2a$10$jYSx6BFRMcNIygQcunsXre7H6VG18WNyp35JM2kJL09ChM91U6hTK', 1,now(),now(),now());

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                             `role_name` varchar(64)  NULL DEFAULT NULL COMMENT '角色名称',
                             `role_description` varchar(128)  NULL DEFAULT NULL COMMENT '角色描述',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'USER', '基本角色',now(),now());
INSERT INTO `sys_role` VALUES (2, 'ADMIN', '超级管理员',now(),now());
INSERT INTO `sys_role` VALUES (3, 'PRODUCT', '管理产品',now(),now());
INSERT INTO `sys_role` VALUES (4, 'ORDER', '管理订单',now(),now());
-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB ;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1,now(),now());
INSERT INTO `sys_user_role` VALUES (2, 1,now(),now());
INSERT INTO `sys_user_role` VALUES (1, 3,now(),now());
INSERT INTO `sys_user_role` VALUES (2, 4,now(),now());
