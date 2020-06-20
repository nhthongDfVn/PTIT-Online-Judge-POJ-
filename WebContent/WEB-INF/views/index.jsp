<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	.cont
    {
     
      padding: 20px;
      margin-top: 10px; 
      word-wrap: break-word;"
    }
</style>

<body>
	<%@include file="header.html"%>
	<div class="container-fluid">
		<div class="row" style="margin-top: 30px">
			<div class="col-xl-8" style="margin-left: 30px">
				<c:if test="${sessionScope.isAdmin.equals(true)}">
					<h6><a href="/PTITCoding/post/add-post.htm">Thêm bài viết mới</a></h6>
				</c:if>

				<c:forEach var="u" items="${post}">
					<div class='cont'>
						<h3>
							<a href="/PTITCoding/post/view/${u.postID}.htm">${u.title}</a>
						</h3>
						<p>admin - ${u.date}</p>
						<p>${u.body}</p>
						<hr>
						
						<c:if test="${sessionScope.isAdmin.equals(true)}">
							<a href="/PTITCoding/post/update/${u.postID}.htm">Chỉnh sửa</a>
							<a href="/PTITCoding/post/delete/${u.postID}.htm"  onclick="return confirm('Bạn chắc chắn muốn xoá bài viết?')">Xoá</a>
						</c:if>
					</div>

				</c:forEach>
				<br>
				<ul class="pagination justify-content-center">

					<c:forEach begin="1" end="${allpage}" var="i">
						<c:if test="${i==current}">
							<li class="page-item active"><a class="page-link"
								href="/PTITCoding/index.htm?page=${i}">${i}</a></li>
						</c:if>
						<c:if test="${i!=current}">
							<li class="page-item"><a class="page-link"
								href="/PTITCoding/index.htm?page=${i}">${i}</a></li>
						</c:if>
					</c:forEach>

				</ul>
			</div>
			<div class="col-xl-3">
				<%@include file="right_page.html"%>
			</div>
		</div>
	</div>
<%@include file="footer.html"%>



</body>
</html>