package com.ruiec.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruiec.service.AdminRoleService;
import com.ruiec.service.NavigationService;
import com.ruiec.service.RoleService;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro基础配置
 * 
 * @author chenJingYuan<br>
 * @date 2018年9月6日 上午8:52:25
 */
@Configuration
public class ShiroConfiguration {

    private Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    /**
     * shiro过滤器
     * 
     * @author chenJingYuan<br>
     * @date 2018年9月6日 上午8:53:13
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager, NavigationService navigationService,
        AdminRoleService adminRoleService, RoleService roleService) {
        // 获取所有权限url
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 配置登录的url和登录成功的url
        bean.setLoginUrl("/login/view");
        /* bean.setSuccessUrl("/"); */
        bean.setUnauthorizedUrl("/login/error");
        // 配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 登录
        filterChainDefinitionMap.put("/login/**", "anon"); // 表示可以匿名访问
        // 注销
        filterChainDefinitionMap.put("/logout/**", "anon"); // 表示可以匿名访问
        filterChainDefinitionMap.put("/admin/common/center", "anon"); // 表示可以匿名访问
        // 静态资源
        filterChainDefinitionMap.put("/**/*.css", "anon");
        filterChainDefinitionMap.put("/**/*.js", "anon");
        filterChainDefinitionMap.put("/images/*.*", "anon");

        // 配置文件放行url
        // for (String requestUri : properties.getRequestUriList()) {
        // if (!StringUtil.isEmpty(requestUri)) {
        // filterChainDefinitionMap.put(requestUri, "anon");
        // }
        // }

        // 所有后台控制器访问权限

        /*if (urls != null && urls.size() > 0) {
            for (String url : urls) {
                filterChainDefinitionMap.put(url, "perms[" + url + "]");
            }
        }*/
        List<Map<String,Object>> urlList = navigationService.getUrls();
        for (Map<String, Object> map : urlList) {
			filterChainDefinitionMap.put(map.get("url").toString(), "perms[" + map.get("perm") + "]");
		}

        filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    /**
     * 配置核心安全事务管理器
     * 
     * @author chenJingYuan<br>
     * @date 2018年9月6日 上午8:53:00
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        logger.info("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     * 配置自定义的权限登录器
     * 
     * @author chenJingYuan<br>
     * @date 2018年9月6日 上午8:52:53
     */
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     * 
     * @author chenJingYuan<br>
     * @date 2018年9月6日 上午8:52:47
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
        @Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 配置方言(供thymeleaf页面标签使用)
     * 
     * @author chenJingYuan<br>
     * @date 2018年9月6日 下午1:58:27
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
