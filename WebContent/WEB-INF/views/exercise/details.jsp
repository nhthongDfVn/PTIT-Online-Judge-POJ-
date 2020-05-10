<%@page import="com.sun.corba.se.impl.protocol.giopmsgheaders.Message"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bài tập</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<%@include  file="../header.html" %>

<div class="row" style="margin-top: 0px">
		<div class="col-sm-8">

			<nav class="navbar navbar-expand-sm">
			<div class="collapse navbar-collapse"
				id="navbarCollapse">
				<ul class="navbar-nav">
					<li class="nav-item">
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/submit/${exercise.exerciseID}.htm">Nộp bài</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bbn">Bài bạn nộp</a></li>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bcb">Bảng chấm bài</a></li>
					</li>
				</ul>
			</div>
			</nav>		
			
			<div style="text-align:center;line-height: 0.5">
				<h3>${exercise.name}</h3>
				<p>Dữ liệu vào: standard input </p>
				<p>Dữ liệu ra: standard output </p>
				<p>Giới hạn thời gian: ${exercise.time} giây </p>
				<p>Giới hạn bộ nhớ: ${exercise.memlimit}  megabyte </p>
				<p>Đăng bởi: admin </p>			
			</div>
			
			<div style="margin-left: 30px; margin-top: 30px">
			<p>${exercise.detail}</p>	
			<p><strong> INPUT</strong></p>
			<p>${exercise.input}</p>	
			<p><strong> OUTPUT</strong></p>
			<p>${exercise.output}</p>	
			</div>
			
			
				
		</div>
		<div class="col-sm-3" style="margin-top: 30px">
			<%@include file="../right_page.html"%>
		</div>
		
</div>
</body>
</html>