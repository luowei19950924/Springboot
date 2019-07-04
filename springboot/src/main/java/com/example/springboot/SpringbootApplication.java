package com.example.springboot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.example.springboot.controller","com.example.springboot.interceptor","com.example.springboot.handler","com.example.springboot.service","com.example.springboot.mail"})
//@EnableAutoConfiguration  自动装载配置
//@EnableAsync//开启异步调用
//@EnableScheduling //开启定时任务QuartZ
//@MapperScan("com.example.springboot.dao")  //mapper
//@EntityScan("com.example.springboot.pojo")  //扫描jpa实体
//@EnableJpaRepositories("com.example.springboot.dao") //扫描jpa-dao
//@EnableNeo4jRepositories //开启neo4j  @EnableNeo4jRepositories(basePackages = "com.example.springboot.dao")
//@EnableCaching //开启缓存
public class SpringbootApplication{ //extends SpringBootServletInitializer {

    /**
     * 重写此方法
     * @author luowei
     * @date 2019/5/14 10:43
     */
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }*/

    /**
     * SpringbootApplication extends WebMvcConfigurerAdapter
     * 整合fastJson第一种
     * @author luowei
     * @date 2019/5/8 19:03
     */
   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
        //创建fastJson的配置对象
        FastJsonConfig config=new FastJsonConfig();
        //对json数据进行格式化
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(config);
        converters.add(converter);
    }*/
    /**
     * 整合fastJson第二种
     * @author luowei
     * @date 2019/5/8 19:09
     */
   @Bean
   public HttpMessageConverters fastJsonMessageConverter(){
       //创建fastJson消息转换器
       FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
       //创建fastJson的配置对象
       FastJsonConfig config=new FastJsonConfig();
       //对json数据进行格式化
       config.setSerializerFeatures(SerializerFeature.PrettyFormat);
       converter.setFastJsonConfig(config);
       HttpMessageConverter<?> con=converter;
       return new HttpMessageConverters(con);
   }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
