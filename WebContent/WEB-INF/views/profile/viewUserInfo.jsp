<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chúng tôi</title>
  <meta charset="utf-8">
  <base href="${pageContext.servletContext.contextPath}/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="../header.html" %>
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
			<h2>${userInfo.user.username}</h2>
			<h2>${userInfo.school}</h2>
			<h2>${userInfo.classs}</h2>
			<h2>${userInfo.email}</h2>
			Tên đầy đủ
			<h2>${userInfo.fullname}</h2>
			<a href="/PTITCoding/profile/update/${userInfo.username}.htm">Cập nhật thông tin</a>

		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>


</body>
</html>