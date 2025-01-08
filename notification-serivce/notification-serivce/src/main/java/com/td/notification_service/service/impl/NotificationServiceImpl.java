package com.td.notification_service.service.impl;

import com.td.notification_service.dto.FinancialTransactionDTO;
import com.td.notification_service.dto.NotificationDTO;
import com.td.notification_service.entity.NotificationEntity;
import com.td.notification_service.mapper.FinancialTransactionMapper;
import com.td.notification_service.mapper.NotificationMapper;
import com.td.notification_service.mapper.NotificationTemplateMapper;
import com.td.notification_service.mapper.UserMapper;
import com.td.notification_service.repository.NotificationRepository;
import com.td.notification_service.service.FinancialTransactionService;
import com.td.notification_service.service.NotificationService;
import com.td.notification_service.service.NotificationTemplateService;
import com.td.notification_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
/*@NoArgsConstructor
@AllArgsConstructor*/
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${notification.email.recipient}")
    private String emailRecipient;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationTemplateService notificationTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private FinancialTransactionService financialTransactionService;

    /*public void sendNotification(NotificationDTO notificationDTO) {
        // Send notification using notification template and user contact information

    }*/

    public void createNotification(FinancialTransactionDTO financialTransactionDTO) {
        // Create notification based on financial transaction

    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        //NotificationEntity notificationEntity = NotificationMapper.toEntity(notificationDTO);
        //return NotificationMapper.toDTO(notificationRepository.save(notificationEntity));

        NotificationEntity notificationEntity = NotificationMapper.toEntity(notificationDTO);
        System.out.println(">"+userService.getUserById(notificationDTO.getUser().getId()));
        notificationEntity.setUser(UserMapper.toEntity(userService.getUserById(notificationDTO.getUser().getId())));
        notificationEntity.setFinancialTransaction(FinancialTransactionMapper.toEntity(financialTransactionService.getFinancialTransactionById(notificationDTO.getFinancialTransaction().getId())));
        notificationEntity.setNotificationTemplate(NotificationTemplateMapper.toEntity(notificationTemplateService.getNotificationTemplateById(notificationDTO.getNotificationTemplate().getId())));
        NotificationEntity savedNotification = notificationRepository.save(notificationEntity);
        return NotificationMapper.toDTO(savedNotification);


    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationDTO getNotificationById(Long id) {
        NotificationEntity notificationEntity = notificationRepository.findById(id).orElse(null);
        return NotificationMapper.toDTO(notificationEntity);

    }

    @Override
    public NotificationDTO updateNotification(NotificationDTO notificationDTO) {
        NotificationEntity existingNotificationEntity = notificationRepository.findById(notificationDTO.getId()).orElse(null);
        if (existingNotificationEntity != null) {
            existingNotificationEntity = NotificationMapper.toEntity(notificationDTO);

            existingNotificationEntity.setUser(UserMapper.toEntity(userService.getUserById(notificationDTO.getUser().getId())));
            existingNotificationEntity.setFinancialTransaction(FinancialTransactionMapper.toEntity(financialTransactionService.getFinancialTransactionById(notificationDTO.getFinancialTransaction().getId())));
            existingNotificationEntity.setNotificationTemplate(NotificationTemplateMapper.toEntity(notificationTemplateService.getNotificationTemplateById(notificationDTO.getNotificationTemplate().getId())));
            NotificationEntity savedNotification = notificationRepository.save(existingNotificationEntity);
            return NotificationMapper.toDTO(savedNotification);
        } else {
            return null;
        }
    }

    @Override
    public void sendNotification(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailRecipient);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);

    }

    public void sendNotificationMail(String recipientEmail, String transactionDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailRecipient);
        message.setTo(recipientEmail);
        message.setSubject("Fraudulent Transaction Alert");
        message.setText("Dear " + recipientEmail + ",\n\nWe have detected a potentially fraudulent transaction on your account. Please review the transaction details below:\n\n" + transactionDetails + "\n\nIf you have any questions or concerns, please do not hesitate to contact us.\n\nBest regards,\n[Your Name]");
        javaMailSender.send(message);
    }


}
