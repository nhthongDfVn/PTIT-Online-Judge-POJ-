<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="header.html" %>

<div class="row" style="margin-top: 30px; margin-left: 30px;">
		<div class="col-sm-8">
			<h3 style="text-align: center"> Danh sách coder hiện tại </h3>
		 <table class="table table table-hover">
	<tr>
	  <th>Username</th>
	  <th>Fullname</th>
	  <th>Email</th>
	</tr>
		<c:forEach var="u" items="${user}">
			<tr>
				<td>${u.username}</td>
				<td>${u.fullname}</td>
				<td>${u.email}</td>
			</tr>
		</c:forEach>

	</table>
		
	</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
</body>
</html>