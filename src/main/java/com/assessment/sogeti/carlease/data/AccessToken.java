package com.assessment.sogeti.carlease.data;

import java.time.LocalDateTime;

public class AccessToken {
    private String token;
    private LocalDateTime issuedAt;
    private LocalDateTime expiredAt;

    public AccessToken(String token, LocalDateTime issuedAt) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiredAt = issuedAt.plusHours(1);  // Add one hour to issuedAt
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
        this.expiredAt = issuedAt.plusHours(1);  // Automatically update expiredAt
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "token='" + token + '\'' +
                ", issuedAt=" + issuedAt +
                ", expiredAt=" + expiredAt +
                '}';
    }
}
