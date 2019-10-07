<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
.error {
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
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<body>
	<form method="post" enctype="multipart/form-data">
		<table class="table table-bordered">

			<tr>
				<th>아이디</th>
				<td><input class="form-control" name="al_id" 
				value="${alba.al_id }"
				 readonly />
				</td>
			</tr>

			<tr>
				<th>이름</th>
				<td><input class="form-control" name="al_name" 
				value="${alba.al_name }" /></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input class="form-control" name="al_age" 
				value="${alba.al_age }"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input class="form-control" name="al_address" 
				value="${alba.al_address }"/></td>
			</tr>
			<tr>
				<th>번호</th>
				<td><input class="form-control" name="al_hp" 
				value="${alba.al_hp }"/></td>
			</tr>
			<tr>
				<th>스펙</th>
				<td><input class="form-control" name="al_spec" 
				 value="${alba.al_spec }"/></td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td><input class="form-control" name="al_desc" 
				 value="${alba.al_desc }"/></td>
			</tr>
			<tr>
            <th>학력</th>
            
               <td>
                  <select id="grade" name="gr_code">
                     <c:forEach items="${gradeName}" var="g">
                        <option value="${g.gr_code}" ${alba.gr_code eq g.gr_code ? 'selected' : ''}>${g.gr_name}</option>
                     </c:forEach>
                  </select>
               </td>

         </tr>
			<tr>
				<th>직업</th>
				<td><input class="form-control" name="al_career" 
				 value="${alba.al_career }"/></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<input class="form-control" name="al_gen" 
				 value="${alba.al_gen }"/></td>
			</tr>
			<tr>
				<th>혈액형</th>
				<td><input class="form-control" name="al_btype" 
				 value="${alba.al_btype }"/></td>
			</tr>
			<tr>
				<th>메일</th>
				<td><input class="form-control" name="al_mail" 
				 value="${alba.al_mail }"/></td>
			</tr>
			<tr>
				<th>자격증</th>
				<td>
					<select class="lic" name="lic_code">
						<option value="0">선택하세요</option>
							<c:forEach items="${licenseName}" var="l">
								<option value="${l.lic_code}">${l.lic_name}</option>
							</c:forEach>
					</select>
					<input type="file" name="lic_image" />
					<br>
					<select class="lic" name="lic_code">
						<option value="0">선택하세요</option>
							<c:forEach items="${licenseName}" var="l">
								<option value="${l.lic_code}">${l.lic_name}</option>
							</c:forEach>
					</select>
					<input type="file" name="lic_image" />
					<br>
					<select class="lic" name="lic_code">
						<option value="0">선택하세요</option>
							<c:forEach items="${licenseName}" var="l">
								<option value="${l.lic_code}">${l.lic_name}</option>
							</c:forEach>
					</select>
				<input type="file" name="lic_image" />
			</td>
			</tr>
			
			
			<tr>
				<td colspan="2">
					<c:choose>
						<c:when test="${not empty alba}">
							<c:url value="/alba/albaUpdate.do" var="updateURL">
							</c:url>
							<button type="submit" onclick="location.href='${updateURL}';">수정 </button>
						</c:when>
						<c:otherwise>
							<c:url value="/alba/albaInsert.do" var="insertURL">
							</c:url>
							<button class="btn btn-primary" type="submit" onclick="location.href='${insertURL}';">추가</button>
						</c:otherwise>
					</c:choose>
										
					<input class="btn btn-warning" type="reset" value="취소" />
			</tr>
		</table>
	</form>
</body>
</html>