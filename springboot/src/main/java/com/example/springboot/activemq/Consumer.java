package com.example.springboot.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 消费者获取监听消息
 * @author luowei
 * @date 2019/5/14 14:17
 */
@Component
public class Consumer {

    @JmsListener(destination = "myqueues")
    public void receiveMsg(String text){
        System.out.println(text+".....");
    }
}
