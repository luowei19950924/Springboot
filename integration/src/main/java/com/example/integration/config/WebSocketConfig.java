package com.example.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket配置类
 * @author luowei
 * @date 2019/5/14 19:28
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注册一个stomp协议的节点，并且映射到指定的url
     * @author luowei
     * @date 2019/5/14 19:33
     */
   /* @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个stomp的endpoint,并指定使用SockJS协议
        registry.addEndpoint("/endpointSocket").withSockJS();
    }*/

    /**
     * 配置消息代理
     * @author luowei
     * @date 2019/5/14 19:34
     */
    /*@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置一个广播示的消息代理
        registry.enableSimpleBroker("/topic");
    }*/

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
