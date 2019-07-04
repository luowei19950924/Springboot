package com.example.integration.controller;

import com.example.integration.pojo.SocketMessage;
import com.example.integration.pojo.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WebSocket控制器
 * @author luowei
 * @date 2019/5/14 19:40
 */
@Controller
public class WebSocketController {

    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //当浏览器向服务器发生STOMP请求时，通过@MessageMapping来映射/getServerTime
    @MessageMapping(value = "/getServerTime")
    //当服务器有消息时，会对订阅了@SendTo中的路径的客户端发送消息
    @SendTo(value = "/topic/getResponse")
    public SocketResponse serverTime(SocketMessage message) throws Exception{
        return new SocketResponse(message.getMessage()+simpleDateFormat.format(new Date()));
    }

    @RequestMapping("/index")
    public String toPage(){
        return "webSocket";
    }
}
