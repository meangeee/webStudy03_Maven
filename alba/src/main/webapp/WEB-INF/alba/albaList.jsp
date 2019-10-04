<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>휴대폰</th>
			<th>성별</th>
			<th>학력</th>
			<th>혈액형</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>
	$.ajax({
		data : 
	})
</body>
</html>