<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bảng xếp hạng</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="header.html" %>


<div class="row" style="margin-top:30px">
  <div class="col-sm-8">
  <h3>Bảng xếp hạng hiện tại</h3>
  <table class="table table-dark table-hover">
	<tr>
	  <th>Hạng</th>
	  <th>Tên người dùng </th>
	  <th>Số bài</th>
	</tr>
	<c:forEach var ="u" items="${rank}">
	<tr>
		<td> 1 </td>
		<td> ${u.username} </td>
		<td> ${u.score} </td>
	</tr>
	</c:forEach>
	</table>



		</div>
  <div class="col-sm-3">
	<%@include  file="right_page.html" %>
  </div>
</div>
</body>
</html>