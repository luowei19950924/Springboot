package com.example.integration.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 * @author luowei
 * @date 2019/5/14 16:30
 */
/*@Component*/
public class MyReceiver {

    @RabbitHandler
    @RabbitListener(queues = "hel")
    public void receive(String text){
        System.out.println("收到消息"+text);
    }

}
