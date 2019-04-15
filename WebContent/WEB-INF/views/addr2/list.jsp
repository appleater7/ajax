<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<label for="ad_dong">읍면동 : </label><input type="text" name="ad_dong" id="ad_dong">
<button onclick="search()">검색</button>
<select id="pageCount" name="pageCount" onchange="changePageCount(this)">
	<option value="10">10</option>
	<option value="20">20</option>
	<option value="30">30</option>
	<option value="40">40</option>
	<option value="50">50</option>
</select>

<table border="1">
	<tr>
		<th>번호</th>
		<th>시도</th>
		<th>구군</th>
		<th>동</th>
		<th>리</th>
		<th>번지</th>
		<th>호</th>
		<th>삭제</th>
	</tr>
	<tbody id="tBody">
	</tbody>
</table>
<div id="dView"></div>
<script>
	function changePageCount(obj){
		location.href='/views/addr2/list?pageCount=' + obj.value;
	}
	function search() {
		var ad_dong = document.querySelector('#ad_dong').value;
		location.href = '/views/addr2/list?pageCount=${param.pageCount}&page=${param.page}&ad_dong=' + ad_dong;
	}
	function view(adNum) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', '/addr2/view?ad_num=' + adNum);
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4){
				if(xhr.status == 200) {
					document.querySelector('#dView').innerHTML = xhr.response;
				}
			}
		}
		xhr.send();
	}
	function closeTable() {
		document.querySelector('#addrTable').style.display='none';
	}
	function updateAddr(){
		var inputs = document.querySelectorAll('input[id]');
		var params = {};
		for(var i = 0 ; i <inputs.length;i++) {
			var input = inputs[i];
			params[input.id] = input.value;
		}
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/addr2/update');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200) {
				var res = JSON.parse(xhr.response);
				alert(res.msg);
				if(res.update === 'true') {
					getList();
					view(params.adNum);
				}else {
					
				}
			}
		}
		xhr.send(JSON.stringify(params));
	}
	function deleteAddr(adNum){
		var param = {};
		param['adNum'] = adNum;
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/addr2/delete');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200) {
				var res = JSON.parse(xhr.response);
				alert(res.msg);
				if(res.delete === 'true') {
					getList();
					closeTable();
				}else {
					
				}
			}
		}
		xhr.send(JSON.stringify(param));
	}	
	function getList() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/addr2/list?pageCount=${param.pageCount}&page=${param.page}&ad_dong=${param.ad_dong}');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				console.log(xhr.response);
				var res = JSON.parse(xhr.response);
				document.querySelector('#pageCount').value = res.pageCount;
				var html = '';
				for(var addr of res.list){
					html += '<tr>';
					html += '<td>' + addr.ad_num + '</td>';
					html += '<td>' + addr.ad_sido + '</td>';
					html += '<td>' + addr.ad_gugun + '</td>';
					html += '<td><a href="javascript:view(' + addr.ad_num +')">' + addr.ad_dong + '</a></td>';
					html += '<td>' + (addr.ad_lee?addr.ad_lee:'') + '</td>';
					html += '<td>' + addr.ad_bunji + '</td>';
					html += '<td>' + addr.ad_ho + '</td>';
					html += '<td><button onclick="deleteAddr(\'' + addr.ad_num + '\')">삭제</button></td>';
					html += '</tr>';
				}
				html +='<tr>';
				html += '<td colspan="8">';
				
				if (res.page != 1) {
					html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=1&ad_dong=' + res.ad_dong + '">◁</a>';
					if (res.page < res.pageCount) {
						html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=1&ad_dong=' + res.ad_dong + '">◀</a>';
					} else {
						html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page='+ (res.page - res.pageCount) +'&ad_dong=' + res.ad_dong + '">◀</a>';
					}
				}
				
				for(var i=res.fBlock;i<=res.lBlock;i++){
					if(i==res.page){
						html += '<b>[' + i + ']</b>';
					}else{
						html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=' + i + '&ad_dong=' + res.ad_dong + '">[' + i + ']</a>';
					}
				}
				var lastCnt = Math.floor(res.totalCnt/res.pageCount) + 1;
				if (res.page != res.totalPageCnt) {
					html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page='+ (res.page + res.pageCount) +'&ad_dong=' + res.ad_dong + '">▶</a>';					
					if ((res.lastCnt - res.page) < res.pageCount) {
						html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=' + res.totalPageCnt + '&ad_dong=' + res.ad_dong + '">▷</a>';
					} else {
						html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=' + res.totalPageCnt + '&ad_dong=' + res.ad_dong + '">▷</a>';					
					}					
				}
				html += '</td>';
				html +='</tr>';				
				document.querySelector('#tBody').innerHTML = html;
			}
		}
	}
	xhr.send();
	}
	getList();
</script>
</body>
</html>