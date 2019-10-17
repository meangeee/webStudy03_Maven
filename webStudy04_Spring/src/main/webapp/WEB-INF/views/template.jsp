<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="20kb" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
<meta charset="UTF-8">


<title>Insert title here</title>
   <tiles:insertAttribute name="preScript"/>
<!--href태그 클라이언트 사이드 방식-->
</head>
<body>
<!-- jsp 서버  iframe 클라이언트-->
   
<div id="header">
   <tiles:insertAttribute name="topMenu"/>
</div>

<div id="leftMenu">
   <tiles:insertAttribute name="leftMenu"/>
</div>
<div id = "contents">
   <tiles:insertAttribute name="contents"/>
</div>
<div id = "footer">
   <tiles:insertAttribute name="footer"/> 
</div>

<script type="text/javascript">
<c:if test="${not empty message }">
$(document).toastmessage('showWarningToast', '${message }');
</c:if>
</script>
</body>
</html>