<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<c:url value='/file/upload.do' />" method='post' enctype='multipart/form-data'>
	<input type="text" name="uploader" />
	<input type="file" name="uploadFile"/>
	<input type="submit" value="업로드"/>
</form>
<c:if test="${not empty saveFileURL }">
	<div style="border:1px solid black;">
		${uploader }
		<img src='<c:url value="${saveFileURL }"/>' />
		<c:remove var="uploader" scope="session"/>
		<c:remove var="saveFileURL" scope="session"/>
	</div>
</c:if>
</body>
</html>