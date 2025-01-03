package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttemptRecordsDTO {

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

    public int getAttemptsCount() {
        return attemptsCount;
    }
}
