<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a02_pageContext.jsp</title>
</head>
<body>
<h4>PageContext 기본객체</h4>
<pre>
   : 하나의 JSP 페이지에 대한 모든 정보를 가진 객체.
   
   1. 나머지 8개의 기본객체를 확보할 때 사용한다.
      <%=request == pageContext.getRequest() %>
      <%=response == pageContext.getResponse() %>
      JSP표현식 : <%=request.getContextPath()%> 
      el표현식 : ${pageContext.request.contextPath }
      
   2. Scope 를 접근할때
      - page, request, session, application Scope 로 구분
      스코프에 데이터 넣기 :
         request.setAttribute("attr", "value");
         pageContext.setAttribute("attr", "value", pageContext.REQUEST_SCOPE);
      <%
//       request.setAttribute("attr", "value");
      	//위에있는코드와똑같은코드
         pageContext.setAttribute("attr", "value", pageContext.REQUEST_SCOPE);
      %>
      스코프에서 데이터 가져오기 : 
      <%=request.getAttribute("attr") %>
      <%=pageContext.getAttribute("attr", pageContext.REQUEST_SCOPE) %> 
   
   3. 흐름제어(flow control) : request dispatch 방식 (forward, include)
   
   		<%
   			String path = "/02/standard.jsp";
   			//이게 원래 정석
//    		RequestDispatcher rd = request.getRequestDispatcher(path);
//    		rd.forward(request, response);
//    		pageContext.forward(path);
// 			rd.include(request, response);
// 			pageContext.include(path);
   		%>
   4. 에러데이터 확보
</pre>
</body>
</html>