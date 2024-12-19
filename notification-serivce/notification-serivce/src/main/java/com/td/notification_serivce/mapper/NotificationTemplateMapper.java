package com.td.notification_serivce.mapper;

import com.td.notification_serivce.dto.NotificationTemplateDTO;
import com.td.notification_serivce.entity.NotificationTemplateEntity;

public class NotificationTemplateMapper {
    public static NotificationTemplateDTO toDTO(NotificationTemplateEntity notificationTemplate) {
        NotificationTemplateDTO dto = new NotificationTemplateDTO();
        dto.setId(notificationTemplate.getId());
        dto.setTemplateName(notificationTemplate.getTemplateName());
        dto.setTemplateContent(notificationTemplate.getTemplateContent());
        return dto;
    }

    public static NotificationTemplateEntity toEntity(NotificationTemplateDTO dto) {
        NotificationTemplateEntity entity = new NotificationTemplateEntity();
        entity.setId(dto.getId());
        entity.setTemplateName(dto.getTemplateName());
        entity.setTemplateContent(dto.getTemplateContent());
        return entity;
    }

}
