package com.td.notification_serivce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class NotificationConfig {

    @Value("${notification.email.sender}")
    private String emailSender;

    @Value("${notification.email.recipient}")
    private String emailRecipient;

    @Value("${notification.sms.sender}")
    private String smsSender;

    @Value("${notification.sms.recipient}")
    private String smsRecipient;

    @Value("${notification.push-notification.sender}")
    private String pushNotificationSender;

    @Value("${notification.push-notification.recipient}")
    private String pushNotificationRecipient;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(emailSender);
        mailSender.setPassword("Notification@123");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }

    /*@Bean
    public SmsSender smsSender() {
        TwilioRestClient twilioRestClient = new TwilioRestClient("your-twilio-account-sid", "your-twilio-auth-token");
        return new TwilioSmsSender(twilioRestClient);

    }*/

   /* @Bean
    public PushNotificationSender pushNotificationSender() {
        // Implement push notification sender logic
    }*/



}
