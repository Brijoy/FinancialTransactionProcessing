package com.td.notification_serivce.service.impl;

import com.td.notification_serivce.dto.FinancialTransactionDTO;
import com.td.notification_serivce.dto.NotificationDTO;
import com.td.notification_serivce.entity.NotificationEntity;
import com.td.notification_serivce.mapper.FinancialTransactionMapper;
import com.td.notification_serivce.mapper.NotificationMapper;
import com.td.notification_serivce.mapper.NotificationTemplateMapper;
import com.td.notification_serivce.mapper.UserMapper;
import com.td.notification_serivce.repository.NotificationRepository;
import com.td.notification_serivce.service.FinancialTransactionService;
import com.td.notification_serivce.service.NotificationService;
import com.td.notification_serivce.service.NotificationTemplateService;
import com.td.notification_serivce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/*@NoArgsConstructor
@AllArgsConstructor*/
public class NotificationServiceImpl implements NotificationService {

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

}
