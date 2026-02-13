package com.my.framework.service;

import com.my.framework.domain.Post;
import com.my.framework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // (기존) 글 쓰기
    // public void writePost(...) { ... }

    // [추가] 전체 글 조회 (최신순 정렬)
    public List<Post> getAllPosts() {
        // ID 기준 내림차순(DESC) = 최신 글이 먼저 나옴
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}