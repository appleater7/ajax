<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보편적인 영화정보 - 개봉 리스트</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>영화명</th>
			<th>연도</th>
			<th>국가</th>
			<th>제작사</th>
			<th>감독</th>
		</tr>
		<c:if test="${fn:length(list)==0}">
			<tr>
				<td colspan="6">등록된 영화 개봉 리스트가 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${list}" var="movie">
			<tr>
				<td>${movie.miNum}</td>
				<td>${movie.miName}</td>
				<td>${movie.miYear}</td>
				<td>${movie.miNational}</td>
				<td>${movie.miVendor}</td>
				<td>${movie.miDirector}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${sessionScope.user!=null}">
		<a href="/views/movie/insert">개봉 영화 등록</a>
	</c:if>
</body>
</html>