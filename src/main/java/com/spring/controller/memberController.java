package com.spring.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.Member;
import com.spring.service.memberService;

@Controller
public class memberController {
	
	@Autowired
	memberService memberService;
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "loginPage";
	}
	
	@PostMapping("/login")
	public String login(Member member,HttpSession session) {
		String id=member.getId();
		String pw=member.getPw();
		
		Map<String, Object> results= memberService.getmember(id,pw);
		if(!results.isEmpty()) {
			session.setAttribute("userId", results.get("id"));
			session.setAttribute("userName", results.get("name"));
			return "redirect:/";
		}else {
			
			return "redirect:/loginPage";
		}
		}
}

