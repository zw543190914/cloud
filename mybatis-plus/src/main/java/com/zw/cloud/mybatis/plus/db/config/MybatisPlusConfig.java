package com.zw.cloud.mybatis.plus.db.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zw.cloud.mybatis.plus.db.AutoFillHandler;
import com.zw.cloud.mybatis.plus.db.MybatisPlusUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setOptimizeJoin(false);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
     */
   /* @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }*/


    /**
     * ID生成器
     *
     * @return id数值
     */
    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        IdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
        MybatisPlusUtil.init(identifierGenerator);
        return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator(identifierGenerator);
    }

    /**
     * 字段注入系统字段
     *
     * @return 处理器
     */
    @Bean
    @Primary
    public MetaObjectHandler autoFillMetaObjectHandler() {
        return new AutoFillHandler() {
            @Override
            public String getUserId() {
                //return WebAuthContext.getUserID();
                return "system";
            }

            @Override
            public String getOrgCode() {
                //return WebAuthContext.getOrgCode();
                return "orgCode-test";
            }

            @Override
            public String getClientId() {
                //return WebAuthContext.getClientID();
                return "clientId-test";
            }

        };
    }

}
