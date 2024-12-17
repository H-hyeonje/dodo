package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.domain.Post;

public interface boradService {
    Map<String, Object> AllboardRead(int page); // 전체 게시판 조회
    Map<String, Object> searchPosts(String type, String keyword, int page); // 검색 게시판 조회
}