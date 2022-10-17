package com.example.spartahomework2.security.exceptionhandler;

import com.example.spartahomework2.exception.ErrorCode;
import com.example.spartahomework2.exception.response.ErrorRes;
import com.example.spartahomework2.web.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper om;
    private static final String CUSTOM_DEFAULT_ERROR_MSG = "권한이 없습니다.";
    private static final String DEFAULT_ERROR_MSG = "접근이 거부되었습니다.";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        List<String> errorMsgList = new ArrayList<>();
        String errorMsg = accessDeniedException.getMessage().equals(DEFAULT_ERROR_MSG)
                ? CUSTOM_DEFAULT_ERROR_MSG
                : accessDeniedException.getMessage();
        errorMsgList.add(errorMsg);

        ResponseDto<String> responseDto = ResponseDto.ofFail(ErrorRes.of(errorMsgList, ErrorCode.AUTHORIZATION_FAIL));

        String result = om.writeValueAsString(responseDto);

        response.getWriter().write(result);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
