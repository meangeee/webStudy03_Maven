<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/cookieDesc.jsp</title>
</head>
<body>
<h4> Cookie </h4>
<pre>
	: Http 의 statless 단점을 보완하기 위한 최소한의 상태정보가 클라이언트 쪽에 저장되는 개념.
	
	쿠키 사용 단계
	서버사이드
	1. 쿠키 객체 생성
	2. 응답에 실어 전송
	
	클라이언트 사이드
	3. 브라우저별로 쿠키 저장소에 저장
	4. 다음번 요청에 실려서 서버로 재전송
	
	서버사이드
	5. 재전송된 쿠키 확보
	
	** 쿠키 속성의 종류
	1. name(required) : 영숫자, _
	2. value(required): 모든 문자 허용, 단 RFC2396규약에 따라 특수문자는 %인코딩, URL 인코딩 필요.
	3. path(optional) : 기본값은 쿠키글 생성할때 경로 반영.
						path의 하위 경로로 발생하는 요청에 대해서만 재전송됨.
	
	4. domain/host    : 기본값은 쿠키 생성 도메인/호스트 반영
			 .naver.com 같은 형태로 특정 기관의 모든 호스트로 재전송 가능(path 설정과 함께).
			 
	5. maxAge	: 기본값은 session 만료시간과 동일.
			0 	: maxAge를 제외한 나머지 속성들이 모두 동일한 쿠키의 경우, 삭제
			-1	: 브라우저 종료시 해당 쿠키도 삭제
					  
	
	<a href="<%=request.getContextPath()%>/10/viewCookie.jsp">동일 경로에서 쿠키 확인하기</a>
	<a href="<%=request.getContextPath()%>/10/10_inner/viewCookie.jsp">동일 깊이에서 쿠키 확인하기</a>
	<a href="<%=request.getContextPath()%>/09/viewCookie.jsp">다른 경로에서 쿠키 확인하기</a>
	<%
		String value = URLEncoder.encode("한글값", "UTF-8");
		Cookie cookie = new Cookie("testCookie", value);
		response.addCookie(cookie);
		
		Cookie targeting = new Cookie("pathCookie", "specificPathCookie");
		targeting.setPath("/");
		response.addCookie(targeting);
		
		Cookie allDomainCookie = new Cookie("allDomain", "allDomainCookie");
// 		allDomainCookie.setDomain(".chy.com"); 테스트용
		allDomainCookie.setPath("/");
		response.addCookie(allDomainCookie);
		
		Cookie allLiveCookie = new Cookie("allLive", "all~~Live~~");
		allLiveCookie.setPath("/");
		allLiveCookie.setMaxAge(0);
		response.addCookie(allLiveCookie);
	%>
	
</pre>
</body>
</html>






