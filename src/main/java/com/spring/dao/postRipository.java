package com.spring.dao;

import com.spring.domain.Post;

public interface postRipository  {
	public Post getpost(int num);
	public void postdelet(int num);
	public void postupdate(Post post,int num);
}
