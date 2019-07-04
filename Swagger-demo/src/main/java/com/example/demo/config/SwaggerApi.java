package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 * @author luo_wei<br>
 * @date 2019年5月8日 下午2:13:17
 */
@Configuration
@EnableSwagger2 //启用Swagger2
public class SwaggerApi {
	
	/**
	 * 创建Docket的Bean之后
	 * @author luo_wei<br>
	 * @date 2019年5月8日 下午2:10:26
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().
				apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).paths(PathSelectors.any()).build();
	}
	/**
	 * 创建该Api的基本信息（这些基本信息会展现在文档页面中）
	 * @ApiIgnore无视
	 * @author luo_wei<br>
	 * @date 2019年5月8日 下午2:10:32
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("大数据BI-JAVA部门对接项目").description("对接出现问题，请找覃志添或者江山大佬")
				.termsOfServiceUrl("http://www.baidu.com/").contact("罗巍").version("1.0.0").build();
	}

}
