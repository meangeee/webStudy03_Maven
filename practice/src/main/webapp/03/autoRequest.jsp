<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta http-equiv="Refresh" content="10;url=https://www.naver.com">  --%>
<title>03/autoRequest.jsp</title>
</head>
<body>
<h4>자동 요청을 발생시키는 방법</h4>
<span id="secArea">10</span>초뒤에 네이버로 이동함.
<pre>
   1. 서버 사이드 방식 : response header 중 Refresh 사용
      <%
//          response.setIntHeader("Refresh", 1); // 요청하는 초단위를 입력 : 1
         Date now = new Date();
      %>
      <%=now %>
   2. 클라이언트 사이드 방식
      1) HTML : meta 태그이용.
      2) Javascript : setInterval/setTimeout, location.reload()
</pre>
<script type="text/javascript">
// 	var span = document.querySelector("#secArea");
// 	var seconds = 3;
// 	var jobId = setInterval(()=>{
// 		span.innerHTML = seconds--;
// 		if(seconds==0) clearInterval(jobId);
// 	}, 1000);

	var test = 23;
	setTimeout(function(){
		let test2 = 89;//let은 명확하게 지역변수로 선언하겠다는 의미
// 		location.reload();
// 		window.history.back();
		window.history.go(1);
	}, 1000);
</script>
</body>
</html>