package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.domain.Post;
@Repository
public class boradRipositoryImlp implements boradRipository {
	
	@Autowired
    private JdbcTemplate template;
	@Override
	public Map<String, Object> AllboardRead(int page) {
		int startIndex=(page-1)*10;
		
		PostRowMapper postRowMapper=new PostRowMapper();
		List<Post> Allpost=new ArrayList<Post>();
		Map<String, Object> result=new HashMap<String, Object>();
		String AllbordReadSQL ="SELECT * FROM Post WHERE isprivate = 1 ORDER BY publishdate DESC limit ?,10";
		String AllbordgetnumSQL="SELECT count(*) FROM Post WHERE isprivate = 1";
		int Allpostgetnum=template.queryForObject(AllbordgetnumSQL, Integer.class);
		Allpost=template.query(AllbordReadSQL,postRowMapper,startIndex);
		result.put("Allpost", Allpost);
		result.put("Allpostgetnum", Allpostgetnum);
		
		return result;
	}

}
