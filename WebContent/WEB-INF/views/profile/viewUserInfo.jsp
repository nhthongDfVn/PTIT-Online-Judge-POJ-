<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Xem thông tin</title>
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
				<div style="margin-left: 30px">
					 <b><a href="/PTITCoding/profile/view/${userInfo.username}.htm">Thông tin cá nhân |</a>  </b>
					 <a href="/PTITCoding/profile/view/submit/${userInfo.username}.htm">Các bài bạn đã nộp |</a> 
					 <a href="/PTITCoding/profile/update/${userInfo.username}.htm">Cập nhật thông tin |</a> 
					 <a href="/PTITCoding/profile/change-password/${userInfo.username}.htm">Đổi mật khẩu |</a>
					 <a href="/PTITCoding/profile/update/image/${userInfo.username}.htm">Đổi hình ảnh</a>
				</div>

				<img style="margin-left: 400px" src="/PTITCoding/images/profile/${userInfo.username}.png" width="100" height="100">
		<div style="margin-left: 50px">
				<form:form modelAttribute="userInfo" >
					<h6>Username</h6>
					<form:input class="form-control" path="username" disabled="true" />
					<h6>Họ tên</h6> 
					<form:input class="form-control" path="fullname" disabled="true"  />
					<h6>Lớp</h6>
					<form:input class="form-control" path="classs" disabled="true"   />
					<h6>Trường</h6>
					<form:input class="form-control" path="school" disabled="true"  />
					<h6>Email</h6>
					<form:input class="form-control" path="email" disabled="true"   />
					<form:input class="form-control" path="avatar" type="hidden"  />
					<br>
				</form:form>
				
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