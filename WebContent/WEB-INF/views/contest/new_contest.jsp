<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thêm cuộc thi</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<style>
	table.fixed { table-layout:fixed; }
	table.fixed td { overflow: hidden; }
</style>

<body>
<%@include  file="../header.html" %>
<div class="row" style="margin-top: 10px">
		<div class="col-sm-8" style="margin-left: 30px ">
			<nav class="navbar navbar-expand-sm">
			<div class="collapse navbar-collapse"
				id="navbarCollapse">
				<ul class="navbar-nav">
					<li class="nav-item">
					<li class="nav-item"><a class="nav-link"
						href="#">Cài đặt</a></li>
					<li class="nav-item"><a class="nav-link"
						href="#">Bộ câu hỏi</a></li>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="#">Thí sinh</a></li>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="#">Thông báo</a></li>
					</li>
				</ul>
			</div>
			</nav>	
			<h4 style="text-align: center">Thêm cuộc thi</h4	>
			
			<div style="margin-left: 50px">
			<form action="/action_page.php">
				<table class="table  ">
					<tbody>
						<tr>
							<td class="w-25">Tên cuộc thi</td>
							<td class="w-50"><input class="form-control" placeholder="PIS Contest" name='name'></td>
						</tr>
						<tr>
							<td>Ngày bắt đầu</td>
							<td><input class="form-control" type="date"  name='date'></td>
						</tr>
						<tr>
							<td>Thời gian bắt đầu</td>
							<td><input class="form-control" type="time" min="00:00" max="11:59" name='time' step="600"></td>
						</tr>
						<tr>
							<td>Lời mở đầu</td>
							<td><textarea class="form-control" placeholder="Mô tả" name="describe" rows="6"></textarea></td>
						</tr>
						<tr>
							<td class="w-25"></td>
							<td class="w-50"><button type="submit" class="btn btn-primary">Thêm cuộc thi</button></td>
						</tr>
					</tbody>
				</table>
			</form>


			</div>
			
			
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</body>
</html>