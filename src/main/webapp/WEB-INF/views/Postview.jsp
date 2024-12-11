<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 보기</title>
<style>
    body {
        font-family: Arial, sans-serif;
        line-height: 1.6;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        font-size: 2em;
        font-weight: bold;
        color: #333;
        text-align: center;
        margin-bottom: 20px;
    }
    .post-details {
        font-size: 0.9em;
        color: #555;
        margin-bottom: 20px;
    }
    .post-details p {
        margin: 5px 0;
    }
    .post-content {
        font-size: 1.2em;
        color: #333;
        line-height: 1.8;
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 5px;
    }
</style>
</head>
<body>
	<div>
		<a href="/TripPlanner/Allboard?page=1">목록</a>
		<a href="/TripPlanner/postview/delete?num=${onepost.p_unique}">삭제</a>
		<a href="/TripPlanner/postview/update?num=${onepost.p_unique}">수정</a>
	</div>
    <div class="container">
        <h1>${onepost.title}</h1>
 <div class="post-details">
    <p><span> ${onepost.id}</span>&nbsp;
    <span>좋아요: ${onepost.likes}</span>&nbsp;
    <span>조회: ${onepost.view}</span>&nbsp;
    <span>작성일 ${postdate}</span></p>
</div>
        <div class="post-content">
            <p>${onepost.contents}</p>
        </div>
    </div>
    
</body>
</html>
