<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 썸머노트 API css -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="../header.jsp" %>
	<!-- 회원일 경우에만 페이지로드 -->
	<% if(loginid == null) response.sendRedirect("/room/user/userlogin.jsp"); %>
	
	<div class="webbox">
	 
		<h3>글쓰기</h3> 
		<form>
			제목 : 	<input type="text" name="btitle" class="btitle"> <br>
			<!-- 섬머노트 -->
  			<textarea id="summernote" name="bcontent" class="bcontent"></textarea>
			첨부파일 :	<input type="file" name="bfile"> <br>
			<!-- form태그 안에서는 버튼 타입으로 수정하기 -->
			
			<div class="oldbfilebox" id="oldbfilebox"></div>
			
			<button type="button" onclick="qupdate()">수정</button> 
		</form>
		
	</div>
	
	<!-- 썸머노트 API js -->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

	<script type="text/javascript" src="/room/qboard/qjs/update.js"></script>
	
</body>
</html>