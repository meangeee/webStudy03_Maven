<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	th,td{
		border: 1px solid black;
	}
	.green{
		color: green;
	}
</style>
</head>
<body>
1. 구구단을 2~9(1~9) table 출력
  단, 첫단과 세번째단, 마지막단 의 컬러를 green;

2. 사용자로부터 특정 사이트의 url 을 입력받음.
   체크박스를 통해 소스로 가져올지 여부 결정.
   ==> 해당 사이트 크로울링.   
<div id="naver">

</div>   
<div id="daum">

</div>
<script type="text/javascript">
	var naverTag = $("#naver");
	var daumTag = $("#daum");
	function crawling(paramObj){
		$.ajax({
			url : "crawler.jsp",
			data : {
				page:paramObj.page
			},
			dataType : "html",
			success : function(resp) {
	// 			var keywords = $(resp).find(".PM_CL_realtimeKeyword_base");//그 소스를 대상으로 dom객체 생성 됨
// 				var keywords = $(resp).find(".hotissue_builtin");
				var keywords = $(resp).find(paramObj.element);
	// 			naverTag.html(keywords);
				paramObj.tag.html(keywords);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
	
		});
	}
	
	setInterval(() => {
		crawling({
			page:"naver",
			element:".PM_CL_realtimeKeyword_base",
			tag:naverTag
		});
		crawling({
			page:"daum",
			element:".hotissue_builtin",
			tag:daumTag
		});
	}, 10000);
</script>
<%-- <c:set var="toSource" value="${param.toSource }"/> --%>
<!-- <form> -->
<%-- 	<input type="url" name="url" value="${param.url }"/> --%>
<!-- 	<input type="checkbox" name="toSource" value="true" -->
<%-- 		${toSource eq 'true'?"checked":"" } --%>
<!-- 	/> -->
<!-- 	<input type="submit" value="가져오기" /> -->
<!-- </form>   -->

<%-- <c:if test="${not empty param.url}"> --%>
<!-- <div id="result"> -->
<%-- 	<c:import url="${param.url }" var="importPage"/> --%>
<%-- 	<c:out value="${importPage }" escapeXml="${toSource eq 'true' }" /> --%>
<!-- </div>  -->
<%-- </c:if> --%>
<table>
	<thead>
		<tr>
			<c:forEach var="dan" begin="2" end="9">
				<td>${dan }단</td>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="mul" begin="1" end="9">
			<tr>
			<c:forEach var="dan" begin="2" end="9" varStatus="vs">
				<c:choose>
				<c:when test="${vs.first or vs.last or vs.count eq 3 }">
					<c:set var="clzName" value="green"/>			
				</c:when>
				
				<c:otherwise>
					<c:set var="clzName" value="normal"/>			
				</c:otherwise>
				</c:choose>
				<td class="${clzName }">${dan }*${mul }=${dan*mul }</td>
			</c:forEach>
			</tr>		
		</c:forEach>
	</tbody>
</table>


<!-- <table> 내가 한,,,. --> 
	<%-- 		<c:forEach var="i" begin="1" end="9" step="1" varStatus="vs"> --%>
	<%-- 			<c:if test="${vs.first }"> --%>
	<%-- 				<c:if test ="${vs.count eq 1 or vs.count eq 3 or vs.count eq last }"> --%>
	<!-- 				<tr> -->
	<%-- 					<c:forEach var="j" begin="2" end="9" step="1"> --%>

	<%-- 						<c:set var="gr" value="green"><td>${j }단</td></c:set> --%>
	<%-- 					</c:forEach> --%>
	<!-- 				</tr> -->
	<%-- 			</c:if> --%>
	<!-- 			<tr> -->
	<%-- 				<c:forEach var="j" begin="2" end="9" step="1"> --%>
	<%-- 					<td>${j }* ${i } = ${i*j }</td> --%>
	<%-- 				</c:if> --%>
	<%-- 				</c:forEach> --%>
	<!-- 			</tr> -->
	<%-- 		</c:forEach> --%>
	<!-- 	</table> -->


</body>
</html>

