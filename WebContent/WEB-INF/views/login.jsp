<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>

<body>
<%@include  file="header.html" %>

	<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
			<div class="container">
			<p style="color: Red;">${message}</p>
				<h2>Đăng nhập</h2>
				<form action="/PTITCoding/login1.htm" method="POST">
					<div class="form-group">
						<label for="email">Tên người dùng:</label> <input type="text"
							class="form-control" id="email" placeholder="Tên người dùng"
							name="username">
					</div>
					<div class="form-group">
						<label for="pwd">Mật khẩu:</label> <input type="password"
							class="form-control" id="pwd" placeholder="Mật khẩu"
							name="password">
					</div>
					<a href="/PTITCoding/register.htm">Chưa có tài khoản?</a> <br><br>
					<button type="submit" class="btn btn-primary">Đăng nhập</button>
				</form>
			</div>
		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
	</div>
</body>
</html>