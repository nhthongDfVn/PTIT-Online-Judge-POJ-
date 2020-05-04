<%@page import="com.sun.corba.se.impl.protocol.giopmsgheaders.Message"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

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
						href="/PTITCoding/submit.htm">Nộp bài</a></li>
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
					<h3>1A XIN CHÀO </h3>
					<p>Dữ liệu vào: standard input </p>
					<p>Dữ liệu ra: standard output </p>
					<p>Giới hạn thời gian: 1.0 giây </p>
					<p>Giới hạn bộ nhớ: 128 megabyte </p>
					<p>Đăng bởi: admin </p>
			</div>
			
			<div style="margin-left: 30px; margin-top: 30px">
			<p>Cho n đội bóng tham dự WC, mỗi đội i bạn được biết chỉ số ghi bàn và chỉ số penalty. Khi 2 đội A và B thi đấu với nhau: đội nào có chỉ số ghi bàn lớn hơn sẽ thắng, nếu bằng nhau thì chỉ số penalty của đội nào lớn hơn sẽ thắng. 2 đội hòa nhau nếu cả 2 chỉ số đều bằng nhau. Hình thức thi đấu tại WC như sau: có tổng cộng n*(n-1)/2 trận đấu, hai đội i và j sẽ thi đấu với nhau đúng một lần, mỗi trận đấu đội nào thắng sẽ được 3 điểm, đội thua không được điểm nào, nếu hai đội hòa nhau thì mỗi đội được 1 điểm. Đội nhiều điểm nhất là đội vô địch. </p>	
			<p>INPUT

-dòng đầu tiên chứa n (2<=n<100000)

-n dòng tiếp theo, dòng thứ i lần lượt là tên đội i và chỉ số bàn thắng, chỉ số penalty (các chỉ số là số nguyên dương không quá 10^9), dữ liệu đảm bảo điểm của hai đội nhiều điểm nhất là khác nhau

OUTPUT

Tên đội vô địch WC </p>
			</div>
			
			
			
				
		</div>
		<div class="col-sm-3" style="margin-top: 30px">
			<%@include file="../right_page.html"%>
		</div>
		
</div>
</body>
</html>