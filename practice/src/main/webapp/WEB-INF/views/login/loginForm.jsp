<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<c:if test="${not empty sessionScope.message }">
	<script type="text/javascript">
		alert("${sessionScope.message }"); //${message}로 해도 되지만 scope코드가 더 속도가 빠름
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
<body>
<form method="post">
	<c:set var="saveId" value="${cookie.idCookie.value }" />
	<ul>
		<li>
			아이디 : <input type="text" name="mem_id" 
						value="${saveId }"/>
		</li>
		<li>
			비밀번호 : <input type="text" name="mem_pass" />
			<input type="checkbox" name="idSave" value="idSave" 
				${not empty saveId?"checked":"" }
			/>아이디기억하기
			<input type="submit" value="로그인" />
		</li>
	</ul>
	

<%-- value="<%=Objects.toString(saveId,"")%>" /> --%>
	
</form>
</body>
</html>