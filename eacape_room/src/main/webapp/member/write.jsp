<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<!-- 고은시[10/24] -->
	
	<div class="webbox">
		<h3>문의사항</h3>
			제목	<input type="text" class="btitle" id="btitle" name="btitle">	<br>
			내용	<input type="text" class="bcontent" id="bcontent" name="bcontent">
			<button type="button" onclick="bwrite()">등록하기</button>
	</div>
	
	<!-- jquery 라이브러리 (api)-->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="../js/board/misrwrite.js"></script>
</body>
</html>