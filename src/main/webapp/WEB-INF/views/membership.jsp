<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/TripPlanner/membershipCreate" method="Post">
		<p>아이디:<input type="text" name="id">
		<p>이름:<input type="text" name="name">
		<p>비밀번호:<input type="text" name="pw">
		<p>지역:<input type="text" name="region">
		<p>성별: 남성<input type="radio" name="sex" value="남자"> /여성<input type="radio" name="sex" value="여자">
		<p>전화번호 :<input type="text" name="phone1"> -<input type="text" name="phone2">-<input type="text" name="phone3">
		<p>생년월일<input type="date" name="birthday">
		<input type="submit" value="회원가입"> 
	</form>
</body>
</html>