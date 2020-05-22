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
      background-color: #CCFF66;
      padding: 20px;
      margin-top: 10px;
      border: 6px double #8A2BE2; 
      word-wrap: break-word;"
    }
</style>

<body>
<div class="container-fluid">
<%@include  file="header.html" %>

<div class="row" style="margin-top: 30px">
		<div class="col-sm-8" style="margin-left: 30px ">
		<h1>404 Not Found </h1>
			

		</div>
		<div class="col-sm-3">
			<%@include file="right_page.html"%>
		</div>
</div>
<%@include  file="footer.html" %>
</div>
</body>
</html>