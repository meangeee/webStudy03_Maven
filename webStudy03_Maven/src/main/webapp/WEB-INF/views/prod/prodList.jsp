<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<table>
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
				<tr>
					<td>${prod.prod_id}</td>
					<td>${prod.prod_name }</td>
					<td>${prod.lprod_nm }</td>
					<td>${prod.buyer_name }</td>
					<td>${prod.prod_cost }</td>
					<td>${prod.prod_price }</td>
					<td>${prod.pord_mileage }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>