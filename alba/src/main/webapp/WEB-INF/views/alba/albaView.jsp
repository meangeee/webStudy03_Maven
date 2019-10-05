<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<h4 class="text-center">${alba.al_name }</h4>
	<table class="table table-bordered">
		<tr>
			<th>아이디</th>
			<td>${alba.al_id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${alba.al_name}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${alba.al_age}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${alba.al_address}</td>
		</tr>
		<tr>
			<th>번호</th>
			<td>${alba.al_hp}</td>
		</tr>
		<tr>
			<th>스펙</th>
			<td>${alba.al_spec}</td>
		</tr>
		<tr>
			<th>자기소개</th>
			<td>${alba.al_desc}</td>
		</tr>
		<tr>
			<th>학력사항</th>
			<c:choose>
				<c:when test="${not empty alba.gradeList }">
					<c:forEach var="grade" items="${alba.gradeList }">
						<td>${grade.gr_name}</td>
					</c:forEach>
				</c:when>
			</c:choose>
		</tr>
		<tr>
			<th>경력</th>
			<td>${alba.al_career}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${alba.al_gen}</td>
		</tr>
		<tr>
			<th>혈액형</th>
			<td>${alba.al_btype}</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>${alba.al_mail}</td>
		</tr>

		<tr>
			<th>자격증</th>
			<c:choose>
				<c:when test="${not empty alba.licenseAlbaList }">
					<c:forEach var="lic" items="${alba.licenseAlbaList }">
						<c:url var="viewURL" value="/alba/licenseImage.do">
							<c:param name="al_id" value="${alba.al_id }" />
						</c:url>
						<c:forEach var="license" items="${lic.licenseList}">
							<td><a href="${viewURL }">${license.lic_name }</a></td>
						</c:forEach>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td>자격증 없음</td>	
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</body>
</html>