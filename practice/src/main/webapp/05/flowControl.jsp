<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4> 웹 어플리케이션에서의 흐름 제어(Flow Control)</h4>
<pre>
	Http의 특성 : Stateless
	connectless 통로를 쓰고 끊어버림 -> 상대의 정보를 기억하지 않아도 됨.
	1. Request Dispatch
	 : 서버사이드 내에서만 이동하는 위임 처리 방식으로 원본 요청을 가지고 분기하는 방식.
		1) forward : 분기한 이후 도착지에서만 응답 전송(완전한 위임. B)
		2) include : 분기한 도착지의 결과 데이터를 가지고 복귀. (A+B)
		
	2. Redirect
	 : 최초의 요청에 대한 응답이 이동 전에 먼저 발생하고,
	   해당 응답에는 Body가 없는 대신, Line(302/307)과 Header(Location)가 응답으로 전송.
	   최종적으로 클라이언트쪽에서 Location 방향으로 새로운 요청을 발생시켜서 이동.
	  <%
// 	  	1. Request dispatch , forward
// 		상대경로를 쓰면 안됨 서버사이드에서?
		String path = "/02/standard.jsp";
// 		RequestDispatcher rd = request.getRequestDispatcher(path);
// 		rd.forward(request, response);
// 		2. Request dispatch, include
// 		rd.include(request, response);
// 		3. Redirect
		String location = request.getContextPath() + path;
		response.sendRedirect(location);
	  %>
	   
</pre>
</body>
</html>