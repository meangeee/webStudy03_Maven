<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	.error{
		color: red;
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
<c:url value="/prod/prodInsert.do" var="insertURL"/>
<button type="button" onclick="location.href='${insertURL}';">신규상품 등록</button>
<table class="table table-bordered table-striped">
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>분류명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="prod" items="${prodList }">
			<c:url value="/prod/prodView.do" var="viewURL">
				<c:param name="what" value="${prod.prod_id }" />
			</c:url>
			<tr>
				<td>${prod.prod_id }</td>
				<td><a href="${viewURL }">${prod.prod_name }</a></td>
				<td>${prod.lprod_nm }</td>
				<td>${prod.buyer_name }</td>
				<td>${prod.prod_cost }</td>
				<td>${prod.prod_price }</td>
				<td>${prod.prod_mileage }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>











