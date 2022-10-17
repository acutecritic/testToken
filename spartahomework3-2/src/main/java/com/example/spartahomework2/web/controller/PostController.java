package com.example.spartahomework2.web.controller;

import com.example.spartahomework2.domain.service.PostService;
import com.example.spartahomework2.web.dto.PostDto;
import com.example.spartahomework2.web.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseDto<?> create(@RequestBody @Valid PostDto.Req reqDto) {

        postService.create(reqDto);

        return ResponseDto.ofSuccess(null);
    }

    @GetMapping
    public ResponseDto<?> readAll() {

        List<PostDto.Res> resList = postService.readAll();

        return ResponseDto.ofSuccess(resList);
    }

    @GetMapping("/{id}")
    public ResponseDto<?> readOneById(@PathVariable("id") final Long id) {

        PostDto.DetailRes res = postService.readOneById(id);

        return ResponseDto.ofSuccess(res);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseDto<?> update(@PathVariable("id") final Long id,
                                 @RequestBody @Valid PostDto.Req reqDto) {

        postService.update(id, reqDto);

        return ResponseDto.ofSuccess(null);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<?> delete(@PathVariable("id") final Long id) {

        postService.delete(id);

        return ResponseDto.ofSuccess(null);
    }

}
