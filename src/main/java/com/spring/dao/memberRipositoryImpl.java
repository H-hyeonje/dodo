package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.Jdbc41Bridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.domain.Member;

@Repository
public class memberRipositoryImpl implements memberRipository{
	
	@Autowired
    private JdbcTemplate template;

	@Override
    public Map<String, Object> getmember(String id, String pw) {
        String SQL = "SELECT id, name FROM memberss WHERE id = ? AND pw = ?";
        List<Map<String, Object>> results = template.queryForList(SQL, id, pw);
        
        if (!results.isEmpty()) {
            return results.get(0); // 첫 번째 결과 반환
        } else {
            return null; // 결과 없음
        }
    }
	
	
	

}
