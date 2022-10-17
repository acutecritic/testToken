package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.MemberService;
import com.example.spartahomework2.security.jwt.TokenProvider;
import com.example.spartahomework2.web.dto.ResponseDto;
import com.example.spartahomework2.web.dto.TokenDto;
import com.example.spartahomework2.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody @Valid MemberDto.SignupReq reqDto){

        memberService.signup(reqDto);

        return ResponseDto.ofSuccess(null);
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody @Valid MemberDto.LoginReq reqDto, HttpServletResponse res){

        TokenDto tokenDto = memberService.login(reqDto);

        res.addHeader(TokenProvider.AUTHORIZATION_HEADER, tokenDto.getAccessToken());

        return ResponseDto.ofSuccess(null);
    }

}
