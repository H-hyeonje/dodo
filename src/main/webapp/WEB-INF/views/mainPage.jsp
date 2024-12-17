<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
  <nav>
 
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <span>안녕하세요, ${sessionScope.userName}님!</span>
                <a href="/TripPlanner/logout">로그아웃</a>
                <a href="/TripPlanner/Allboard">게시판</a>
                <a href="/TripPlanner/postform">작성</a>
            </c:when>
            <c:otherwise>
                <a href="/TripPlanner/loginPage">로그인</a>
                <a href="/TripPlanner/Allboard">게시판</a>
            </c:otherwise>
        </c:choose>
    </nav>
</body>
</html>