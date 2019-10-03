<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>파일 업로드 처리</h4>
<pre>
   1. client side
      1) form 구성 : 
         method = "post" : body 형성
         enctype ="multipart/form-data" : body가 multi part로 구성됨 
         (각 part를 통해 input 태그 하나의 데이터가 전송됨.)
         part name = input 태그의 name 속성.
         
   2. server side
   	  1) servlet 등록시 multipart-config 설정 추가(Part API 사용, Parameter 사용)
   	  2) 저장위치
   	  3) 저장명
   	  4) 메타 데이터 추출
   	  5) stream copy(upload)
   	  
</pre>
<form action="<c:url value='/file/upload.do'/>" method="post" enctype="multipart/form-data">
   <input type="text" name="uploader"/>
   <input type="file" name="uploadFile"/>
   <input type="submit" value="업로드">
</form>
<c:if test="${not empty saveFileURL }">
   <div style="border:1px solid blcak;">
      ${uploader}
      <img src='<c:url value="${saveFileURL }"/>' />
      <c:remove var="uploader" scope="session"/>
      <c:remove var="saveFileURL" scope="session"/>
   </div>
</c:if>
</body>
</html>