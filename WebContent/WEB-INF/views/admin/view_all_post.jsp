<%@page import="com.sun.corba.se.impl.protocol.giopmsgheaders.Message"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tất cả bài viết</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

	<%@include file="../header.html"%>

	<div class="row" style="margin-top: 10px">
		<div class="col-sm-8"  style="margin-left: 10px">


			<table id="exercise" class="table table-hover"
				style="margin-top: 20px" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th class="th-sm">Tiêu đề</th>
						<th class="th-sm"></th>
						<th class="th-sm"></th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach var="u" items="${post}">
						<tr>
							<td>${u.title}</td>
							<td><a href="/PTITCoding/post/update/${u.postID}.htm"">
									Sửa </a></td>
							<td><a href="/PTITCoding/post/delete/${u.postID}.htm"
								data-toggle="modal" data-target="#myModal"
								onClick='GetCellValues()'"> Xoá </a></td>
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
								<button type="button" class="btn btn-success"
									data-dismiss="modal">No</button>
						</form>
					</div>

				</div>
			</div>
		</div>


	</div>
	<div class="col-sm-3" style="margin-top: 30px">
		<%@include file="../right_page.html"%>
	</div>
	</div>
	<script>
		function GetCellValues() {
			var table = document.getElementById('mytable');
			for (var r = 0, n = table.rows.length; r < n; r++) {
				for (var c = 0, m = table.rows[r].cells.length; c < m; c++) {
					alert(table.rows[r].cells[c].innerHTML);
				}
			}
		}
	</script>
</body>
</html>