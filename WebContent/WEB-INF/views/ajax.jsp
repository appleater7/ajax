<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX Test</title>
<script src="/js/common.js"></script>
</head>
<body>
	<div id="rDiv"></div>
	<script>
		/* 	function callFunc(num1, num2) {
		 alert('나는 첫번째 callFunc()');
		 }
		 var cf = new CommonFunc();
		 cf.callFunc();
		 cf.callFunc2();
		 callFunc(); */
		/* 	var abc = '123';
		 abc = function() {
		 alert(1);
		 }
		 abc(); */

		/* 		var callback = function(res, res2) {
		 var res2 = res2 ? res2 : 3;
		 alert(res2);
		 }
		 callback(2, 0);
		 callback(0, 2); */

		/* 		var callback = function(res) {
		 console.log(res);
		 } */

		/* 		var xhr = new XMLHttpRequest();
		 xhr.open('GET', '/addr2/list');
		 xhr.onreadystatechange = function() {
		 if (xhr.readyState == 4 && xhr.status == 200) {
		 callback(xhr.response);
		 }
		 }
		 xhr.send(); */
		/* 		function _ge(id) {
		 return document.querySelector('#' + id);
		 }
		 _ge('rDiv').innerHTML = 'abc'; */
		function callback(res) {
			 var res = JSON.parse(res);
			 for(var addr of res.list) {
				 document.write(addr.ad_num + addr.ad_sido);
			 }
		}
		var config = {
				url : '/addr2/list',
				method : 'GET',
				async : true
		}
		var au = new AjaxUtil();
		au.open(config);
		au.setCallback(callback);
		au.send();
		
	</script>
</body>
</html>