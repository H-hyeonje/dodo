package com.spring.controller;

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
	public String login(Member mamber) {
		
		return "redirect:/";
	}
	
	@GetMapping("/membership")
	public String membership() {
		return "membership";
	}
	
	@PostMapping("/membershipCreate")
	public String mambershipCreate(Member mamber) {
		memberService.memberCreate(mamber);
	
		return "loginPage";
	}
	
}
