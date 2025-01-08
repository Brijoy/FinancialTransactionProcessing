package com.td.notification_service.mapper;

import com.td.notification_service.dto.NotificationDTO;
import com.td.notification_service.entity.NotificationEntity;

public class NotificationMapper {

    public static NotificationDTO toDTO(NotificationEntity notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setNotificationType(notification.getNotificationType());
        dto.setNotificationStatus(notification.getNotificationStatus());
        dto.setUser(UserMapper.toDTO(notification.getUser()));
        dto.setFinancialTransaction(FinancialTransactionMapper.toDTO(notification.getFinancialTransaction()));
        dto.setNotificationTemplate(NotificationTemplateMapper.toDTO(notification.getNotificationTemplate()));
        return dto;
    }

    public static NotificationEntity toEntity(NotificationDTO dto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setId(dto.getId());
        entity.setNotificationType(dto.getNotificationType());
        entity.setNotificationStatus(dto.getNotificationStatus());
        entity.setUser(UserMapper.toEntity(dto.getUser()));
        entity.setFinancialTransaction(FinancialTransactionMapper.toEntity(dto.getFinancialTransaction()));
        entity.setNotificationTemplate(NotificationTemplateMapper.toEntity(dto.getNotificationTemplate()));
        return entity;
    }

}
