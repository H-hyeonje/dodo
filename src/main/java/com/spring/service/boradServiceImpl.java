package com.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.*;
import com.spring.domain.Post;
@Service
public class boradServiceImpl implements boradService{
	@Autowired
	boradRipository boradRipository;

	@Override
	public Map<String, Object> AllboardRead(int page) {
		Map<String, Object> result=boradRipository.AllboardRead(page);
		return result;
	}

	@Override
    public Map<String, Object> searchPosts(String type, String keyword, int page) {
        return boradRipository.searchPosts(type, keyword, page);
    }
	

}
