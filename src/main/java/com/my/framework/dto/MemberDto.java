package com.my.framework.dto;

import lombok.Data;

@Data	// Getter, Setter, toString 자동 생성
public class MemberDto {
	private String memberId;
	private String memberName;
	private String password;
}
