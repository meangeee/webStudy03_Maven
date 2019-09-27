<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체(내장객체)</h4>
<pre>
	: JSP 컨테이너에 의해 서블릿 소스가 피싱될때 기본 제공되는 객체(_JSPService 메소드의 지역변수 형태)
	***PageContext pageContext	 : <a href="<%=request.getContextPath()%>">/06/pageContext.jsp 참고</a>
	HttpServletRequest request   : http 프로토콜의 요청에 대한 정보를 캡슐화.
	HttpServletResponse response : http 프로토콜의 응답에 대한 정보를 캡슐화.
	JSPWriter out 				 : 한 jsp 페이지의 출력 버퍼에 대한 정보를 캡슐화. 
	HttpSession session			 : 한 유저가 한 브라우저를 이용한 상황에서 해당 유저에 대한 정보를 캡슐화
		세션 : 유저가 어플리케이션을 사용하고 있는 기간(시간),
			  해당 기간내에 두 피어사이에 의미있게 형성된 통로
			  http stateless 단점을 보완하기 위한 최소한의 상태 정보를 서버상에서 유지.
			  <a href="${pageContext.request.contextPath}/06/sessionDesc.jsp">/06/sessionDesc.jsp 참고</a>
	ServletContext application   : 어플리케이션(컨텍스트)과 서버에대한 정보를 캡슐화.
	SErvletConfig config		 : 서블릿에 대한 메타데이터 객체.
	Object page					 : this. 현재 페이지의 인스턴스
	Trowable exception			 : 발생한 예외나 에러에 대한 정보를 캡슐화.
	ServletContext --> <%=application.hashCode() %>
</pre>
</body>
</html>