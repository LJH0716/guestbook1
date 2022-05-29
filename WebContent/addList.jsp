<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>

<%
	GuestbookDao guestbookDao = new GuestbookDao();
	List<GuestbookVo> guestbookList = guestbookDao.select();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록입니다</title>
</head>

<body>
	<form action="./add.jsp" method="post">
		<table border="1">
		<tr>
			<td><label for="name">이름</label></td>
			<td><input id="name" type="text" name="name" value=""></td>
			<td><label for="pw">비밀번호</label></td>
			<td><input id="pw" type="text" name="password" value=""></td>
		</tr>
		<tr>
			<td colspan="4"><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="submit">등록</button>
			</td>
		</tr>
		</table>
	</form>
	<br>

	<% for (int i=0; i < guestbookList.size(); i++) {%>
		<table border="1">
			<tr>
				<td><%=guestbookList.get(i).getNo()%></td>
				<td><%=guestbookList.get(i).getName()%></td>
				<td><%=guestbookList.get(i).getRegDate()%></td>
				<td><a href="./deleteForm.jsp?no=<%=guestbookList.get(i).getNo()%>">[삭제]</a></td>
			</tr>
			<tr>
				<td colspan="4">
					<%=guestbookList.get(i).getContent()%>
				</td>
			</tr>
		</table>
		<br>
	<%} %>	


</body>

</html>