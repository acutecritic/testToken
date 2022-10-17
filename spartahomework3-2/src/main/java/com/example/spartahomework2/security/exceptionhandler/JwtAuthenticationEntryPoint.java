package com.example.spartahomework2.security.exceptionhandler;

import com.example.spartahomework2.exception.ErrorCode;
import com.example.spartahomework2.exception.response.ErrorRes;
import com.example.spartahomework2.web.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper om;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        List<String> errorMsg = new ArrayList<>();
        errorMsg.add("로그인이 필요합니다.");

        ResponseDto<String> responseDto = ResponseDto.ofFail(ErrorRes.of(errorMsg, ErrorCode.AUTHENTICATION_FAIL));

        String result = om.writeValueAsString(responseDto);

        response.getWriter().write(result);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
