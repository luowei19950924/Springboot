package com.luowei.springcloudeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * eureka注册中心启动类
 * @author luowei
 * @date 2019/4/26 14:53
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringCloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaApplication.class, args);
        System.out.println("启动完成");
    }

}
