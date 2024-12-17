package com.spring.service;


import com.spring.domain.Post;

public interface postService {
	 	Post getPostById(int num);
	    void createPost(Post post);
	    void deletePost(int num);
	    void updatePost(Post post, int num);
	    void incrementViewCount(int postId);
	    void incrementPostLike(int postId);

}
