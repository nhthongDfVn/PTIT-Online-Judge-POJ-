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
						href="#">Thêm bài tập</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bbn">Bộ test</a></li>
					</li>
				</ul>
			</div>
			</nav>	
			<h4 style="text-align: center">Thêm testcase</h4	>
			
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
			<form:form action="/PTITCoding/exercise/add-testcase.htm" modelAttribute="testcase" method="POST" >
				<table class="table  ">
					<tbody>
					<form:input  class="form-control" placeholder="1"  path="exercise.exerciseID" value="${exercise.exerciseID}" type="hidden" />	
						<tr>
							<td>Mã testcase</td>
							<td><form:input placeholder="1"  path="numtest"/></td>
						</tr>
						<tr>
							<td>Input</td>	
							<td><form:textarea class="form-control" placeholder="1 3 4" rows="3"  path="input"/></td>
						</tr>
						<tr>
							<td>Output</td>
							<td><form:textarea class="form-control" placeholder="8" rows="3"  path="output"/></td>
							
						</tr>
						<tr>
							<td class="w-25"></td>
							<td class="w-50"><button type="submit" class="btn btn-primary">Thêm testcase</button></td>
						</tr>
					</tbody>		
				</table>
			</form:form>


			</div>
			
			
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</body>
</html>