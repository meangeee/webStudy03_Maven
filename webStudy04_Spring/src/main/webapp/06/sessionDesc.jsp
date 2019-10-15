<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4>HttpSession</h4>
<pre>
세션 : - 시간 : 유저가 어플리케이션을 의미있게 사용하고 있는 기간 (사용시작~종료, WEB)
	 - 통로 : 한 세션내에서 두 피어사이에 설정된 유일한 통로(데이터베이스)
세션의 생명주기(lifecycle)
시작 : 최초의 요청이 서버에 전달될때.
	 세션 properties : 생성시점, 식별자(세션ID), 마지막 요청 시점,
	생성 시점 : <%=new Date(session.getCreationTime()) %>
	세션 아이디 : <%=session.getId() %> 쿠키와 같다 클라이언트와 서버가 같은 데이터를 갖고 있다. -> 대화를 하기 위한 장치
	마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
	만료시간 : <%=session.getMaxInactiveInterval() %>s
	<%
		session.setMaxInactiveInterval(2*60);
	%>
	재설정 후 : <%= session.getMaxInactiveInterval()%>s
	<a href="sessionDesc.;jsessiongid=<%=session.getId()%>">세션 유지를 위한 링크</a>
	ServletContext application : 어플리케이션(컨텍스트)와 서버에 대한 정보를 캡슐화.
	<a href="<%=request.getContextPath() %>/07/applicationDesc.jsp">/07/applicationDesc.jsp 참고</a>
	한세션내에서 발생하는 요청을 식별하는 방법
	session tracking mode
	1) Cookie : JSESSIONID 같은 세션을 식별자를 포함하는 쿠키를 전송하고 저장하고 재전송하는 방법으로 식별.
	2) URL : jsessionid 세션 파라미터를 전송하여 세션을 식별(보안 취약성, URL rewriting 문젱)
	3) SSL(Secure Sockey Layer) : 두 피어사이의 데이터가 암호화되어 전달되며, 암호문내에서 세션 식별자.
								: 인증서가 필요함 (openSSL)

종료 : 

	 1) 세션 만료 시간(ex 30분) 이내에 새로운 요청이 발생하지 않을 때.
	 2) 브라우저 종료 : 만료 시간이 지난 후 만료
	 3) 특정 쿠기 삭제시 : 만료 시간이 지난 후 만료
	 4) 명시적인 로그아웃(invalidate) : 서버사이드 코드
	 
	<%
		session.invalidate(); //서버에서 세션 아이디 지우기
	%>
	 
</pre>
</body>
</html>