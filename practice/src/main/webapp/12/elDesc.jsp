<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>표현언어(Expression Language)</h4>
<pre>
   : 속성데이터를 표현하기 위한 목적의 스크립트 형태 언어 \${속성명}
   <%
      String attr = "속성데이터";
      pageContext.setAttribute("attr", attr);
      request.setAttribute("attr", "요청속성");
   %>
   <%=attr %> ==> ${attr }, ${requestScope.attr }
   
   1. 속성데이터의 표현
   2. 연산자 지원
      1) 산술연산자 (-+/*) : 연산자 중심
         ${1+2 }, ${1+"2" }, ${"1"+"2" }, \${"A" + "1" }
         ${test }, <%=request.getAttribute("test") %>
         ${test + 3 }, ${test1-test2 }
      2) 논리연산자(and, or, not)  
         ${true && true }, ${test and true }, ${test1 or test2 }
         ${not test }, ${not test and true }
      3) 비교연산자 (gt >, lt <, ge >=, le <=, eq == , ne != )
         ${3 lt 4 }, ${test eq 3 }, ${test1 ne test2 }
      4) 단항연산자 (empty) : 있느냐? - null? - length/size 체크
         <%
            pageContext.setAttribute("test", "");
            List<String> list = new ArrayList<>();
            pageContext.setAttribute("list", list);
            list.add("testValue");
         %>
         ${empty test }, ${empty list }
      5) 삼항연산자( 조건식 ? 참 : 거짓 )
         ${not empty test ? "있다" : "없다"}
         ${not empty tes1 and not empty test2 ? test1+test2 : "없다"}
   3.(속성으로 공유된) 집합객체에 대한 접근 방법
      <%
         String[] array =  new String[]{"ele1", "ele2"};
         pageContext.setAttribute("array", array);
         list.add("testValue2");
         Map<String, Object> map = new HashMap<>();
         pageContext.setAttribute("map", map);
         map.put("key-1", "value1");
         map.put("key2", "value2");
         Set<String> set = new HashSet<>();
         pageContext.setAttribute("set", set);
         set.add("value1");
         set.add("value2");
      %>
      ${array[6] }, ${list[6] } ==> list 호출 주의
      ${map.get("key1") }, ${map.key-1 }, ${map["key-1"] } ==> 연산자 주의
      ${set } ==> el에서 하나의 value를 꺼내오는 것은 불가
   4. (속성으로 공유된) 객체에 대한 접근 방법
   <%
      MemberVO member = new MemberVO();
      pageContext.setAttribute("member", member);
      member.setMem_name("테스트");
   %>
      ${member.getMem_name() } ==> 이건 가능하면 쓰지말자 , ${member.mem_name }, ${member["mem_name"]}
   5. EL의 기본객체(11)
      1) 영역(Scope) 객체 Map&lt;String, Object&gt;
         pageScope, rquestScope, sessionScope, applicationScope
         ${pageScope.member } == ${pageScope["member"] }
         ${pageScope.member.mem_name }, ${pageScope["member"]["mem_name"] }
         ${member.mem_name } Scope 명칭 생략 (순차적으로 찾아서 사용)   ${applicationScope.test }, ${test }
      2) 파라미터 객체 : param(Map&lt;String, String&gt;), paramValues(Map&lt;String, String[]&gt;)
         <%=request.getParameter("param1") %>, 
         ${param.param1 }, ${paramValues.param1[1] }
      3) 헤더 객체 : header(Map&lt;String, String&gt;), headerValues(Map&lt;String, String[]&gt;)
      	 ${header.accept }
      	 ${header["user-agent"] }
      	 ${headerValues["user-agent"]}
      4) 쿠키 객체 : cookie(Map&lt;String, Cookie&gt;)
      	 ${cookie.JSESSIONID }, ${cookie["JSESSIONID"]["value"] }, ${cookie.JSESSIONID.name }
      5) 컨텍스트 파라미터 객체 : initParam(Map&lt;String,String&gt;)
      	<%=application.getInitParameter("service") %>
      	${initParam.service}, ${initParam["service"] }
      6) pageContext
      	   <%=request.getContextPath() %>, ${ pageContext.request.contextPath }
      	${pageContext.request.method }

</pre>
</body>
</html>












