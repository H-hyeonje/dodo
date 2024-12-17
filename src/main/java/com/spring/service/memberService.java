package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.domain.Member;

public interface memberService {
	
	public Map<String, Object> getmember(String id,String pw);
}
