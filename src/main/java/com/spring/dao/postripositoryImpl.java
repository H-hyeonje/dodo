package com.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.domain.Post;

@Repository
public class postripositoryImpl implements postRipository{
	@Autowired
    private JdbcTemplate template;
		
	
	@Override
	public Post getpost(int num) {
		
		PostRowMapper postRowMapper=new PostRowMapper();
		String getpostSQL="select * from Post where p_unique=?";
		String viewupdateSQL="UPDATE Post SET view = view + 1 WHERE p_unique = ?";
		template.update(viewupdateSQL,num);
		Post result =  template.queryForObject(getpostSQL, postRowMapper, num);
		
		return result;
	}


	@Override
	public void postdelet(int num) {
		String deletSQL="DELETE FROM Post WHERE p_unique = ? ";
		template.update(deletSQL,num);
		
	}


	@Override
	public void postupdate(Post post,int num) {
		String updateSQL="UPDATE Post SET title =?, contents =?, region=?, isprivate=?, Satisfaction=? WHERE p_unique=?";
		template.update(updateSQL,
							post.getTitle(),
							post.getContents(),
							post.getRegion(),
							post.getIsPrivate(),
							post.getSatisfaction(),
							num );
	}

}
