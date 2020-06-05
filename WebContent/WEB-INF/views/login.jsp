<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<c:choose>
	<c:when test="${sessionScope.username==null}">
	</c:when>
	<c:otherwise>
		<meta http-equiv="refresh" content="0; url = /PTITCoding/index.htm" />
	</c:otherwise>
</c:choose>
</head>

<body>
<%@include  file="header.html" %>
<div class="container-fluid">
	<div class="row" style="margin-top: 30px">
		<div class="col-xl-8">
			<div class="container">
				<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Sai tên tài khoản hoặc mật khẩu</strong> Vui lòng thử lại
					</div>
				</c:if>

				<h2>Đăng nhập</h2>
				<form:form action="/PTITCoding/login.htm" method="POST" modelAttribute="user">
					<div class="form-group">
						<label for="email">Tên người dùng:</label> 
						<form:input required="required" 
							class="form-control" placeholder="Tên người dùng" path="username"/>
							<form:errors style="color:red" path="username"/>
					</div>
					<div class="form-group">
						<label for="pwd">Mật khẩu:</label> 
						<form:input type="password" required="required"
							class="form-control"  placeholder="Mật khẩu" path="password"/> 
							<form:errors style="color:red"  path="password"/>
					</div>
					<a href="/PTITCoding/register.htm">Chưa có tài khoản?</a> <br><br>
					<button type="submit" class="btn btn-primary">Đăng nhập</button>
				</form:form>
			</div>
		</div>
		<div class="col-xl-4">
			<%@include file="right_page.html"%>
		</div>
	</div>
</div>
</body>
</html>