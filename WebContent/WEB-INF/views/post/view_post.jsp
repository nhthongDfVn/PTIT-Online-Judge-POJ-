<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> ${post.title}</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <script src="../ckeditor/ckeditor.js"></script>
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
			<div style="margin-left: 30px" >
			<h3 style="text-align: center">${post.title} </h3>
			<p>${post.body} </p>
			<p style="text-align: right;">${post.date} </p>	
			</div>
		<h5 style="margin-left: 30px;color:blue ">Bình luận</h5>


		<div style="margin-left: 30px" >
		<c:forEach var="u" items="${old}">
					<div class='cont'>
						<b>${u.username}</b>
						<p>${u.comment}</p>
						<c:if test="${sessionScope.isAdmin.equals(true)}">
							
							<a href="#"  onclick="return confirm('Bạn chắc chắn muốn xoá bình luận?')">Xoá</a>
						</c:if>
					</div>

				</c:forEach>


					<c:choose>
						<c:when test="${sessionScope.username==null}">
					         (Đăng nhập để thêm bình luận)
						</c:when>
						<c:otherwise>
							<form:form
								action="/PTITCoding/post/add-comment/${post.postID}.htm"
								modelAttribute="cmt" method="POST">

								<form:textarea required="required" class="form-control"
									placeholder="Nội dung bài viết" rows="3" path="comment" />
								<br>
								<div>
									<i><button type="submit" class="btn btn-primary">Bình
										luận</button></i>
								</div>

							</form:form>
						</c:otherwise>
					</c:choose>



				</div>

			</div>	
		
		
		
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</div>
<%@include file="../footer.html"%>
</body>
</html>