package com.td.notification_service.service.impl;

import com.td.notification_service.dto.NotificationTemplateDTO;
import com.td.notification_service.entity.NotificationTemplateEntity;
import com.td.notification_service.mapper.NotificationTemplateMapper;
import com.td.notification_service.repository.NotificationTemplateRepository;
import com.td.notification_service.service.NotificationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService{

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public NotificationTemplateDTO getNotificationTemplateById(Long id) {
        NotificationTemplateEntity notificationTemplateEntity = notificationTemplateRepository.findById(id).orElse(null);
        return NotificationTemplateMapper.toDTO(notificationTemplateEntity);

    }

    @Override
    public NotificationTemplateDTO createNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO) {
        NotificationTemplateEntity notificationTemplateEntity = NotificationTemplateMapper.toEntity(notificationTemplateDTO);
        NotificationTemplateEntity savedNotificationTemplate = notificationTemplateRepository.save(notificationTemplateEntity);
        return NotificationTemplateMapper.toDTO(savedNotificationTemplate);
    }

    @Override
    public NotificationTemplateDTO updateNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO) {
        NotificationTemplateEntity existingNotificationTemplateEntity = notificationTemplateRepository.findById(notificationTemplateDTO.getId()).orElse(null);
        if (existingNotificationTemplateEntity != null) {
            existingNotificationTemplateEntity = NotificationTemplateMapper.toEntity(notificationTemplateDTO);
            NotificationTemplateEntity savedNotificationTemplate = notificationTemplateRepository.save(existingNotificationTemplateEntity);
            return NotificationTemplateMapper.toDTO(savedNotificationTemplate);
        } else {
            return null;
        }

    }

    @Override
    public void deleteNotificationTemplate(Long id) {
        notificationTemplateRepository.deleteById(id);


    }
}
