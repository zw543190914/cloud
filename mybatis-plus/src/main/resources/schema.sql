CREATE TABLE `user_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) NOT NULL,
                             `age` tinyint(4) DEFAULT NULL,
                             `bir` datetime DEFAULT NULL,
                             `other` json DEFAULT NULL,
                             `create_time` datetime NOT NULL,
                             `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
                             `deleted` int(11) NOT NULL DEFAULT '0',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1439464213926895618 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;