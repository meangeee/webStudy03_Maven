<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/ajaxDesc</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<pre>
   클라이언트 현재시간 : <span id="client"></span> 
   서버의 현재시간: <span id="server"></span>
</pre>
<script type="text/javascript">
   var client = document.querySelector("#client");
   var server = document.querySelector("#server");
   setInterval(()=> {
      var now = new Date();
      client.innerHTML = now;
   $.ajax({
      url:"<%=request.getContextPath()%>/08/getServerTime.jsp",
//       method:"get",
      data:"param=value&param2=value2",
      dataType:"json", //Accept:application/json(요청) == Content-Type:application/json(응답) 라는 요청헤더를 결정
      success:function(resp){
         server.innerHTML=resp.time;
      },
      error:function(errorResp){
         console.log(errorResp.status);
      }
   }, 1000);
   
      
   });
   
</script>
</body>
</html>