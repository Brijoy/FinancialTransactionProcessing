package com.td.notification_service.dto;

import com.td.notification_service.util.NotificationStatus;
import com.td.notification_service.util.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {
    private Long id;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private UserDTO user;
    private FinancialTransactionDTO financialTransaction;
    private NotificationTemplateDTO notificationTemplate;

}
