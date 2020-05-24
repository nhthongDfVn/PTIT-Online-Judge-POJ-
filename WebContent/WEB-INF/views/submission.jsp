<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <div style="margin-left: 30px">
  <h3 style="text-align: center">Bảng nộp bài</h3>
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
		<c:forEach var="u" items="${submit}">
			<tr>
				<td>${u.submitID}</td>
				<td>${u.timesubmit}</td>
				<td>${u.username}</td>
				<td>${u.exerciseID}</td>
				<td> C++ </td>
				<td>
				<c:choose>
					<c:when test="${u.answer==-1}"><p style="color:red">Biên dịch lỗi</p> </c:when>
					<c:when test="${u.answer==0}"><p style="color:green">Thành công</p></c:when>
					<c:otherwise><p style="color:red"> Sai test ${u.answer}</p></c:otherwise>
				</c:choose>
				</td>	
				<td>${u.timerun} ms</td>
				<td>N/A</td>
			</tr>
		</c:forEach>

	</table>
	
	
	
	
  </div>

