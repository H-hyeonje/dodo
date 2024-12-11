package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.*;
import com.spring.domain.Post;

@Service
public class postServiceImlp implements postService {
	@Autowired
	postRipository postRipository;

	@Override
	public Post getpost(int num) {
		Post result=postRipository.getpost(num);
		return result;
	}

	@Override
	public void postdelet(int num) {
		postRipository.postdelet(num);
	}

	@Override
	public void postupdate(Post post,int num) {
		postRipository.postupdate(post,num);
	}
}
