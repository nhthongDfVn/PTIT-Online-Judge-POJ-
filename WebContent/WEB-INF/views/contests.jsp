<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cuộc thi</title>
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
		<div class="col-sm-8" style="margin-left: 30px">
			<h5>Sắp diễn ra </h5>
			<table class="table table-dark table-hover">
				<tr>
					<th>Tên kỳ thi</th>
					<th>Ngày bắt đầu</th>
					<th>Thời gian</th>
					<th>Đã đăng kí</th>
					<th></th>
				</tr>
				<tr>
					<td>ITMC GRADUATE D18</td>
					<td>26/11/2018 - 08:00</td>
					<td>03:00</td>
					<td>50</td>
					<td>Đăng kí</td>
				</tr>
				<tr>
					<td>PIS Contest	</td>
					<td>26/11/2018 - 08:00</td>
					<td>03:00</td>
					<td>50</td>
					<td>Đăng kí</td>
				</tr>
			</table>
			<h5>Đã diễn ra</h5>
			<table class="table table-dark table-hover">
				<tr>
					<th>Tên kỳ thi</th>
					<th>Ngày bắt đầu</th>
					<th>Thời gian</th>
					<th>Đã đăng kí</th>
					<th></th>
				</tr>
				<tr>
					<td>ITMC GRADUATE D18</td>
					<td>26/11/2018 - 08:00</td>
					<td>03:00</td>
					<td>50</td>
					<td>Xếp hạng</td>
				</tr>
				<tr>
					<td>PIS Contest	</td>
					<td>26/11/2018 - 08:00</td>
					<td>03:00</td>
					<td>50</td>
					<td>Xếp hạng</td>
				</tr>
			</table>
		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
</body>
</html>