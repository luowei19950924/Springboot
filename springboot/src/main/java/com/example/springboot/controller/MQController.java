package com.example.springboot.controller;

import com.example.springboot.activemq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author luowei
 * @date 2019/5/14 14:20
 */
@Controller
public class MQController {

    @Resource
    private Producer producer;

    @RequestMapping("/activemq")
    @ResponseBody
    public String tests(){
        //点对点消息
        Destination destination=new ActiveMQQueue("myqueues");
        for (int i=1;i<=3;i++){
            producer.sendMessage(destination,"hello"+i);
        }
        return "ok";
    }

}
