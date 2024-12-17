package com.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.memberRipository;
import com.spring.domain.Member;
@Service
public class memberServiceImpl implements memberService {
	@Autowired
	memberRipository memberRipository;

	@Override
	public Map<String, Object> getmember(String id, String pw) {
		Map<String, Object> results=memberRipository.getmember(id, pw);
		return results;
	}
	


}
