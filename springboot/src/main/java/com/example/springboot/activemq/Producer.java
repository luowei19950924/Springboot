package com.example.springboot.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * 生产者制造消息
 * @author luowei
 * @date 2019/5/14 13:59
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * @author luowei
     * @date 2019/5/14 14:10
     */
    public void sendMessage(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
