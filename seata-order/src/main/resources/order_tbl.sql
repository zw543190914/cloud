CREATE TABLE `order_tbl` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                             `count` int NOT NULL DEFAULT '0',
                             `money` int NOT NULL DEFAULT '0',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_user_code` (`user_id`,`commodity_code`)
) ENGINE=InnoDB COMMENT='订单表';