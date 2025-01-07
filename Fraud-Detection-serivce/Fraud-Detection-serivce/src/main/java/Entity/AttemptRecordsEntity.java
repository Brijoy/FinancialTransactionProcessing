package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "attempt_records")
public class AttemptRecordsEntity{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private Long transactionId;
 private Long userId;
 private String status;
 private String otp;
 private String cvv;
 private int otpAttempts;
 private int cvvAttempts;
 private int attemptsCount;
 private LocalDateTime timestamp;
}