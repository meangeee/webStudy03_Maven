<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/resourceIdentify.jsp</title>
</head>
<body>
<pre>
URI(Uniform Resource Identifier) : 
URL(Uniform Resource Locator) : 
URN(Uniform Resource Name) : 
URC(Uniform Resource Content) :
자원을 네트워크상에서 가지고 놀기 위한 방법들
<%
	String uri = request.getRequestURI();
	String url = request.getRequestURL().toString();
%>
<%=uri %>
<%=url %>
경로 표기방식
1. 상대경로 표기 : 현재 위치를 기준으로 자원을 식별하는 방법.
ex) ../images/Koala.jpg
2. 절대경로 표기 : 경로의 루트부터 전체를 표기하여 식별하는 방법.
ex) http://localhost/webStudy01/images/Koala.jpg
현재 위치(주소의 사용 위치)에서 기억된 정보는 생략가능.
** 클라이언트 사이드 절대 경로 표기 : 도메인 이후의 모든 경로가 표기됨.
** 서버 사이드 절대 경로 표기 : context name 이후의 모든 경로가 표기됨.

</pre>
<img src="../images/Koala.jpg">
<img src="/webSutdy01/images/Koala.jpg">
</body>
</html>