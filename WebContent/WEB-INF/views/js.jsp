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
var config = {
		name : '홍길동',
		info : function(){
			alert('인포테이션');
		}
}
config.info();
config.test = function() {
	alert('나도 됨');
}
config.test();
config.toString = function(){
	alert('나 출력하면 이제 이게 나옴');
	alert(config.name);
}

var num1 = 1;
var num2 = '1';
alert('num1==num2 = '+num1==num2);
alert('num1===num2 = '+num1===num2);
alert('num1===1 = '+num1===1);
alert('num1!==num2 = '+num1!==num2);
</script>
</body>
</html>