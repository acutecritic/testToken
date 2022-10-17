package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Member;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.beans.ConstructorProperties;

public class MemberDto {

    @Getter
    public static class LoginReq{

        @NotBlank(message = "닉네임을 입력해 주세요.")
        private final String nickname;

        @NotBlank(message = "비밀번호를 입력해 주세요.")
        private final String password;

        @ConstructorProperties({"nickname, password"})
        public LoginReq(String nickname, String password) {
            this.nickname = nickname;
            this.password = password;
        }



    }

    @Getter
    public static class SignupReq{

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,12}$",
        message = "닉네임은 4-12글자이며, 1자 이상의 알파벳과 숫자만 가능합니다")
        private final String nickname;

        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{4,32}$",
        message = "비밀번호는 4-32글자이며, 1자 이상의 알파벳 소문자와 숫자만 가능합니다.")
        private final String password;

        @NotBlank(message = "비밀번호 확인을 입력해 주세요.")
        private final String passwordCheck;

        @ConstructorProperties({"nickname","password","passwordCheck"})
        public SignupReq(String nickname, String password, String passwordCheck){
            this.nickname = nickname;
            this.password = password;
            this.passwordCheck = passwordCheck;
        }

        public Member toEntity(String encodedPassword){
            return new Member(this.nickname, encodedPassword);
        }
    }
}
