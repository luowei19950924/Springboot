package com.example.springboot.mail;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luowei
 * @date 2019/5/13 11:19
 */
/*@Service*/
public class EmailServiceImpl implements EmailService{

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    /**
     * 简单邮件的发送
     * @author luowei
     * @date 2019/5/13 14:06
     */
    public void sendSimpleMail(String sendTo,String title,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);

        mailSender.send(message);
    }
    /**
     * 发送带附件的邮件
     * @author luowei
     * @date 2019/5/13 14:20
     */
    public void sendAttachmentMAil(String sendTo, String title, String content, File file){
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);
            FileSystemResource resource=new FileSystemResource(file);
            helper.addAttachment("附件",resource);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 发送模板邮件
     * @author luowei
     * @date 2019/5/13 14:26
     */
    public void sendTemplateMail(String sendTo, String title, String info){
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            //封装模板使用的数据
            Map<String,Object> model=new HashMap<>();
            model.put("username","小小白");
            //得到模板
            Template template=freeMarkerConfigurer.getConfiguration().getTemplate(info);
            String html=FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            helper.setText(html,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
