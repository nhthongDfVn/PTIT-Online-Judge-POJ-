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
				<td><a href="/PTITCoding/submit/view/${u.submitID}.htm">C++</a> </td>
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

