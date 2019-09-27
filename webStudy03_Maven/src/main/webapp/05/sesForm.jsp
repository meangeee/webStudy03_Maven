<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src ="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("[name='sesMember']").on("change", function(){
		//1.타겟팅 2. 3.할일 4.
		//trigger 유발시킨다
			var form = $(this).parents("form");//내 조상들을 가져 옴 근데 그중에 form만
			console.log(form);
			form.submit();
		});
	});
</script>
</head>
<body>
<%
	//ses라는 파라미터를 받아야 함
	//파라미터 검증
	Map<String, String[]> memberMap = new LinkedHashMap<>();
	memberMap.put("a001", new String[]{"바다", "/ses/bada.jsp"});
	memberMap.put("a002", new String[]{"유진", "/ses/yujin.jsp"});
	memberMap.put("a003", new String[]{"슈", "/ses/shu.jsp"});
	
// 	Iterator<String> it = memberMap.keySet().iterator();
		
// 		while(it.hasNext()){
// // 			멤버들 key
// 			String pk = it.next();
// // 			값을 가져오고 싶다!
// 			String name = memberMap.get(pk)[0];
%>
	<form action="<%=request.getContextPath() %>/sesProcess" name="sesForm">
		<select name="sesMember">
		<% 
			for(Entry<String, String[]> tmp : memberMap.entrySet()){
				String pk = tmp.getKey();
				String name = tmp.getValue()[0];
		%>
			<option  value="<%=pk %>"><%= name %></option>
			<%
			}
			%>
		</select>
	</form>
<!-- A. memberMap을 이용해서 동적 option 구성. -->
<!-- B. submit 버튼을 대신하여, option 선택 시 코드로 submit 이벤트를 trigger 시킴 -->
<!-- C. 전송 후 서버사이드에서 sesMember 파라미터에 따라 다음 특성으로 개인 페이지를 서비스 함. -->
<!-- 1. 사용자는 멤버의 개인페이지에 대한 정보를 모름. //위임이동 Dispatch-->
<!-- 2. 사용자는 모든 요청이 sesForm.jsp에 의해 처리되는걸로 착각함. //서버사이드에서 이동한걸 모르게 Dispatch-->
<!-- 3. 각 멤버의 개인 페이지에서는 sesForm으로 전달된 파라미터를 확인 할 수 있도록 함. request Dispatch -->
<!-- 4. 멤버의 개인페이지 결과 화면에서 다른 멤버를 선택 할 수 있도록 -->

<!-- *** -->
<!-- 1. 현재 요청에 포함된 모든 데이터를 삭제하고 도착지로 이동. state -->
<!-- 2. 클라이언트는 최종적으로 멤버의 개인페이지의 위치를 인지 할 수 있음. -->

<!-- 1 2 3번을 바꿔가면서. 차이점 적어보기 -->
</body>
</html>