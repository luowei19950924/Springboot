package com.example.springboot.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 多数据源-2
 * @author luowei
 * @date 2019/5/10 19:43
 */
/*@Configuration
@MapperScan(basePackages = "com.example.springboot.mapper",sqlSessionFactoryRef = "db2SqlSessionFactory")*/
public class DataSource2 {

    /**
     * 创建db2数据库
     * @author luowei
     * @date 2019/5/10 19:44
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }
    /**
     * 创建SqlSessionFactory
     * @author luowei
     * @date 2019/5/10 19:57
     */
    @Bean(name = "db2SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactoryBean(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     * @author luowei
     * @date 2019/5/10 19:58
     */
    @Bean(name="db2TransactionManager")
    public DataSourceTransactionManager testDataSourceTransactionManager(@Qualifier("db2DataSource") DataSource dataSource){
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
