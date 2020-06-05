<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Random,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thêm bài viết</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <script src="../ckeditor/ckeditor.js"></script>
  
<c:if test="${!sessionScope.isAdmin.equals(true)}">
	<meta http-equiv="refresh" content="0; url = /PTITCoding/index.htm" />
</c:if>
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
			<h4 style="text-align: center">Bài viết mới</h4	>
			
			<div style="margin-left: 50px">
			<c:if test="${message.equals('success')}">

					<div class="alert alert-success">
						<strong>Thành công!</strong> Bạn có thể xem bài viết ở  <a href="/PTITCoding/index.htm"class="alert-link">trang chủ</a>.
					</div>
			</c:if>
			<c:if test="${message.equals('fail')}">
					<div class="alert alert-danger">
						<strong>Thất bại!</strong> Không thể thêm mới bài viết
					</div>
			</c:if>
			<form:form action="/PTITCoding/post/add-post.htm" modelAttribute="post" method="POST" >
			<h6>Tiêu đề</h6>
			<form:errors style="color:red" path="title"/>
			<form:input required="required" class="form-control" placeholder="Tên bài viết"  path="title"  />
			<h6>Nội dung</h6>
			<form:errors style="color:red" path="body"/>
			<form:textarea required="required" class="form-control" placeholder="Nội dung bài viết" rows="10"  path="body"  />
			<br>
			<div class="col text-center">
     			 	<button  type="submit" class="btn btn-primary">Thêm bài viết</button>
    		</div>
			
			</form:form>
			</div>
			
			
		</div>
		<div class="col-sm-3">
			<%@include file="../right_page.html"%>
		</div>
</div>
</div>
 <script>
       CKEDITOR.replace( 'body' );
</script>
</body>
</html>