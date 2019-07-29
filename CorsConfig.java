package com.ruiec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域请求配置
 * @author luo_wei<br>
 * @date 2019年5月29日 下午5:37:23
 */
@Configuration
public class CorsConfig {

	/**
	 * 为指定的路径模式注册自定义的CorsConfiguration。
	 * @author luo_wei<br>
	 * @date 2019年5月29日 下午5:42:18
	 */
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
		corsConfiguration.addAllowedHeader("*"); // 2允许任何头
		corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等） 
		return corsConfiguration;
	}

	/**
	 * 根据路径模式映射的CorsConfiguration集合
	 * 提供每个请求的CorsConfiguration实例。
	 * @author luo_wei<br>
	 * @date 2019年5月29日 下午5:42:13
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

}
