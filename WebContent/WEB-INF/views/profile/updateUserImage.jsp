<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cập nhật thông tin</title>
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
<div class="container-fluid">
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
		<div style="margin-left: 30px">
					 <a href="/PTITCoding/profile/view/${userInfo.username}.htm">Thông tin cá nhân |</a> 
					 <a href="/PTITCoding/profile/view/submit/${userInfo.username}.htm">Các bài bạn đã nộp |</a> 
					 <a href="/PTITCoding/profile/update/${userInfo.username}.htm">Cập nhật thông tin |</a>
					 <a href="/PTITCoding/profile/change-password/${userInfo.username}.htm">Đổi mật khẩu |</a> 
					<b><a href="/PTITCoding/profile/update/image/${userInfo.username}.htm">Đổi hình ảnh</a></b>
				</div>
		
		
		
			<div style="margin-left: 3px">
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
				
				
				
				
				<img style="margin-left: 400px" src="/PTITCoding/images/profile/${userInfo.username}.png" width="100" height="100">
		<div style="margin-left: 50px">
				<br>
				<form action="/PTITCoding/profile/update/image/${userInfo.username}.htm"
				method="post" enctype="multipart/form-data">
				<input type="file" name="image">
				<button>Cập nhật</button>
				
				</form>
				
				</div>
				
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