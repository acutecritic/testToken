package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.CommentService;
import com.example.spartahomework2.web.dto.CommentDto;
import com.example.spartahomework2.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/posts/{post_id}")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/comments")
    public ResponseDto<?> create(@PathVariable("post_id") final Long postId,
                                 @RequestBody @Valid CommentDto.Req reqDto) {

        commentService.create(postId, reqDto);

        return ResponseDto.ofSuccess(null);
    }

    @GetMapping("/comments")
    public ResponseDto<?> getByPostId(@PathVariable("post_id") final Long postId) {

        List<CommentDto.Res> resList = commentService.findAllByPostId(postId);

        return ResponseDto.ofSuccess(resList);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/comments/{comment_id}")
    public ResponseDto<?> update(@PathVariable("post_id") final Long postId,
                                 @PathVariable("comment_id") final Long commentId,
                                 @RequestBody @Valid CommentDto.Req reqDto) {

        commentService.update(postId, commentId, reqDto);

        return ResponseDto.ofSuccess(null);
    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseDto<?> delete(@PathVariable("post_id") final Long postId,
                                 @PathVariable("comment_id") final Long commentId) {

        commentService.delete(postId, commentId);

        return ResponseDto.ofSuccess(null);
    }

}
