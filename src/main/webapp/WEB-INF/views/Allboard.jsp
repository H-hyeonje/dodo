<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f7f6;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        margin-top: 20px;
        font-size: 36px;
        color: #333;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    }

    th, td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: center;
    }

    th {
        background-color: #4CAF50;
        color: white;
        font-weight: bold;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    .pagination {
        text-align: center;
        margin-top: 20px;
    }

    .pagination a {
        display: inline-block;
        padding: 8px 16px;
        margin: 0 5px;
        color: #4CAF50;
        text-decoration: none;
        border-radius: 5px;
        border: 1px solid #ddd;
        font-size: 16px;
    }

    .pagination a:hover {
        background-color: #4CAF50;
        color: white;
    }

    .pagination .active {
        background-color: #4CAF50;
        color: white;
    }

    .pagination .disabled {
        color: #ccc;
        pointer-events: none;
    }
</style>
</head>
<body>
    <h1>게시판</h1>
    <table>
        <thead>
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>글쓴이</th>
                <th>작성일</th>
                <th>추천</th>
                <th>조회</th>
            </tr>
        </thead>
        <tbody>
         <c:forEach items="${Allpost}" var="All" varStatus="loop">
            <tr>
                <td>${getpostnumber.get(loop.index)}</td>
                <td><a href="/TripPlanner/postview?num=${All.p_unique}">${All.title}</a></td>
                <td>${All.id}</td>
                <td>${date.get(loop.index)}</td>
                <td>${All.likes}</td>
                <td>${All.view}</td>
            </tr>
           </c:forEach> 
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach items="${getTotalPages}" var="Total">
            <a href="/TripPlanner/Allboard?page=${Total}">[${Total}]</a>
        </c:forEach>
    </div>
    
    <div>
    <form action="/board/search">
    <select>
    	<option value="id">글쓴이</option>
    	<option value="title">글제목</option>
    </select>
    <input type="text" neme="keyword">
    <input type="submit" value="검색">
    </form>
    </div>
</body>
</html>
