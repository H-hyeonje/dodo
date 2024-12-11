package com.spring.dao;

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
	public void memberCreate(Member member) {
		String memberCreateSQL="INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?)";
		template.update(memberCreateSQL,
				member.getName(),
				member.getId(),
				member.getPw(),
				member.getRegion(),
				member.getSex(),
				member.getPhone1(),
				member.getPhone2(),
				member.getPhone3(),
				member.getBirthday()

				);
		
	}

}
