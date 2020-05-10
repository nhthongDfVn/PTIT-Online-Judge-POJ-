<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<%@include  file="header.html" %>

<div class="row" style="margin-top: 30px; margin-left: 30px;">
		<div class="col-sm-8">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active"
					data-toggle="tab" href="#home">Chung</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#menu">Bài viết</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#menu">Bài tập</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#menu">Người dùng</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#menu">Cuộc thi</a></li>	
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div id="home" class="container tab-pane active">
					hello 
				</div>
				<div id="menu1" class="container tab-pane fade">
					<br>
					<h3>Menu 1</h3>
					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris nisi ut aliquip ex ea commodo consequat.</p>
				</div>
				<div id="menu2" class="container tab-pane fade">
					<br>
					<h3>Menu 2</h3>
					<p>Sed ut perspiciatis unde omnis iste natus error sit
						voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
				</div>
			</div>
			<a href="#">Xem tất cả</a>



		</div>
	
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
</body>
</html>