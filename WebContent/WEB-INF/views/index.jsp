<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Trang chủ</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<style>
table.dataTable thead .sorting:after, table.dataTable thead .sorting:before,
	table.dataTable thead .sorting_asc:after, table.dataTable thead .sorting_asc:before,
	table.dataTable thead .sorting_asc_disabled:after, table.dataTable thead .sorting_asc_disabled:before,
	table.dataTable thead .sorting_desc:after, table.dataTable thead .sorting_desc:before,
	table.dataTable thead .sorting_desc_disabled:after, table.dataTable thead .sorting_desc_disabled:before
	{
	bottom: .5em;
}
</style>

<body>
<%@include  file="header.html" %>

<div class="row" style="margin-top: 30px">
		<div class="col-sm-8" style="margin-left: 30px ">
			<h2>Trang này dành cho admin</h2>
			<%
				Random random = new Random();

				// Trả về ngẫu nhiên (0, 1 hoặc 2).
				int randomInt = random.nextInt(3);

				if (randomInt == 0) {
			%>

			<h1>
				Random value =<%=randomInt%></h1>

			<%
				} else if (randomInt == 1) {
			%>

			<h2>
				Random value =<%=randomInt%></h2>

			<%
				} else {
			%>
			<h3>
				Random value =<%=randomInt%></h3>
			<%
				}
			%>
			
		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
</body>
</html>