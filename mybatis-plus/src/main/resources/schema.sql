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

CREATE TABLE `base_tender_craft` (
                                     `id` bigint NOT NULL COMMENT '主键',
                                     `customer_no` varchar(50) NOT NULL DEFAULT '' COMMENT '客户编号',
                                     `customer_name` varchar(64) NOT NULL DEFAULT '' COMMENT '客户名称',
                                     `fabric_name` varchar(255) NOT NULL DEFAULT '' COMMENT '坯布名称',
                                     `fabric_no` varchar(255) NOT NULL DEFAULT '' COMMENT '坯布编号',
                                     `color_no` varchar(50) DEFAULT '' COMMENT '颜色编号',
                                     `color_name` varchar(200) DEFAULT NULL COMMENT '颜色名称',
                                     `batch_no` varchar(255) DEFAULT '' COMMENT '批次号',
                                     `top_feed` decimal(5,2) DEFAULT NULL COMMENT '上超喂',
                                     `lower_feed` decimal(5,2) DEFAULT NULL COMMENT '下超喂',
                                     `brush_feed` decimal(5,2) DEFAULT NULL COMMENT '毛刷超喂',
                                     `speed` decimal(3,0) DEFAULT NULL COMMENT '车速',
                                     `falling_feed` decimal(3,1) DEFAULT NULL COMMENT '落布超喂',
                                     `out_cloth_feed` decimal(3,1) DEFAULT NULL COMMENT '冷水辊超喂',
                                     `speci_wind_speed` json DEFAULT NULL COMMENT '排风设定转速',
                                     `speci_cycle_wind_speed` json DEFAULT NULL COMMENT '循环风设定转速',
                                     `drying_room_preset_temp` json DEFAULT NULL COMMENT '烘箱设定温度',
                                     `org_code` varchar(32) NOT NULL COMMENT '工厂id',
                                     `gram_weight` decimal(18,0) DEFAULT NULL COMMENT '白坯克重',
                                     `weft_density` varchar(50) DEFAULT NULL COMMENT '纬密',
                                     `total_amplitude` varchar(32) DEFAULT NULL COMMENT '总门幅',
                                     `assistant` json DEFAULT NULL COMMENT '助剂(防水类、软剂类、其他)',
                                     `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                     `create_user` varchar(32) NOT NULL COMMENT '创建用户',
                                     `create_time` datetime NOT NULL COMMENT '创建时间',
                                     `create_system` varchar(32) NOT NULL COMMENT '创建系统',
                                     `update_user` varchar(32) NOT NULL DEFAULT '0' COMMENT '更新用户',
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                     `update_system` varchar(32) DEFAULT NULL COMMENT '修改系统',
                                     `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
                                     `pt_quantity` decimal(18,4) DEFAULT NULL COMMENT '配桶数量',
                                     `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建用户名称',
                                     `craft_type` bigint DEFAULT NULL COMMENT '工艺名称',
                                     `specification` varchar(200) NOT NULL DEFAULT '' COMMENT '规格',
                                     `style` varchar(200) NOT NULL DEFAULT '' COMMENT '风格',
                                     `fan_speed` decimal(4,0) DEFAULT NULL COMMENT '循环风转速(0-9999)',
                                     `drying_room_temp` decimal(4,0) DEFAULT NULL COMMENT '烘箱温度(0-9999)',
                                     `exhaust_speed` decimal(4,0) DEFAULT NULL COMMENT '排风转速(0-9999)',
                                     `rolling_pressure` decimal(2,0) DEFAULT NULL COMMENT '轧车压力(0-50)',
                                     `torsion` decimal(5,2) DEFAULT NULL COMMENT '扭度',
                                     `schedule_gram_heft` varchar(128) DEFAULT NULL COMMENT '克重',
                                     `schedule_weft_density` varchar(128) DEFAULT NULL COMMENT '纬密',
                                     `schedule_elasticity` varchar(128) DEFAULT NULL COMMENT '弹力',
                                     `dynamic_field` json DEFAULT NULL COMMENT '动态字段',
                                     `device_ids` json DEFAULT NULL COMMENT '设备id,支持多条',
                                     `craft_mold` bigint DEFAULT '1478970733311668666' COMMENT '工艺类型',
                                     `device_name` varchar(32) DEFAULT '' COMMENT '设备名称',
                                     `stereotype_requirement` varchar(255) DEFAULT '' COMMENT '定型要求',
                                     `craft_source` tinyint DEFAULT '0' COMMENT '工艺来源(0-标准工艺,1-算法工艺)',
                                     `product_record_id` bigint DEFAULT NULL COMMENT '生产记录表id(base_product_record)',
                                     `door_width_actual` json DEFAULT NULL COMMENT '门幅 JSON',
                                     `product_weight` varchar(20) DEFAULT '' COMMENT '成品布克重(成品克重)',
                                     `shrinkage` decimal(10,2) DEFAULT NULL COMMENT '缩率',
                                     `thickness` varchar(100) DEFAULT '' COMMENT '厚度',
                                     `pre_operator` varchar(30) DEFAULT NULL COMMENT '中车操作员',
                                     `brand` varchar(20) DEFAULT NULL COMMENT '设备品牌型号',
                                     PRIMARY KEY (`id`),
                                     KEY `idx_no` (`org_code`,`customer_no`,`fabric_no`,`craft_type`,`specification`,`style`) USING BTREE
) ENGINE=InnoDB COMMENT='定型工艺';

CREATE TABLE `base_product_record` (
                                       `id` bigint NOT NULL COMMENT '主键',
                                       `product_info_id` bigint DEFAULT NULL COMMENT '历史生产信息表id',
                                       `org_code` varchar(32) NOT NULL COMMENT '机构编号',
                                       `device_id` bigint NOT NULL COMMENT '设备表id',
                                       `device_name` varchar(32) NOT NULL COMMENT '设备名称',
                                       `product_card_code` varchar(32) NOT NULL COMMENT '流转卡号',
                                       `order_id` varchar(20) DEFAULT '' COMMENT '订单号',
                                       `customer_no` varchar(20) NOT NULL COMMENT '客户id',
                                       `customer_name` varchar(20) NOT NULL COMMENT '客户名称',
                                       `color_no` varchar(50) NOT NULL COMMENT '颜色色号',
                                       `color` varchar(200) NOT NULL COMMENT '颜色名称',
                                       `fabric_no` varchar(255) NOT NULL DEFAULT '' COMMENT '坯布编号',
                                       `fabric_name` varchar(255) NOT NULL DEFAULT '' COMMENT '坯布名称',
                                       `batch_no` varchar(255) DEFAULT NULL COMMENT '坯布批次',
                                       `fabric_width` varchar(50) DEFAULT NULL COMMENT '成品门幅',
                                       `gram_weight` decimal(18,0) DEFAULT NULL COMMENT '白坯克重',
                                       `gram_heft` int DEFAULT NULL COMMENT '成品克重',
                                       `weft_density` varchar(64) DEFAULT NULL COMMENT '纬密',
                                       `plan_meters` decimal(18,1) DEFAULT NULL COMMENT '计划米数',
                                       `defect` json DEFAULT NULL COMMENT '疵点',
                                       `defects_number` int DEFAULT NULL COMMENT '出疵匹数',
                                       `severity` varchar(20) DEFAULT NULL COMMENT '严重程度',
                                       `result` varchar(20) DEFAULT NULL COMMENT '处理结果：1-拉掉 2-放行 3-检验',
                                       `car_number` varchar(50) DEFAULT NULL COMMENT '车号',
                                       `upper_door_width` double(32,1) DEFAULT NULL COMMENT '上机门幅',
                                       `lower_door_width` double(32,1) DEFAULT NULL COMMENT '下机门幅',
                                       `scan_time` datetime(3) DEFAULT NULL COMMENT '扫描时间',
                                       `pre_status` varchar(10) NOT NULL COMMENT '前车状态(0.待办/待排产 1.已处理/生产中 2.已完成)',
                                       `after_status` varchar(10) NOT NULL COMMENT '后车状态(0.待办 1.已处理)',
                                       `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
                                       `save_time` datetime DEFAULT NULL COMMENT '保存时间',
                                       `operator` varchar(255) DEFAULT NULL COMMENT '操作员',
                                       `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
                                       `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建用户',
                                       `create_system` varchar(32) NOT NULL DEFAULT '' COMMENT '创建系统',
                                       `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '修改时间',
                                       `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '修改用户',
                                       `update_system` varchar(32) NOT NULL DEFAULT '' COMMENT '修改系统',
                                       `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
                                       `source` tinyint NOT NULL DEFAULT '0' COMMENT '数据来源(0.扫码 1.排产 2.扫描转排产)',
                                       `sort` int DEFAULT '0' COMMENT '排序值',
                                       `pt_quantity` decimal(18,4) DEFAULT NULL COMMENT '配桶数量',
                                       `product_no` bigint DEFAULT NULL COMMENT '合缸生产批号',
                                       `product_sort` bigint DEFAULT '0' COMMENT '合缸生产序号',
                                       `employee_name` varchar(64) DEFAULT NULL COMMENT '业务员',
                                       `markup_craft` varchar(255) DEFAULT NULL COMMENT '加价要求',
                                       `team_group` varchar(255) DEFAULT NULL COMMENT '班组',
                                       `avg_car_speed` decimal(6,2) DEFAULT NULL COMMENT '平均车速',
                                       `avg_temperature` decimal(6,2) DEFAULT NULL COMMENT '平均温度',
                                       `avg_wind_speed` decimal(6,2) DEFAULT NULL COMMENT '平均风速',
                                       `avg_top_feed` decimal(5,2) DEFAULT NULL COMMENT '平均上超喂',
                                       `dept_name` varchar(255) DEFAULT NULL COMMENT '染色车间',
                                       `assistant` json DEFAULT NULL COMMENT '助剂(中控结束时助剂信息)',
                                       `schedule_date` date DEFAULT NULL COMMENT '排班日期',
                                       `team_group_id` bigint DEFAULT NULL COMMENT '班组id',
                                       `team_group_name` varchar(64) DEFAULT NULL COMMENT '班组名称',
                                       `team_shift_id` bigint DEFAULT NULL COMMENT '班次ID',
                                       `team_shift_name` varchar(64) DEFAULT NULL COMMENT '班次名称',
                                       `workshop_id` bigint DEFAULT NULL COMMENT '车间id',
                                       `workshop_name` varchar(64) DEFAULT NULL COMMENT '车间名称',
                                       `schedule_start_time` datetime DEFAULT NULL COMMENT '班次开始时间',
                                       `schedule_end_time` datetime DEFAULT NULL COMMENT '班次结束时间',
                                       `calc_day` date DEFAULT NULL COMMENT '统计日期，格式 yyyy-MM-dd',
                                       `pre_car_number` varchar(50) DEFAULT NULL COMMENT '定前布车号',
                                       `craft_type` bigint DEFAULT NULL COMMENT '工艺类型',
                                       `pre_sort` int DEFAULT '0' COMMENT '待生产列表排序值',
                                       `specification` varchar(200) DEFAULT NULL COMMENT '规格',
                                       `style` varchar(200) DEFAULT NULL COMMENT '风格',
                                       `operator_info` json DEFAULT NULL COMMENT '操作人信息',
                                       `pre_operator` varchar(30) DEFAULT NULL COMMENT '中车操作员',
                                       `start_time` datetime DEFAULT NULL COMMENT '中车开始时间',
                                       `end_time` datetime DEFAULT NULL COMMENT '中车结束时间',
                                       `front_operator` varchar(30) DEFAULT NULL COMMENT '前车操作员',
                                       `actual_meters` decimal(18,2) DEFAULT NULL COMMENT '实际长度',
                                       `matches` varchar(100) DEFAULT NULL COMMENT '实际匹数',
                                       `expected_time` varchar(32) DEFAULT NULL COMMENT '预计执行时长',
                                       `actual_time` varchar(32) DEFAULT NULL COMMENT '实际执行时长',
                                       `exception_type` int DEFAULT '0' COMMENT '异常类型：0-正常数据;1-质量异常;2-操作异常;3-质量异常和操作异常',
                                       `product_weight_min` varchar(20) DEFAULT '' COMMENT '成品克重最低',
                                       `product_weight_max` varchar(20) DEFAULT '' COMMENT '成品克重最高',
                                       `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                       `is_batch` tinyint NOT NULL DEFAULT '0' COMMENT '是否批量排产：0否，1是',
                                       `process_name` varchar(30) DEFAULT NULL COMMENT '工序名称',
                                       `schedule_import` tinyint NOT NULL DEFAULT '0' COMMENT '排产导入(0:否,1:是)',
                                       `quality_status` varchar(20) DEFAULT NULL COMMENT '质量状态：不合格，合格',
                                       `process_code` varchar(128) NOT NULL DEFAULT '' COMMENT '工序code',
                                       `product_code` varchar(128) NOT NULL DEFAULT '' COMMENT '客户品名',
                                       `platform` int NOT NULL DEFAULT '0' COMMENT '数据来源平台(0:定型排产,1:aps排产)',
                                       `task_no` varchar(50) DEFAULT NULL COMMENT '任务号',
                                       `assistant_exception_type` int DEFAULT '0' COMMENT '助剂异常类型，0-正常，1-异常',
                                       `customer_request` varchar(255) DEFAULT NULL COMMENT '客户要求',
                                       `stereotype_requirement` varchar(255) DEFAULT '' COMMENT '定型要求',
                                       `craft_suitability` decimal(6,2) DEFAULT NULL COMMENT '工艺匹配度',
                                       `shrinkage` decimal(10,2) DEFAULT NULL COMMENT '缩率',
                                       `craft_exception_type` int DEFAULT '0' COMMENT '工艺异常类型，0-正常，1-异常',
                                       `is_have_craft` tinyint DEFAULT NULL COMMENT '是否缺少工艺;1-是,0-否',
                                       `product_weight` varchar(20) DEFAULT '' COMMENT '成品布克重(成品克重)',
                                       `thickness` varchar(100) DEFAULT '' COMMENT '厚度',
                                       `process_report_type` tinyint NOT NULL DEFAULT '0' COMMENT '报工标识(0:初始状态 10: 不可报工,20:未报工,30:已报工)',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       KEY `product_card_idx` (`org_code`,`product_card_code`,`device_id`) USING BTREE
) ENGINE=InnoDB  COMMENT='生产记录表';
