-- 데이터베이스 생성
CREATE DATABASE jyfw_db;

-- 멤버 테이블 생성
CREATE TABLE members (
    member_id VARCHAR(50) PRIMARY KEY,
    member_name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- 테스트용 데이터 넣기 (비번은 암호화 안 된 거라 실제 로그인은 안 될 수도 있음, 테스트용)
INSERT INTO members VALUES ('admin', '관리자님', '{noop}1234');