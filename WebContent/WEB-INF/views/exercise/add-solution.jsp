<%@page import="com.sun.corba.se.impl.protocol.giopmsgheaders.Message"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bài tập</title>
  <meta charset="utf-8">
   <base href="${pageContext.servletContext.contextPath}/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<%@include  file="../header.html" %>
<div class="container-fluid">
<div class="row" style="margin-top: 0px">
		<div class="col-sm-8">

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
		
				
			<div style="margin-left: 50px">
			<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Đã có Bài giải mẫu</strong> </a>
					</div>
			</c:if>
			<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Chưa có lời giải hoặc Lời giải không hợp lệ!</strong>
					</div>
			</c:if>	
			
				<div style="margin-top: 50px">
					<c:if test="${message.equals('success')}">

						<div class="alert alert-success">
							<textarea disabled rows="15" cols="110">${body}</textarea>  
						</div>
					</c:if>
					<form
						action="/PTITCoding/exercise/add-solution/${exercise.exerciseID}.htm"
						method="POST" enctype="multipart/form-data" >
						<input type="file" name="file">
						<button>Cập nhật</button>
					</form>
				</div>
			</div>	
		</div>
		<div class="col-sm-3" style="margin-top: 30px">
			<%@include file="../right_page.html"%>
		</div>
		
</div>
</div>
<%@include file="../footer.html"%>
</body>
</html>