package com.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.*;
import com.spring.domain.Post;

@Service
public class postServiceImlp implements postService {

    @Autowired
    private postRepository postRepository;

    @Override
    public Post getPostById(int num) {
        return postRepository.getPostById(num);
    }

    @Override
    public void createPost(Post post) {
        postRepository.createPost(post);
    }

    @Override
    public void deletePost(int num) {
        postRepository.deletePost(num);
    }

    @Override
    public void updatePost(Post post, int num) {
        postRepository.updatePost(post, num);
    }

    @Override
    public void incrementViewCount(int postId) {
        postRepository.incrementViewCount(postId);
    }

    @Override
    public void incrementPostLike(int postId) {
        postRepository.incrementPostLike(postId);
    }
}

