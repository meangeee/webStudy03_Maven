<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	a{
		cursor: pointer;
	}
</style>	
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<h4>자유게시판 </h4>
<table class="table table-bordered">
	<thead>
	<c:set var="board" value="${board }"/>
	<tr>
		<th>글번호</th>
		<td>${board.bo_no }</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td>${board.bo_title }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.bo_writer }</td>
	</tr>
	<tr>
		<th>작성날짜</th>
		<td>${board.bo_date }</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${board.bo_hit }</td>
	</tr>
	<tr>
		<th>좋아요</th>
		<td>${board.bo_like }</td>
	</tr>
	<tr>
		<th>아이피주소</th>
		<td>${board.bo_ip }</td>
	</tr>
	</thead>
	
	<tr>
		<td>
		<c:url value="/board/boardInsert.do" var="insertURL">
			<c:param name="bo_parent" value="${board.bo_no }" />
		</c:url>
		<input type="button" 
		value="답글쓰기"
		onclick="locatino.href='${insertURL}';"/>
		</td>
	</tr>
</table>
<script type="text/javascript">

	
</script>
</body>
</html>




