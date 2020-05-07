<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Nộp bài</title>
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
		<div class="col-sm-8" style="margin-left: 30px" >
			<h3 style="text-align: center">Nộp bài giải </h3>


			<form action="/action_page.php" method='POST' enctype="multipart/form-data">
				<input class="form-control" id="disabledInput" type="text"
						placeholder="Tên bài: TINH TONG" disabled>
				<p>Ngôn ngữ</p> 
					<select
						class="form-control" id="sel1" name="language">
						<option>C</option>
						<option>C++</option>
						<option>C++14</option>
						<option>Java</option>
						<option>Python</option>
						<option>Python3</option>
						<option>Scala</option>
						<option>PHP</option>
						<option>Perl</option>
						<option>CSharp</option>
					</select>
					<label for="comment">Bài giải</label>
					<textarea class="form-control" placeholder="Code" name="code" rows="5"></textarea>
					<p>Hoặc nộp bằng file</p>
					<input type="file" class="form-control-file border" name="myfile"><br>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>

		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
<%@include file="footer.html"%>
</body>
</html>