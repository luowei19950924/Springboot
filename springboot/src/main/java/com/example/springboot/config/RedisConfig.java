package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * redis配置
 * @author luowei
 * @date 2019/5/14 11:20
 */
/*@Configuration*/
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String redisNodes;

    @Bean
    public JedisCluster getJedisCluster(){
        String [] redisnodes=redisNodes.split(",");
        Set<HostAndPort> nodes=new HashSet<>();
        for (String node: redisnodes) {
            String[] arr=node.split(":");
            HostAndPort hostAndPort=new HostAndPort(arr[0],Integer.valueOf(arr[1]));
            nodes.add(hostAndPort);
        }
        JedisCluster cluster=new JedisCluster(nodes);
        return cluster;
    }
}
