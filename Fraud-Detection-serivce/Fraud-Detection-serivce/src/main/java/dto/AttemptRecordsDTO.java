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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setAttemptsCount(int attemptsCount) {
        this.attemptsCount = attemptsCount;
    }

    public void setCvvAttempts(int cvvAttempts) {
        this.cvvAttempts = cvvAttempts;
    }

    public void setOtpAttempts(int otpAttempts) {
        this.otpAttempts = otpAttempts;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public int getCvvAttempts() {
        return cvvAttempts;
    }

    public String getCvv() {
        return cvv;
    }

    public int getOtpAttempts() {
        return otpAttempts;
    }

    public String getOtp() {
        return otp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getAttemptsCount() {
        return attemptsCount;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}