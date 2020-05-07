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
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">
			<div class="card" style="width: 400px">
				<img class="card-img-top"
					src="https://www.w3schools.com/bootstrap4/img_avatar1.png"
					alt="Card image" style="width: 100%">
				<div class="card-body">
					<h4 class="card-title">nhthongDfVn</h4>
					<p class="card-text">Some example text some example text. John
						Doe is an architect and engineer</p>
					<a href="#" class="btn btn-primary">See Profile</a>
				</div>
			</div>


		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>


</body>
</html>