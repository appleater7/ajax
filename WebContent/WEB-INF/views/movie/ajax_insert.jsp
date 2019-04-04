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
<table border="1">
<tr>
	<th>영화명</th>
	<td><input type="text" name="mi_name"></td>
</tr>
<tr>
	<th>연도</th>
	<td><input type="text" name="mi_year"></td>
</tr>
<tr>
	<th>국가</th>
	<td><input type="text" name="mi_national"></td>
</tr>
<tr>
	<th>제작사</th>
	<td><input type="text" name="mi_vendor"></td>
</tr>
<tr>
	<th>감독</th>
	<td><input type="text" name="mi_director"></td>
</tr>
<tr>
	<td colspan="2"><button onclick="insertMovie()">등록하기</button></td>
</tr>
</table>
<script>
function insertMovie() {
	var inputs = document.querySelectorAll('input[name]');
	var params = '';
	for (var i = 0; i < inputs.length; i++) {
		var input = inputs[i]
		params += input.name + '=' + input.value + '&';
	}
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '/am/insert');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.response);
			alert(result.msg);
			if (result.url) {
				location.href = result.url;
			}
		}
	}
	xhr.send(params);
}
</script>
</body>
</html>