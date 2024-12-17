package com.spring.dao;

import java.util.List;
import java.util.Map;

import com.spring.domain.Member;

public interface memberRipository {
	public Map<String, Object> getmember(String id,String pw);
}
