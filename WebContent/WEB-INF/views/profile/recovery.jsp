<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Quên mật khẩu</title>
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
<div class="container-fluid">
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
		
		<div style="margin-left: 50px">

					<c:if test="${message.equals('success')}">

						<div class="alert alert-success">
							<strong>Vui lòng kiểm tra email của bạn
						</div>
					</c:if>
					<c:if test="${message.equals('fail')}">
						<div class="alert alert-danger">
							<strong>Thất bại!</strong> Có lỗi xảy ra trong quá trình thực hiện
						</div>
					</c:if>
					<form action="/PTITCoding/recover.htm" method="POST" >
					<h6>Nhập email của bạn</h6>
					<input class="form-control" name="email" type="email" required />
					<p style="color:red">${emailpasserr} </p>
		
					<br>
					<div class="col text-center">
						<button type="submit" class="btn btn-primary"> Khôi phục mật khẩu</button>
					</div>
				</form>
		</div>
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>

</div>
<%@include file="../footer.html"%>
</body>
</html>