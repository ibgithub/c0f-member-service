package com.ib.member.service;

import com.ib.member.dto.JwtResponse;
import com.ib.member.dto.LoginRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthClientService {

    private final RestTemplate restTemplate;

    public AuthClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login(String username, String password) {

        LoginRequest req = new LoginRequest(username, password);

        JwtResponse res = restTemplate.postForObject(
                "http://localhost:8081/api/auth/login",
                req,
                JwtResponse.class
        );

        return res.getToken();
    }
}
