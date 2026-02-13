package com.my.framework.service.impl;

import org.springframework.stereotype.Service;

import com.my.framework.dto.MemberDto;
import com.my.framework.mapper.MemberMapper;
import com.my.framework.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper memberMapper;
	
	@Override
	public String getMemberName(String memberId) {
		MemberDto member = memberMapper.selectMemberById(memberId);
		return (member != null) ? member.getMemberId() : "손님";
	}
}
