package com.my.framework.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.framework.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService memberService;
	
	@GetMapping("/")
	public String index(Model model) {
		// 1. 현재 로그인한 사용자 정보 가져오기 (Spring Security 기능)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// 2. 로그인 여부 확인
		// 조건1. 널 체크,
		// 조건2. 인증절차 통과했는지, 
		// 조건3. 스프링시큐리티는 로그인을 안한 사람한테 anonymousUser라는 임시 이름표를 붙여줌. 그래서 조건1,2를 통과했어도 익명인지 아닌지 체크하는 조건
		if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
			String userId = auth.getName(); // 로그인한 ID
			
			// 3. MyBatis 서비스 호출해서 이름 가져오기
			String userName = memberService.getMemberName(userId);
			model.addAttribute("userName", userName);
			
			return "index";
		} else {
			return "redirect:/login"; // 로그인 안 했으면 로그인 페이지로 튕겨내기
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
