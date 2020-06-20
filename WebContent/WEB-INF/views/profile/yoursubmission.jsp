

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chúng tôi</title>
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
<div class="row" style="margin-top: 30px">
		<div class="col-sm-8">

				<div style="margin-left: 30px">
					 <a href="/PTITCoding/profile/view/${userInfo.username}.htm">Thông tin cá nhân |</a>  
					<b> <a href="/PTITCoding/profile/view/submit/${userInfo.username}.htm">Các bài bạn đã nộp |</a> </b> 
					 <a href="/PTITCoding/profile/update/${userInfo.username}.htm">Cập nhật thông tin |</a> 
					 <a href="/PTITCoding/profile/change-password/${userInfo.username}.htm">Đổi mật khẩu |</a>
					 <a href="/PTITCoding/profile/update/image/${userInfo.username}.htm">Đổi hình ảnh</a>
				</div>
				<br>
			
				<div style="margin-left: 30px">
					<table class="table table-dark table-hover">
						<tr>
							<th>ID</th>
							<th>Thời điểm nộp</th>
							<th>Người nộp</th>
							<th>Mã bài</th>
							<th>Ngôn ngữ</th>
							<th>Kết quả</th>
							<th>Thời gian chạy</th>
							<th>Bộ nhớ</th>
						</tr>
						<c:forEach var="u" items="${submit}">
							<tr>
								<td><a href="/PTITCoding/submit/view/${u.submitID}.htm">${u.submitID}</a></td>
								<td><a href="/PTITCoding/submit/view/${u.submitID}.htm">${u.timesubmit}</a></td>
								<td><a href="/PTITCoding/submit/view/${u.submitID}.htm">${u.username}</a></td>
								<td><a href="/PTITCoding/exercise/view/${u.exerciseID}.htm">${u.exerciseID}</a></td>
								<td><a href="/PTITCoding/submit/view/${u.submitID}.htm">C++</a>
								</td>
								<td><c:choose>
										<c:when test="${u.answer==-1}">
											<p style="color: red">Biên dịch lỗi</p>
										</c:when>
										<c:when test="${u.answer==0}">
											<p style="color: green">Thành công</p>
										</c:when>
										<c:otherwise>
											<p style="color: red">Sai test ${u.answer}</p>
										</c:otherwise>
									</c:choose></td>
								<td>${u.timerun}ms</td>
								<td>N/A</td>
							</tr>
						</c:forEach>

					</table>
					<br>
					<ul class="pagination justify-content-center">

						<c:forEach begin="1" end="${allpage}" var="i">
							<c:if test="${i==current}">
								<li class="page-item active"><a class="page-link"
									href="/PTITCoding/exercise.htm?page=bcb&next=${i}">${i}</a></li>
							</c:if>
							<c:if test="${i!=current}">
								<li class="page-item"><a class="page-link"
									href="/PTITCoding/exercise.htm?page=bcb&next=${i}">${i}</a></li>
							</c:if>
						</c:forEach>

					</ul>
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

