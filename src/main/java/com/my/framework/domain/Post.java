package com.my.framework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity // 이 클래스는 DB의 테이블이 됩니다
@Table(name = "posts") // 테이블 이름은 'posts'로 할게요
@Getter @Setter // Lombok이 Getter, Setter 자동 생성
public class Post {

    @Id // 기본키 (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1, 2, 3... 자동 증가
    private Long id;

    @Column(nullable = false) // 제목은 필수!
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 내용은 길게 저장 가능
    private String content;

    private String author; // 작성자

    private LocalDateTime createdDate; // 작성일

    @PrePersist // DB에 저장되기 직전에 실행
    public void prePersist() {
        this.createdDate = LocalDateTime.now(); // 현재 시간 자동 입력
    }
}