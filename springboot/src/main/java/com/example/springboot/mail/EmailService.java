package com.example.springboot.mail;

/**
 * @author luowei
 * @date 2019/5/13 11:17
 */
public interface EmailService {

    //发送简单的邮件
    void sendSimpleMail(String sendTo,String title,String content);
}
