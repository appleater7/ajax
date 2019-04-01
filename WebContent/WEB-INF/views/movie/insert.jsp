<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보편적인 영화정보</title>
</head>
<body>
<form method="post" action="/movie/insert">
<table border="1">
<tr>
	<th>영화명</th>
	<td><input type="text" name="mi_name" id="mi_name"></td>
</tr>
<tr>
	<th>연도</th>
	<td><input type="number" name="mi_year" id="mi_year"></td>
</tr>
<tr>
	<th>국가</th>
	<td><input type="text" name="mi_national" id="mi_national"></td>
</tr>
<tr>
	<th>제작사</th>
	<td><input type="text" name="mi_vendor" id="mi_vendor"></td>
</tr>
<tr>
	<th>감독</th>
	<td><input type="text" name="mi_director" id="mi_director"></td>
</tr>
<tr>
	<td colspan="2"><button>등록하기</button></td>
</tr>
</table>
</form>
</body>
</html>