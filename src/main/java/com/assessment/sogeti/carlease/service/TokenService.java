package com.assessment.sogeti.carlease.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.assessment.sogeti.carlease.data.AccessToken;

@Service
public class TokenService {
    private final Map<String, AccessToken> tokenStore = new HashMap<>();


    public AccessToken createAccessToken(String token, LocalDateTime issuedAt) {
        AccessToken accessToken = new AccessToken(token, issuedAt);
        saveToken(accessToken);
        return accessToken;
    }

    public void saveToken(AccessToken accessToken) {
        tokenStore.put(accessToken.getToken(), accessToken);
    }
    
    public boolean isTokenValid(AccessToken accessToken) {
        // Check if the current time is before the expiredAt time
        return LocalDateTime.now().isBefore(accessToken.getExpiredAt());
    }

    public boolean validateToken(String token) {
        AccessToken accessToken = getTokenFromStore(token);
        if (accessToken != null) {
            return isTokenValid(accessToken);
        } else {
            return false;
        }
    }

    public AccessToken getTokenFromStore(String token) {
        return tokenStore.get(token);
    }

    public void grantAccessIfValid(String token) {
        if (validateToken(token)) {
            System.out.println("Token is valid. Granting access...");
            // Implement logic to grant access, e.g., allow API calls
        } else {
            System.out.println("Token is expired or invalid. Access denied.");
            // Implement logic for expired or invalid token, e.g., reject API calls
        }
    }

    
    
}
