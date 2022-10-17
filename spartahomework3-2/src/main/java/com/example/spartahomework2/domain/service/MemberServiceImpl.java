package com.example.spartahomework2.domain.service;

import com.example.spartahomework2.domain.repository.MemberRepository;
import com.example.spartahomework2.exception.customException.AlreadyExistsException;
import com.example.spartahomework2.exception.customException.InvalidInputException;
import com.example.spartahomework2.security.jwt.TokenProvider;
import com.example.spartahomework2.web.dto.TokenDto;
import com.example.spartahomework2.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService{

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Override
    public void signup(MemberDto.SignupReq reqDto) {

        //닉네임 중복, 비밀번호 확인 일치 여부 검증
        validateSignup(reqDto);

        //비밀번호 암호화 후 저장
        String encodePw = passwordEncoder.encode(reqDto.getPassword());
        memberRepository.save(reqDto.toEntity(encodePw));

    }

    @Override
    public TokenDto login(MemberDto.LoginReq reqDto) {

        // nickname, password 기반으로 만들어진 authentication token 생성(아직 인증 X)
        // UsernamepasswordAuthenticationToken의 생성자 확인 해보길!
        Authentication beforeAuthentication = new UsernamePasswordAuthenticationToken(reqDto.getNickname(), reqDto.getPassword());

        // 실제 검증
        // authenticationManager에게 인증 확인 요청 후,
        // 인증이 성공적으로 처리된다면, 인증 완료된 authenticationToken 생성
        // 내부적으로, UserDetailsService, DaoAuthenticationProvider를 거친다.(확인해보세요)
        Authentication afterAuthentication = authenticationManagerBuilder.getObject().authenticate(beforeAuthentication);

        TokenDto tokenDto = tokenProvider.generateToken(afterAuthentication);

        return tokenDto;
    }

    private void validateSignup(MemberDto.SignupReq reqDto){
        checkEmail(reqDto.getNickname());
        checkPasswordEq(reqDto.getPassword(), reqDto.getPasswordCheck());
    }

    private void checkEmail(String nickname){
        if(memberRepository.existsByNickname(nickname)){
            throw new AlreadyExistsException("이미 등록된 닉네임 입니다.");
        }
    }

    private void checkPasswordEq(String password, String passwordCheck){

        if(!password.equals(passwordCheck)){
            throw new InvalidInputException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

    }
}
