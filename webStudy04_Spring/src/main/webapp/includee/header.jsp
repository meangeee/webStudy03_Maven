<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<form name="logoutForm"
		action="${pageContext.request.contextPath}/logout" method="post">

</form>
<ul>
	<li>
		<a href="${cPath }/member/memberList.do">회원관리</a>
		<a href="${cPath }/prod">상품관리</a>
		<a>거래처관리</a>
		<a>알바생관리</a>
		<a href="${cPath }/board/B01/boardList.do">자유게시판</a>
		<a href="${cPath }/board/B02/boardList.do">공지게시판</a>
		<a>방명록</a>
	</li>
	<li>
		<sec:authorize access="isAuthenticated()">
         <sec:authentication property="principal" var="authMember"/>
         <a href="${cPath}/mypage">${authMember.mem_name}님 ,${authMember.mem_role}</a><a href="#" onclick="document.logoutForm.submit();">로그아웃</a> 
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
         <a href="${cPath}/login">로그인</a>
         <a href="${cPath}/member/memberInsert.do">가입하기</a>
      </sec:authorize>
	</li>
</ul>
상단 메뉴 바 헤더 로고 등
<script>
	console.log($);
</script>