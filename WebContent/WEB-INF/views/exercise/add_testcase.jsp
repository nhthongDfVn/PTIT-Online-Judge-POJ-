<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thêm testcase</title>
  <meta charset="utf-8">
  <base href="${pageContext.servletContext.contextPath}/">
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
<div class="container-fluid">
<div class="row" style="margin-top: 10px">
		<div class="col-sm-8" style="margin-left: 30px ">
			<nav class="navbar navbar-expand-sm">
			<div class="collapse navbar-collapse"
				id="navbarCollapse">
				<ul class="navbar-nav">
					<li class="nav-item">
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise/view/${exercise.exerciseID}.htm">Bài tập</a></li>
				</ul>
			</div>
			</nav>	
			<h4 style="text-align: center; margin-bottom: 40px">Thêm testcase</h4	>
			
			<div style="margin-left: 50px">
			<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Thành công!</strong> Bạn đã thêm 1 testcase </a>.
					</div>
			</c:if>
			<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Thất bại!</strong> Không thể thêm mới testcase
					</div>
			</c:if>
			
			<c:forEach var="s" items="${items}">
				<div class="alert alert-success">
							Input
							<textarea disabled rows="3" cols="110">${s.input}</textarea>  
							Output
							<textarea disabled rows="3" cols="110">${s.output}</textarea> 
						</div>
			</c:forEach>
			
			<form
						action="/PTITCoding/exercise/add-testcase/${exercise.exerciseID}.htm"
						method="POST" enctype="multipart/form-data" >
						<input type="file" name="file">
						<button>Thêm mới testcase</button>
					</form>


			</div>
			
			
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</div>
</body>
</html>