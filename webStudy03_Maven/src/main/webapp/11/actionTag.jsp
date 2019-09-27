<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>액션 태그</h4>
<pre>
	: 커스텀 태그의 일종으로 JSP 스펙에서 기본 제공되는 커스텀 태그.
	커스텀 태그 사용 방법
	1. taglib 지시자를 이용해서 해당 커스텀 태그 로딩.
	2. 태그 형태로 사용
	&lt;<prefix:tagname attribute/>/&gt;
<%-- 	<jsp:include page="/02/standard.jsp"></jsp:include> --%>
	<%--
		pageContext.include("/02/standard.jsp");
	--%>
<%-- 	<jsp:forward page="/02/standard.jsp"/> --%>
		<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"></jsp:useBean>
		<%--
			MemberVO member = (MemberVO)request.getAttribute("member");
			if(member==null){
				member = new MemberVO();
				request.setAttribute("member", member);
			}
		--%>
		
		<%=member %>
		<%=request.getAttribute("member") %>
</pre>
</body>
</html>