<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%
	request.setCharacterEncoding("UTF-8");
	int no = Integer.parseInt(request.getParameter("no"));
	
	GuestbookDao guestbookDao = new GuestbookDao();
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제폼</title>
</head>

<body>
	<form action="./delete.jsp" method="post">
		비밀번호<input type="password" id="pass" name="password" value="">
		<button type="submit">확인</button>
		<input type="hidden" name="no" value="<%=request.getParameter("no")%>">
	</form>
	<a href="./addList.jsp">메인으로 돌아가기</a><br>
</body>

</html>