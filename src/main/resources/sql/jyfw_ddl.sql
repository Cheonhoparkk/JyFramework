-- 데이터베이스 생성
CREATE DATABASE jyfw_db;

-- 멤버 테이블 생성
CREATE TABLE members (
    member_id VARCHAR(50) PRIMARY KEY,
    member_name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- members 테이블에 role 컬럼 추가 (기본값은 일반 유저인 ROLE_USER)
ALTER TABLE members ADD COLUMN role VARCHAR(20) DEFAULT 'ROLE_USER';

-- 테스트용 관리자 계정 추가 (비밀번호는 암호화된 '1234' 입니다)
-- BCrypt 로 '1234'를 암호화한 값: $2a$10$X8A.m2U/.p.6H5B6v8N3/eI0vG7.1M/9L0f5m.O9C1w9E2X5z.XmG
INSERT INTO members (member_id, member_name, password, role) 
VALUES ('admin', '관리자', '$2a$10$X8A.m2U/.p.6H5B6v8N3/eI0vG7.1M/9L0f5m.O9C1w9E2X5z.XmG', 'ROLE_ADMIN');

-- 테스트용 일반 유저 계정 추가 (비밀번호 '1234')
INSERT INTO members (member_id, member_name, password, role) 
VALUES ('user', '일반사용자', '$2a$10$X8A.m2U/.p.6H5B6v8N3/eI0vG7.1M/9L0f5m.O9C1w9E2X5z.XmG', 'ROLE_USER');

-- 관리자랑 일반 유저 비밀번호를 스프링이 읽을 수 있는 평문 '1234'로 변경
UPDATE members SET password = '{noop}1234' WHERE member_id = 'admin';
UPDATE members SET password = '{noop}1234' WHERE member_id = 'user';