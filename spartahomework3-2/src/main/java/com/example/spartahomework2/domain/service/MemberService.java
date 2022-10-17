package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.web.dto.TokenDto;
import com.example.spartahomework2.web.dto.MemberDto;

public interface MemberService {

    void signup(MemberDto.SignupReq reqDto);

    TokenDto login(MemberDto.LoginReq reqDto);

}
