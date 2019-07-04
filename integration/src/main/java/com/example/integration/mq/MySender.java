package com.example.integration.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 * @author luowei
 * @date 2019/5/14 16:27
 */
/*@Component*/
public class MySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("hel","你好！！！");
    }

}
