package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Comment;
import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    public static class Req{

        @NotBlank(message = "내용을 입력해 주세요.")
        private final String content;

        @ConstructorProperties({"content"})
        public Req(String content) {
            this.content = content;
        }

        public Comment toEntity(Member member, Post post){

            return new Comment(member, post, content);

        }
    }

    @Getter
    public static class Res{

        private final Long id;
        private final String content;
        private final String author;
        private final LocalDateTime createdAt;

        public Res(Comment comment, String author) {
            this.id = comment.getId();
            this.content = comment.getContent();
            this.author = author;
            this.createdAt = comment.getCreatedAt().toLocalDateTime();
        }
    }
}
