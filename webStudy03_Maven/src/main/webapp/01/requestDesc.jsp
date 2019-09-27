<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01/requestDesc.jsp</title>
<script type="text/javascript">
   console.log("test");
</script>
</head>
<body>
<h4>HttpServletRequest의 메소드</h4>
<pre>
   CharacterEncoding : <%=request.getCharacterEncoding() %> 
   contentLength : <%=request.getContentLength() %>
   contentType : <%=request.getContentType() %> : body message mime
   contextPath : <%=request.getContextPath() %> **** client side 절대경로에 사용.
   requestURI: <%=request.getRequestURI() %>
   
   Server Info
   localAddr : <%=request.getLocalAddr() %> *** server side IP 주소
   localName : <%=request.getLocalName() %>
   localPort : <%=request.getLocalPort() %>
   
   Client Info
   remoteAddr : <%=request.getRemoteAddr() %>
   remoteHost : <%=request.getRemoteHost() %>
   remotePort : <%=request.getRemotePort() %> *** client side 에서 요청을 보낼때 쓰는 port => 요청시마다 바뀐다. 
   queryString : <%=request.getQueryString() %>
   serverName : <%=request.getServerName() %>
   serverPort : <%=request.getServerPort() %>
   servletContext : <%=request.getServletContext().hashCode() %>
   <%=request.getServletContext().getContextPath() %>
</pre>
   <img src="<%=request.getContextPath() %>/images/Jellyfish.jpg"> 
</body>
</html>