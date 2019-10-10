<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
필요한 구구단의 최소단과 최대단 입력.
<!--  서버로 데이터를 유일하게 보낼 수 있는 것이 form
url표기 방식 /webStudy01/gugudan-->
<form action="<%=request.getContextPath()%>/module/layout.do" method="post">
<input type ="number" name="minDan" />
<input type ="number" name="maxDan" />
<input type="hidden" name="command" value="gugudanProcess"/>
<input type ="submit" value = "전송" />
</form>
</body>
</html>