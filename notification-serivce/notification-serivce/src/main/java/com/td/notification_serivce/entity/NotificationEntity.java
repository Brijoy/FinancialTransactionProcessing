package com.td.notification_serivce.entity;

import com.td.notification_serivce.util.NotificationStatus;
import com.td.notification_serivce.util.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "financial_transaction_id")
    private FinancialTransactionEntity financialTransaction;
    @ManyToOne
    @JoinColumn(name = "notification_template_id")
    private NotificationTemplateEntity notificationTemplate;

}
