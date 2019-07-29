package com.ruiec.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenwang
 * @classname SocketIOConfig
 * @date 2019-07-06 10:55
 */
@Configuration
public class NettySocketIOConfig {

    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private String port;

    @Value("${socketio.bossCount}")
    private String bossCount;

    @Value("${socketio.workCount}")
    private String workCount;

    @Value("${socketio.allowCustomRequests}")
    private String allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private String upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private String pingTimeout;

    @Value("${socketio.pingInterval}")
    private String pingInterval;

    /**以下配置在上面的application.properties中已经注明*/
    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(Integer.parseInt(port));
        config.setBossThreads(Integer.parseInt(bossCount));
        config.setWorkerThreads(Integer.parseInt(workCount));
        config.setAllowCustomRequests(Boolean.parseBoolean(allowCustomRequests));
        config.setUpgradeTimeout(Integer.parseInt(upgradeTimeout));
        config.setPingTimeout(Integer.parseInt(pingTimeout));
        config.setPingInterval(Integer.parseInt(pingInterval));
        return new SocketIOServer(config);
    }
}

