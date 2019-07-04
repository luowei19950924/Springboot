package com.example.integration.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * 消息发送者
 * @author luowei
 * @date 2019/5/14 18:47
 */
@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    //@Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        String msg = UUID.randomUUID().toString();
        //发送消息
        ListenableFuture future = kafkaTemplate.send("test", msg);
        future.addCallback(o -> System.out.println("send-消息发送成功："+msg),
                throwable->System.out.println("send-消息发送失败："+msg));
    }
}
