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
<div class="container-fluid">
<div class="row" style="margin-top: 0px; margin-left: 20px">
		<div class="col-sm-8">
		<div style="margin-top: 30px" > 
			<h4 style="text-align: center">Chi tiết bài nộp</h4>
			<table class="table table-dark table-hover">
				<tr>
					<th>ID</th>
					<th>Thời điểm nộp</th>
					<th>Người nộp</th>
					<th>Tên bài</th>
					<th>Ngôn ngữ</th>
					<th>Kết quả</th>
					<th>Thời gian chạy</th>
					<th>Bộ nhớ</th>
				</tr>
					<tr>
						<td>${submit.submitID}</td>
						<td>${submit.timesubmit}</td>
						<td>${submit.username}</td>
						<td>${submit.exerciseID}</td>
						<td>C++</td>
						<td><c:choose>
								<c:when test="${submit.answer==-1}">
									<p style="color: red">Biên dịch lỗi</p>
								</c:when>
								<c:when test="${submit.answer==0}">
									<p style="color: green">Thành công</p>
								</c:when>
								<c:otherwise>
									<p style="color: red">Sai test ${submit.answer}</p>
								</c:otherwise>
							</c:choose></td>
						<td>${submit.timerun}ms</td>
						<td>N/A</td>
					</tr>
			</table>

				<c:forEach var="s" items="${inout}">
							<div class="alert alert-success">
								<h6>Input</h6>
								<textarea disabled rows="3" cols="110">${s.input}</textarea>
								<h6>Our Output</h6>
								<textarea disabled rows="3" cols="110">${s.output}</textarea>
								<h6>Your Output</h6>
								<textarea disabled rows="3" cols="110">${s.output1}</textarea>
							</div>

					
				</c:forEach>



			</div>




		</div>
		<div class="col-sm-3" style="margin-top: 30px">
			<%@include file="../right_page.html"%>
		</div>
		
</div>
</div>
</body>
</html>