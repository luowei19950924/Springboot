package com.lw.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author luowei
 * @date 2019/5/22 15:52
 */
@Configuration
@MapperScan("com.lw.mybatisplus.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     * @author luowei
     * @date 2019/5/22 15:54
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
