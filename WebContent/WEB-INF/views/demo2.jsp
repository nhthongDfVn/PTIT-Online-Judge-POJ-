<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
</head>
<body>
	<table class="table table-dark table-hover">
	<tr>
		<th>PostID</th>
		<th>Title</th>
		<th>Date</th>
	</tr>
	<c:forEach var ="u" items="${post}">
	<tr>
		<td> ${u.postID} </td>
		<td> ${u.title} </td>
		<td> ${u.body} </td>
	</tr>
	</c:forEach>
	</table>
	
</body>
</html>