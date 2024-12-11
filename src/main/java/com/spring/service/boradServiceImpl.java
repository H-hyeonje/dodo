package com.spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.*;
@Service
public class boradServiceImpl implements boradService{
	@Autowired
	boradRipository boradRipository;

	@Override
	public Map<String, Object> AllboardRead(int page) {
		Map<String, Object> result=boradRipository.AllboardRead(page);
		return result;
	}
}
