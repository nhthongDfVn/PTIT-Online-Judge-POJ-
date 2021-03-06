<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thêm bài tập</title>
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

			</nav>	
			<h4 style="text-align: center">Thêm bài tập</h4	>
			
			<div style="margin-left: 50px">
			<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Thành công!</strong> Bạn có thể xem ở mục <a href="#"class="alert-link">bài tập</a>.
					</div>
			</c:if>
			<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Thất bại!</strong> Không thể thêm mới bài tập
					</div>
			</c:if>
			<form:form action="/PTITCoding/exercise/add-exercise.htm" modelAttribute="exercise" method="POST" >
				<table class="table  ">
					<tbody>
						<tr>
							<td class="w-25">Tên bài tập</td>
							<td class="w-50"><form:input required="required"  placeholder="1A"  path="name"/>
								<br>
								<form:errors style="color:red" path="name"/>
							</td>
							
						</tr>
						<tr>
							<td>Thời gian</td>
							<td><form:input required="required"  placeholder="1.0"  path="time"/> giây
							<br> <form:errors style="color:red" path="time"/>
							</td>
							
						</tr>
						<tr>
							<td>Dạng bài</td>
							<td><form:input required="required"  placeholder="Quy hoạch động"  path="type"/>
							<br> <form:errors style="color:red" path="type"/>
							</td>
							
						</tr>
						<tr>
							<td>Giới hạn bộ nhớ</td>
							<td><form:input required="required"  placeholder="1024"  path="memlimit"/> MB
							<br> <form:errors style="color:red" path="memlimit"/>
							</td>
							
						</tr>
						<tr>
							<td>Mô tả bài tập</td>
							<td><form:textarea class="form-control" placeholder="Mô tả" rows="6"  path="detail"/>
							<br> <form:errors style="color:red" path="detail"/>
							</td>
							
						</tr>
						<tr>
							<td>Input mẫu</td>	
							<td><form:textarea class="form-control" placeholder="1 3 4" rows="3"  path="input"/>
							<br> <form:errors style="color:red" path="input"/>
							</td>
							
						</tr>
						<tr>
							<td>Output mẫu</td>
							<td><form:textarea class="form-control" placeholder="8" rows="3"  path="output"/>
							<br> <form:errors style="color:red" path="output"/>
							</td>		
						</tr>
						<tr>
							<td class="w-25"></td>
							<td class="w-50"><button type="submit" class="btn btn-primary">Thêm bài tập</button></td>
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
<%@include file="../footer.html"%>
</body>
</html>