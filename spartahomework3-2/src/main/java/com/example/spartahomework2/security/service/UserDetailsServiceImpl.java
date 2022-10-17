package com.example.spartahomework2.security.service;

import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return memberRepository.findByNickname(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("입력하신 회원 닉네임 정보가 없습니다."));

    }

    private UserDetails createUserDetails(Member member){

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().toString());

        return new User(String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority));
    }
}
