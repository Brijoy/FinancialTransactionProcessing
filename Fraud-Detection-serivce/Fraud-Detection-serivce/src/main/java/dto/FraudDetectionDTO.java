package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class FraudDetectionDTO {

    private Long fraudAttemptId;
    private String status;
    private int attempt;
    private LocalDateTime timestamp;
}
