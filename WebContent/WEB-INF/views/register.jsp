<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Đăng kí</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="header.html" %>
<div class="container-fluid">
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
			<div class="container">
				<h2>Đăng kí</h2>
				<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Thành công!</strong> Đăng kí tài khoản thành công
					</div>
				</c:if>
				<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Thất bại!</strong> Đăng kí thất bại
					</div>
				</c:if>
				<form:form action="/PTITCoding/register.htm" method="POST" modelAttribute="user">
					<div class="form-group">
						<label for="email">Tên tài khoản:</label> 
						<form:input type="text" required="required"
							class="form-control"  placeholder="Tên tài khoản"
							path="username"/>
							<form:errors style="color:red" path="username"/>
					</div>
					<div class="form-group">
						<label for="pwd">Mật khẩu:</label> 
						<form:input type="password" required="required"
							class="form-control" placeholder="Mật khẩu" 	path="password"/>
							<form:errors style="color:red" path="password"/>
					</div>
					<div class="form-group">
						<label for="pwd">Nhập lại mật khẩu:</label> <input type="password" required
							class="form-control" id="pwd1" placeholder="Nhập lại mật khẩu" name="re-password">
							 <span  style="color:red"> ${errmess}</span>
					</div>
					<a href="/PTITCoding/login.htm">Đã có tài khoản?</a> <br><br>
					<button type="submit" class="btn btn-primary">Đăng kí</button>
				</form:form>
			</div>
		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
	
<%@include file="footer.html"%>
</div>
</body>
</html>