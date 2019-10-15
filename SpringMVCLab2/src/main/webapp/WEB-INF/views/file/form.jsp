<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
	uploader : <input type="text" name="uploader" />
	file : <input type="file" name="uploadFile" />
	<input type="submit" value="업로드" />
</form>
<spring:eval expression="@appInfo.imagesClientUrl" var="images"></spring:eval>
<img src="${pageContext.request.contextPath }${images}${fileVO.savename }" />
</body>
</html>