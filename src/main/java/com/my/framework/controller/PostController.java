package com.my.framework.controller;

import com.my.framework.domain.Post;
import com.my.framework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        // 1. 서비스에게 "글 다 가져와!" 시키기
        List<Post> postList = postService.getAllPosts();

        // 2. 가져온 글 목록을 "posts"라는 이름으로 화면(HTML)에 전달
        model.addAttribute("posts", postList);

        // 3. index.html을 보여줘라
        return "index";
    }
}