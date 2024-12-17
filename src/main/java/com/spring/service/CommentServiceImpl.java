package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.dao.CommentRepository;
import com.spring.dao.CommentRowMapper;
import com.spring.domain.Comment;

import java.util.List;

@Repository
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByPostId(int postId, int page, int size) {
        int offset = (page - 1) * size;
        return commentRepository.getCommentsByPostId(postId, offset, size);
    }

    @Override
    public int countCommentsByPostId(int postId) {
        return commentRepository.countCommentsByPostId(postId);
    }

    @Override
    public Comment getCommentById(int c_unique) {
        return commentRepository.getCommentById(c_unique);
    }

    @Override
    public void insertComment(Comment comment) {
        commentRepository.insertComment(comment);
    }

    @Override
    public void updateComment(int c_unique, String comments) {
        commentRepository.updateComment(c_unique, comments);
    }

    @Override
    public void deleteComment(int c_unique) {
        commentRepository.deleteComment(c_unique);
    }

    @Override
    public void incrementCommentLikes(int c_unique) {
        commentRepository.incrementCommentLikes(c_unique);
    }
	
}
