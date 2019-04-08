<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	var xhr = XMLHttpRequest();
	xhr.open('GET', '/addr2/list');
	xhr.onreadystatechange = function(){
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				document.write(xhr.response);
			}
		}	
	}
	xhr.send();
</script>
</body>
</html>