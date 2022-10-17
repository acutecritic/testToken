package com.example.spartahomework2.web.dto;

import com.example.spartahomework2.domain.entity.Member;
import com.example.spartahomework2.domain.entity.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

public class PostDto {

    @Getter
    public static class Req {

        @NotBlank(message = "제목을 입력해 주세요.")
        private final String title;

        @NotBlank(message = "내용을 입력해 주세요.")
        private final String content;

        @ConstructorProperties({"title", "content"})
        public Req(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public Post toEntity(Member member){

            return new Post(member, this.title, this.content);
        }
    }

    @Getter
    public static class Res{

        private final Long id;
        private final String title;
        private final String author;
        private final LocalDateTime createdAt;

        public Res(Post post) {
            this.id = post.getId();;
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.createdAt = post.getCreatedAt().toLocalDateTime();
        }
    }

    @Getter
    public static class DetailRes{

        private final Long id;
        private final String title;
        private final String author;
        private final String content;
        private final LocalDateTime createdAt;

        public DetailRes(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.author = post.getAuthor();
            this.content = post.getContent();
            this.createdAt = post.getCreatedAt().toLocalDateTime();
        }
    }

}
