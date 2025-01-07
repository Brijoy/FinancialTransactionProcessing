package com.td.notification_serivce.config;

import jakarta.mail.MessagingException;
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

    @Value("${notification.email.password}")
    private String emailPassword;

    /*@Value("${notification.sms.sender}")
    private String smsSender;

    @Value("${notification.sms.recipient}")
    private String smsRecipient;

    @Value("${notification.push-notification.sender}")
    private String pushNotificationSender;

    @Value("${notification.push-notification.recipient}")
    private String pushNotificationRecipient;*/

    @Bean
    public JavaMailSender javaMailSender() throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //mailSender.setHost("smtp.gmail.com");
        //mailSender.setPort(587);
        mailSender.setHost("sandbox.smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername(emailSender);
        //mailSender.setPassword("Notification@1234");
        //mailSender.setPassword("2456 9920");
        mailSender.setPassword(emailPassword);

        Properties props = new Properties();
                // mailSender.getJavaMailProperties();


        //props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(props);

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
