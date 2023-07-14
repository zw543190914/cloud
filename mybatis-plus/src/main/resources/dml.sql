-- json对象修改 将 wind_speed字段中windSpeed2值都乘以30
-- {"windSpeed1": 35.0, "windSpeed2": 37.0, "windSpeed3": 39.0, "windSpeed4": 41.0, "windSpeed5": 0.0, "windSpeed6": 0.0}
update `history_product_info_carft` SET wind_speed = JSON_SET(wind_speed, '$.windSpeed2', JSON_EXTRACT(wind_speed, '$.windSpeed2')*30)
where id in (1234321,1435781697952485378) and  JSON_EXTRACT(wind_speed, '$.windSpeed2') IS NOT NULL;

-- json 数组 JSON_CONTAINS
update  `base_tenter_craft` SET speci_wind_speed = JSON_SET(speci_wind_speed, '$.speciWindSpeed2', JSON_EXTRACT(speci_wind_speed, '$.speciWindSpeed2')*30)
WHERE JSON_CONTAINS(device_ids,JSON_OBJECT('id','1625739547898167298'));

-- json数组包含
SELECT * FROM `user_info` where JSON_CONTAINS(description,JSON_OBJECT('preOperatorAssistantStatisticDTOList', JSON_OBJECT('statisticDTOList',JSON_OBJECT('actualAmount',3.50))));
/**
  [
    {
        "deviceId":"1470590869131509761",
        "deviceName":"test_定型机#cqk",
        "count":1,
        "preOperatorAssistantStatisticDTOList":[
            {
                "preOperator":"63A3AD5FF01F4021BC2A494074D9CE88",
                "statisticDTOList":[
                    {
                        "preOperator":"63A3AD5FF01F4021BC2A494074D9CE88",
                        "parentAuxiliaryName":"软剂类",
                        "parentAuxiliaryId":"75",
                        "auxiliary":"无胆防绒剂TR30",
                        "actualAmount":3.5,
                        "hasActualAmount":true
                    },
                    {
                        "preOperator":"63A3AD5FF01F4021BC2A494074D9CE88",
                        "parentAuxiliaryName":"软剂类",
                        "parentAuxiliaryId":"75",
                        "auxiliary":"柔软剂F300",
                        "actualAmount":11.55,
                        "hasActualAmount":true
                    }
                ]
            }
        ]
    }
]
 */
-- json 数组模糊搜索
select * from user_info_0 where  json_extract(other,"$[*].name") like CONCAT('%','test','%');
-- 过滤出 assistant不为[]的数据
SELECT id,assistant FROM `base_tenter_craft` WHERE JSON_LENGTH(assistant) > 0;

-- JSON 对象过滤
SELECT * FROM base_dictionary WHERE JSON_EXTRACT(external_filed, '$.multipleProduction') = false;
SELECT * FROM base_dictionary WHERE org_code = 'devController' and `group_code` = 'CRAFT_TYPE_CONFIG'
and JSON_CONTAINS(external_filed,JSON_OBJECT('multipleProduction', false));

-- 将数据插入其他表
insert into `product_report_search_condition` (name,org_code,type,name_id,create_time,update_time)
SELECT customer_name as name, org_code,'customerName' as type,customer_no as name_id,now(),now()
FROM  product_record where customer_name is not null
GROUP BY customer_name;

-- 将所有org_code数据中增加一个type=27的数据。 按照orgCode分组，向每一组中都新增一条数据
INSERT INTO `dyeing_stenter`.`base_shaping_param_config` (`name`, `product_card_field`, `status`, `sort`, `remark`, `module`, `type`, `update_user`, `update_system`, `update_time`, `org_code`, `config_json`)
SELECT 'canUpdateCraftType', 0, 0, 1, '生产中是否允许变更「工序类型」？', '', 27, 'system', 'system', NOW(), org_code , null
FROM  `dyeing_stenter`.`base_shaping_param_config` where type = 1 GROUP BY org_code;

-- 假如要将B表中的字段更新到A表中语句：
update 库名.表A
    inner join(select 列a,列b from 表B) c
    on A.列名 = c.列名
set A.列名 = c.列名;

update `dyeing_stenter`.`base_product_record` A
    inner join(select id,assistant from `dyeing_stenter`.history_product_info) c
    on A.product_info_id = c.id
set A.assistant = c.assistant;

-- json 平均数赋值给其他表
update `dyeing_stenter`.`base_product_record` as A
    inner join(

        select product_info_id, CAST((
                                             IF(json_extract(cycle_wind_speed,'$.cycleWindSpeed4') = 'null'
                                                 ,0,json_extract(cycle_wind_speed,'$.cycleWindSpeed4')) +

                                             IF(json_extract(cycle_wind_speed,'$.cycleWindSpeed5') ='null'
                                                 ,0,json_extract(cycle_wind_speed,'$.cycleWindSpeed5')) +
                                             IF(json_extract(cycle_wind_speed,'$.cycleWindSpeed6') ='null'
                                                 ,0,json_extract(cycle_wind_speed,'$.cycleWindSpeed6')))/3 AS DECIMAL) as result

        from `dyeing_stenter`.history_product_info_carft
        where cycle_wind_speed is not null

    ) as c
    on A.product_info_id = c.product_info_id
set A.avg_wind_speed = c.result;

-- 排序
SELECT device_name FROM `base_product_record` ORDER BY
    FIELD(device_name,'12','1','阿巴吧','测试-prod','test_定型机#3',
          'test设备008','嘎嘎嘎1234','test_定型机#1','hj','设备名称','test_定型机#2'),id;
