<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 고은시[10/25] -->
	<%@include file="../header.jsp" %>
	
	<%
		//JSP스크립트 태그 [JAVA 코드 작성 공간]
		 session.setAttribute("mid", null);
      //페이지전환 main으로 이동
      response.sendRedirect("/room/user/userlogin.jsp");
	%>
		
</body>
</html>