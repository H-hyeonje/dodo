package com.spring.service;

import com.spring.domain.Post;

public interface postService {
	public Post getpost(int num);
	public void postdelet(int num);
	public void postupdate(Post post,int num);
	
}
