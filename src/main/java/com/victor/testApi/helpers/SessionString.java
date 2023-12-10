package com.victor.testApi.helpers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SessionString {
    public String generateSessionString(){
        int length = 128;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
