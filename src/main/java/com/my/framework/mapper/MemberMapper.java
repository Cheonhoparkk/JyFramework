package com.my.framework.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.my.framework.dto.MemberDto;

@Mapper
public interface MemberMapper {
	// ID로 회원 정보 조회
	MemberDto selectMemberById(String memberId);

}
