package com.springvue.springboot1.controller;

import com.springvue.springboot1.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RestController
@Component
public class EmailController {
    @Autowired
    JavaMailSender mailSender;//注入QQ发送邮件的bean

    @RequestMapping("/qqemail")
    public Object qqemail(@RequestBody Email email) {
        try {
            System.out.println("邮箱"+email.getEmail());
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("2924466431@qq.com");//设置发件qq邮箱
            message.setTo(email.getEmail());				//设置收件人
            message.setSubject(email.getTitle());	//设置标题
            message.setText(email.getText());  	//第二个参数true表示使用HTML语言来编写邮件
            this.mailSender.send(mimeMessage);
            return "发送成功";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "发送失败";
        }
    }
}
