package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraud_detection")
public class FraudDetectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fraudAttemptId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private FinancialTransactionEntity financialTransaction;

    @ManyToOne
    @JoinColumn(name = "notification_template_id", nullable = false)
    private NotificationTemplateEntity notificationTemplate;

    private String status;
    private int attempt;
    private LocalDateTime timestamp;

    public int getAttempt() {
        return attempt;
    }
}

