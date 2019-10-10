<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!--     errorPage="/error/eachError.jsp" -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/errorProcess.jsp</title>
</head>
<body>
<h4>에러 처리 방법</h4>
<%
	if(1==1){
// 		throw new NullPointerException("강제 발생 예외");
		throw new IOException("강제 발생 예외");
	}
%>
<pre>
	1. 지역적 : jsp 페이지 하나를 대상. page 지시자 -> errorPage 속성 - 우선 순위 최상
	2. 전역적 : 웹 어플리케이션을 대상(web.xml -> error-page).
		1) 발생 예외 타입별 처리(exception-type)
		2) 상태 코드별 처리(error-code) - 우선 순위 최하
		
</pre>
</body>
</html>