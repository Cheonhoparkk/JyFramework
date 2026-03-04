package com.my.framework.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.framework.dto.MemberDto;
import com.my.framework.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("============== [로그인 시도] 아이디: " + username + " ==============");
		// 1. 화면에서 입력받은 아이디(username)로 DB 조회
        MemberDto member = memberMapper.selectMemberById(username);
        
        System.out.println("============== [DB 조회 결과]: " + member + " ==============");
        
        // 2. 일치하는 회원이 없으면 에러 던지기
        if (member == null) {
        	System.out.println("============== 실패: 회원이 DB에 없습니다! ==============");
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
        }
        
        // 3. 스프링 시큐리티가 이해할 수 있는 User 객체로 변환해서 리턴
        // 여기서 DB에 있는 암호화된 비밀번호와 권한(role)을 시큐리티에게 넘겨줌
        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .roles(member.getRole().replace("ROLE_", "")) // ROLE_ADMIN -> ADMIN 으로 변환해서 넣기
                .build();
	}
}
