package com.ruiec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件配置类
 * @author luowei
 * @date 2019/5/13 11:11
 */
@Component
public class EmailConfig {

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    @Value("${spring.mail.username}")
    private String emailFrom;


}
