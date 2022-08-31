package com.offcn.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author wcz
 * @create 2022-08-23 11:04
 */
@Component
public class MailUtil {
    public void textSend(String text){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-mail.xml");
        JavaMailSenderImpl	mailsend=(JavaMailSenderImpl) context.getBean("mailSender");
        //创建简单的邮件
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("591100976@qq.com");
        msg.setTo("591100976@qq.com");
        msg.setSubject("JAVA0115测试邮件");
        msg.setText(text);

        //发送邮件

        mailsend.send(msg);
        System.out.println("send ok");

    }
}
