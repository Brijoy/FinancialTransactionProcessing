package com.td.notification_serivce.dto;

import com.td.notification_serivce.util.NotificationStatus;
import com.td.notification_serivce.util.NotificationType;
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
