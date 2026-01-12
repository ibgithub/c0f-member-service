package com.ib.member.controller;

import com.ib.member.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    public MemberController() {
    }

    @GetMapping
    public List<String> members() {
        return List.of("Budi", "Ani", "Siti");
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        return authentication.getName();
    }
}