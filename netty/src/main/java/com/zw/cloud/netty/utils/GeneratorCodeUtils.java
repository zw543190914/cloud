package com.zw.cloud.netty.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Lists;

import java.util.ArrayList;

public class GeneratorCodeUtils {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/netty/src/main/java");
        gc.setAuthor("zw");
        gc.setOpen(false);
        gc.setIdType(IdType.ASSIGN_ID);
        //gc.setDateType(DateType.ONLY_DATE);
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/chat?serverTimezone=GMT%2B8&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("password");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("模块名");
        pc.setParent("com.zw.cloud.netty.web");
        pc.setController("controller.chat");
        pc.setService("service.api.chat");
        pc.setServiceImpl("service.impl.chat");
        pc.setMapper("dao.chat");
        pc.setEntity("entity.chat");
        pc.setXml("dao.chat");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //表名，多个英文逗号分割
        strategy.setInclude("chat_msg","friends_request","my_friend","user_info");
        // 逻辑删除
        strategy.setLogicDeleteFieldName("deleted");
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);

        // 自动填充
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        TableFill createUser = new TableFill("create_user", FieldFill.INSERT);
        ArrayList<TableFill> fills = Lists.newArrayList(createTime,updateTime,createUser);
        strategy.setTableFillList(fills);
        // 乐观锁
        strategy.setVersionFieldName("version");

        //strategy.setTablePrefix(pc.getModuleName() + "_");

        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 执行
        mpg.execute();
    }
}
