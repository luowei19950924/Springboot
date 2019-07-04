package com.example.springboot.controller;

import com.example.springboot.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author luowei
 * @date 2019/5/13 13:41
 */
@Controller
public class EmailController {

    @Resource
    private EmailService emailService;

    /**
     * 邮件发送
     * @author luowei
     * @date 2019/5/13 13:44
     */
    @RequestMapping("send")
    public void sendSimpleMessage(){
        emailService.sendSimpleMail("shenwang@ruiec.cn","我是你爸爸","儿砸，儿砸，我是你爸爸");
    }
}
