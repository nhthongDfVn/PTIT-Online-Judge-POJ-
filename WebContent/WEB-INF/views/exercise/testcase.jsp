<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Testcase</title>
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
						href="#">Chỉnh sửa bài tập</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm/testcase/">Bộ test</a></li>
					</li>
				</ul>
			</div>
			</nav>	
			<h4 style="text-align: center"> Test case</h4	>
			<div style="margin-left: 50px">
				<table id="exercise" class="table table-hover"
					style="margin-top: 20px" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th class="th-sm">ID</th>
							<th class="th-sm">Input</th>
							<th class="th-sm">Output</th>
							<th class="th-sm"></th>
							<th class="th-sm"></th>
						</tr>
					</thead>
					<tbody id="myTable">
						<c:forEach var="u" items="${testcase}">
							<tr>
								<td>${u.testcaseID}</td>
								<td>${u.input}</a></td>
								<td>${u.output}</a></td>
								<td>Sửa</a></td>
								<td>Xoá</a></td>
							</tr>
						</c:forEach>
						<a href="/PTITCoding/exercise/add-testcase/.htm">Thêm testcase</a>

					</tbody>
				</table>
			</div>
			
			
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</body>
</html>