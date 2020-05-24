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

<script language="javascript" type="text/javascript"
	src="../editarea/edit_area/edit_area_full.js"></script>
<script language="javascript" type="text/javascript">
	editAreaLoader.init({
		id : "textarea_1" // textarea id
		,
		syntax : "css" // syntax to be uses for highgliting
		,
		start_highlight : true
	// to display with highlight mode on start-up
	});
</script>

</head>
<body>
<%@include  file="header.html" %>
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8" style="margin-left: 30px" >
			<h3 style="text-align: center; margin-bottom: 30px">Nộp bài giải </h3>


			<form action="/PTITCoding/submit/${exercise.exerciseID}.htm" method='POST' enctype="multipart/form-data">
				<input class="form-control" id="disabledInput" type="text" name="exerciseID" value="${exercise.exerciseID}"
						placeholder="Tên bài: ${exercise.name}" disabled>
					<p>Ngôn ngữ</p> 
					<select
						class="form-control" id="sel1" name="language">
	
						<option>C++</option>
					</select>
					<label for="comment">Bài giải</label>
					<textarea id="textarea_1" class="form-control" placeholder="Code" name="code" rows="15"></textarea>
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