<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
<!-- source : /images/이미지 리스트 -->
<!-- target : /07 -->
<%
	String[] imageFiles = (String[]) request.getAttribute("imageFiles");
	String[] targetFiles = (String[]) request.getAttribute("targetFiles");
%>
<%=request.getContextPath() %>/imagesFolderProcess
<form  method="post">
<select name="filename" required>
	<option value>이미지 선택</option>
	<%
		for(String file : imageFiles){
			%>
			<option><%=file %></option>
			<%
		}
	%>
</select>
<input type="radio" value="COPY" name="command"  required/>복사
<input type="radio" value="MOVE" name="command"  required/>이동
<input type="radio" value="DELETE" name="command"  required/>삭제
<input type="submit" value="명령을 처리햇!" />
</form>
<ul>
	<%
		for(String file : targetFiles){
			%>
			<li><%=file %></li>
			<%
		}
	%>
</ul>
</body>
</html>



