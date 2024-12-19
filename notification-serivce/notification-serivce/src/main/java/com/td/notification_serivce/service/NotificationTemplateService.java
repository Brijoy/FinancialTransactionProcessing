package com.td.notification_serivce.service;

import com.td.notification_serivce.dto.NotificationTemplateDTO;

public interface NotificationTemplateService {
    public NotificationTemplateDTO getNotificationTemplateById(Long id);
    public NotificationTemplateDTO createNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO);
    public NotificationTemplateDTO updateNotificationTemplate(NotificationTemplateDTO notificationTemplateDTO);
    public void deleteNotificationTemplate(Long id);

}
