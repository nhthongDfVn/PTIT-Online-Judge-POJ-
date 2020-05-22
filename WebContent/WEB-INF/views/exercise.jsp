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
<div class="container-fluid">
<%@include  file="header.html" %>

<div class="row" style="margin-top: 0px">
		<div class="col-sm-8">

			<nav class="navbar navbar-expand-sm">
			<div class="collapse navbar-collapse"
				id="navbarCollapse">
				<ul class="navbar-nav">
					<li class="nav-item">
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bt">Bài tập</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bbn">Bài bạn nộp</a></li>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="/PTITCoding/exercise.htm?page=bcb">Bảng chấm bài</a></li>
					</li>
				</ul>
			</div>
			</nav>			
		<%
			if ((request.getParameter("page").equals("bbn"))){
		%>
		<h1>Bạn đang gọi bài bạn nộp</h1>
		<%
			}
			else if ((request.getParameter("page").equals("bcb"))) {
		%>
			<%@include  file="submission.jsp" %>
		<%} 
			else {
		%>
		  <input class="form-control" id="myInput" type="text" placeholder="Mã bài, tên bài, dạng bài, ...">
			<table id="exercise" class="table table-hover"
				style="margin-top: 20px" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th class="th-sm">Mã bài</th>
						<th class="th-sm">Tên bài</th>
						<th class="th-sm">Dạng bài</th>
						<th class="th-sm">Số người giải</th>
						<th class="th-sm"></th>
						<th class="th-sm"></th>
						<th class="th-sm"></th>
					</tr>
				</thead>
				<tbody id="myTable">
				<c:forEach var="u" items="${exercise}">
				   <tr>
						<td>${u.exerciseID}</td>
						<td><a href="/PTITCoding/exercise/view/${u.exerciseID}.htm">${u.name}</a></td>
						<td>${u.type}</td>
						<td>64</td>
						<td>✔️</td>
						<td><a href="/PTITCoding/exercise/update/${u.exerciseID}.htm""> Sửa </a></td>
						<td><a href="/PTITCoding/exercise/delete/${u.exerciseID}.htm" data-toggle="modal" data-target="#myModal" onClick='GetCellValues()' "> Xoá </a></td>
					</tr>
				</c:forEach>
					
				</tbody>
			</table>

			<!-- Delete -->   
			<div class="modal" id="myModal">
				<div class="modal-dialog">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">Xoá bài viết</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<form action="delete.htm" method="post">
							<input type="hidden" name="id" id="id">
							<!-- Modal body -->
							<div class="modal-body">Bạn thật sự muốn xoá bài viết?</div>

							<!-- Modal footer -->
							<div class="modal-footer">


								<button type="submit" class="btn btn-danger"
									data-dismiss="modal">Yes</button>
						<button type="button" class="btn btn-success" data-dismiss="modal">No</button>
						</form>
						</div>

					</div>
				</div>
			</div>


			<%
				}
			%>
		
			
		
					</div>
		<div class="col-sm-3" style="margin-top: 30px">
			<%@include file="right_page.html"%>
		</div>
</div>
<script>
$(document).ready(function(){
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});

function GetCellValues() {
    var table = document.getElementById('mytable');
    for (var r = 0, n = table.rows.length; r < n; r++) {
        for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
            alert(table.rows[r].cells[c].innerHTML);
        }
    }
}
	
	</script>
	</div>
</body>
</html>