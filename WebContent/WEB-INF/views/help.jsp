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
		<div style="margin-left: 30px">
			<h3>Tạo bài tập mới (admin)</h3>
			<p>- Vào mục bài tập, chọn mục Thêm bài tập</p>
			<p>- Nhập các tham số cần thiết cho bài tập</p>
			<p>- Tải tệp tin <b>đáp án</b> cho bài giải</p>
			<p>- Tải các tệp <b>testcase</b> cho bài tập</p>
			<p>- Hoàn thành việc tạo bài tập</p>
			
			<h3>Nộp bài tập</h3>
			<p>- Đăng nhập vào hệ thống</p>
			<p>- Chọn chi tiết bài tập</p>
			<p>- Chọn <b> Nộp bài</b></p>
			<p>- Chọn ngôn ngữ, nộp theo 2 hình thức file hoặc text. Bấm nộp bài</p>
			<p>- Xem kết quả chấm chính thức tại Bảng chấm bài</p>
			
			
			<h3>Các kết quả chấm bài</h3>
			<p>- Thành công: bài làm đúng tất cả các test.</p>
			<p>- Sai ở test 1: có một test cho kết quả sai.</p>
			<p>- Biên dịch lỗi: lỗi chạy chương trình (tràn mảng, chia cho0, exit code khác 0...).</p>
			<p>- Quá thời gian: chạy quá giới hạn thời gian của bài tập.</p>
			<p>- Quá bộ nhớ: chạy quá giới hạn bộ nhớ của bài tập.</p>
		</div>

		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>

</div>
<%@include file="footer.html"%>
</body>
</html>