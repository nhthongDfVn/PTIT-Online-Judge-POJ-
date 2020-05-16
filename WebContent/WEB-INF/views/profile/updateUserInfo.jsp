<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chúng tôi</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <base href="${pageContext.servletContext.contextPath}/">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="../header.html" %>
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
			<div style="margin-left: 50px">
				<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Thành công!</strong>Lưu thông tin</a>.
					</div>
				</c:if>
				<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Thất bại!</strong> Không thể lưu thông tin
					</div>
				</c:if>
				<h3 style="text-align: center">Thông tin cá nhân</h3>
				<form:form action="/PTITCoding/profile/update/${userInfo.username}.htm"
					modelAttribute="userInfo"  method="POST">
					<h6>Username</h6>
					<form:input class="form-control" path="username" disabled="true" />
					<h6>Họ tên</h6> 
					<form:input class="form-control" path="fullname"  />
					<h6>Lớp</h6>
					<form:input class="form-control" path="classs"  />
					<h6>Trường</h6>
					<form:input class="form-control" path="school"  />
					<h6>Email</h6>
					<form:input class="form-control" path="email"  />
					<form:input class="form-control" path="avatar" type="hidden"  />
					<br>
					<div class="col text-center">
						<button type="submit" class="btn btn-primary">Lưu chỉnh
							sửa</button>
					</div>

				</form:form>
			</div>



		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>


</body>
</html>