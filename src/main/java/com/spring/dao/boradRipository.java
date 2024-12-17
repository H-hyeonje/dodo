package com.spring.dao;

import java.util.List;
import java.util.Map;

import com.spring.domain.Post;

public interface boradRipository {
	 Map<String, Object> AllboardRead(int page);
	 Map<String, Object> searchPosts(String type, String keyword, int page); 
}
