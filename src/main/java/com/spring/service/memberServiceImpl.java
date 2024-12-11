package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.memberRipository;
import com.spring.domain.Member;
@Service
public class memberServiceImpl implements memberService {
	@Autowired
	memberRipository memberRipository;
	
	@Override
	public void memberCreate(Member member) {
		memberRipository.memberCreate(member);
		
	}

}
