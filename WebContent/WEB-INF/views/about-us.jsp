<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chúng tôi</title>
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
			<h3>Về dự án</h3>
			<p>Ứng dụng lập trình web được viết bởi Nguyễn Hoàng Thống</p>
			<p>Project: Thiết kế web</p>
			<p>Ngày hoàn thành: 07/06/2020</p>
			<p>Phiên bản: Beta 1.0</p>
			<h3>Sứ mạng</h3>
			<p>Nộp bài lấy điểm đã</p>
			<p>Nâng cao khả năng lập trình của các bạn sinh viên</p>
			<h3>Liên hệ</h3>
			<p><a href="https://www.facebook.com/nhthongDfVn">Facebook</a></p>
			<p><a href="https://twitter.com/hoangthongith">Twitter</a></p>
			<p><a href="">Discord: nhthongDfVn#5288</a></p>


		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>

</div>
<%@include file="footer.html"%>
</body>
</html>