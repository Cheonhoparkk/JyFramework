package com.my.framework.repository;

import com.my.framework.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// <다룰 객체(Entity), 그 객체의 ID 타입(Long)>
public interface PostRepository extends JpaRepository<Post, Long> {
    // 아무것도 안 적어도 저장, 조회, 수정, 삭제 기이 자동으로 생깁니다!
}