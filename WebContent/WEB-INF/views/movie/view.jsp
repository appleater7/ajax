<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보편적인 영화정보 - 개봉 영화</title>
</head>
<body>
<c:if test="${movie==null}">
	조회하신 번호는 없는 번호입니다.
</c:if>
<c:if test="${movie!=null}">
	<table border="1">
		<tr>
			<th>번호</th>
			<td>${movie.miNum}</td>
		</tr>
		<tr>
			<th>영화제목</th>
			<td>${movie.miName}</td>
		</tr>
		<tr>
			<th>연도</th>
			<td>${movie.miYear}</td>
		</tr>
		<tr>
			<th>국가</th>
			<td>${movie.miNational}</td>
		</tr>
		<tr>
			<th>제작사</th>
			<td>${movie.miVendor}</td>
		</tr>
		<tr>
			<th>감독</th>
			<td>${movie.miDirector}</td>
		</tr>
	</table>
	<c:if test="${sessionScope.user!=null}">
	<form method="post" action="/movie/delete">
		<input type="hidden" name="mi_num" value="${movie.miNum}">
		<button>삭제</button>	
	</form>
	</c:if>
</c:if>
<a href="/movie/list">리스트로 돌아가기</a>
</body>
</html>