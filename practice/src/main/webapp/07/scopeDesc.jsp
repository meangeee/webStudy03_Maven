<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc.jsp</title>
</head>
<body>
 <h4>Scope (영역)</h4>
 <pre>
 	: 웹 앱내에서 데이터(attribute)를 공유하기 위한 저장 공간
 	각 기본 객체가 가진 Map&lt;String, Object&gt;.
 	모든 scope는 해당 영역에 대한 제어를 담당하는 기본 객체와 동일한 생명주기를 가짐.
 	저장 공간을 선택시, 최소한의 저장 scope를 선택함.
 	
 	1. page Scope : Pagecontext
 	2. request Scope : HttpServletResquest
 	3. session Scope : HttpSession
 	4. application Scope : ServletContext
 	//넣다 뺏다를 해보자
 	<%
 		pageContext.setAttribute("pageAttr", "페이지 속성");
 		request.setAttribute("requestAttr", "요청 속성");
 		session.setAttribute("sessionAttr", "세션 속성");
 		application.setAttribute("applicationAttr", "어플리케이션 속성");
//  		pageContext.forward("/02/standard.jsp");
		//위에와 같은 코드임 Dispatch일때
// 		request.getRequestDispatcher("/02/standard.jsp")
// 			   .forward(request, response);
		
// 		request.getRequestDispatcher("/02/standard.jsp")
// 			   .include(request, response);
		
		//redirect방법 나가서 새로운 요청이 들어ㅏ오ㅑ야하기때문에 client ~
// 		응답이 다시 나갈때 request가 없어짐
		response.sendRedirect(request.getContextPath() + "/02/standard.jsp");
 	%>
 	
 </pre>

</body>
</html>