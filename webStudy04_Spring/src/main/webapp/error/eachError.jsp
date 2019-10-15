<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>에러를 처리할 목적의 페이지</h4>
<pre>
	<%
		ErrorData ed = pageContext.getErrorData();
	%>
	<%=exception.getMessage() %>
	에러 발생 위치 : <%=ed.getRequestURI() %>
	에러 상태 코드 : <%=ed.getStatusCode() %>
	에러 : <%= ed.getThrowable() %>
	오늘 203호에서 최종 프로젝트 발표를했는데,
	한 시간 내내 서서 보다가 허리 뽀사지는 줄...
	근데 가만히 보니까 별로 어려워보이지도 않고,
	내가 하면 그보다 더 잘 할 수 있을 것 같은 생각도 들고...
	그랬으면 하는 게 나의 바람인데, 과연 그럴라나...
</pre>
</body>
</html>