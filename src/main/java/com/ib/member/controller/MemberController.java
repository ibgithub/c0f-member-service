package com.ib.member.controller;

import com.ib.member.dto.MemberDto;
import com.ib.member.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDto> members(Authentication authentication) {
        // username dari JWT
        String username = authentication.getName();
        System.out.println("Request by: " + username);

        return memberService.getAllMembers();
    }
}