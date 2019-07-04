package com.example.integration.controller;

import com.example.integration.mq.MySender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 * @author luowei
 * @date 2019/5/14 16:33
 */
/*@Controller*/
public class TestController {

    @Autowired
    private MySender mySender;

    @RequestMapping("/send")
    @ResponseBody
    public String test(){
        mySender.send();
        return "ok";
    }
}
