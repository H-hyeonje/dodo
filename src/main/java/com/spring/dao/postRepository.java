package com.spring.dao;



import com.spring.domain.Post;

public interface postRepository  {
	 	Post getPostById(int postId);
	    void createPost(Post post);
	    void updatePost(Post post, int postId);
	    void deletePost(int postId);
	    void incrementViewCount(int postId);
	    void incrementPostLike(int postId);
}
