<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/jdbcDesc.jsp</title>
<style>
 talbe{
 	border : "collapse";dddd
 }
</style>
</head>
<body>
<h4>JDBC (Java DataBase Connectivity)</h4>
<pre>
	: Facade 패턴을 이용하여 데이터베이스와 자바 어플리케이션을 연결하는 방법 : Driver
	1. 벤더가 제공하는 Driver를 classpath에 추가.
	2. driver class 로딩(Classloader 사용)
	3. 로딩된 driver를 통해 Connection 연결.(개방)//커넥션
	4. Query 객체 생성
		1) Statement : Sql injection 취약(동적쿼리..)
		2) PreparedStatement : 정적 쿼리 지향(쿼리 파라미터 활용)
		3) CallableStatement : 함수나 프로시저를 호출
	   //커넥션이 왜 필요했어? db에서 가져오거나 보내려고.
	   sql이라는 언어를 이용해서 명령어를 남겼을때 적절한 방법으로 db까지 가서 해석이 되는지 명령어 파싱
	   = query객체를 만들어 주는 것
	5. Query 실행 (SQL)//명령어
		1) ResultSet exectuetQuery : select
		2) int executeUpdate : insert, update, delete(row count 반환)
	6. 질의 결과 (raw data)사용
	   //db로부터 java로 보내는 data를 잡아야 함 갖고 놈(?)
 ***7. close
	   //더 이상 필요없는 query객체를 마무리(?)
	
</pre>

<%
	//이미 만들어진 코드 소비하고 있을 뿐
	Map<String, Object> dataMap = (Map)request.getAttribute("dataMap");
	List<DataBasePropertyVO> list = (List) dataMap.get("list");
	String[] headers = (String[])dataMap.get("headers");
%>
<table border="1" style="border-collapse : collapse">
	<thead>
		<tr>
			<%
				for(String header : headers){
					%>
					<th><%=header %></th>
					<%
				}
			%>
		</tr>
	</thead>
	<tbody>
	<%
	if(!list.isEmpty()){
		for(DataBasePropertyVO tmp : list){
	%>			
	<tr><td><%= tmp.getProperty_name()%></td>
	<td><%= tmp.getProperty_value()%></td>
	<td><%= tmp.getDescription()%></td>
	</tr>
	<% 
		}
	}else{
	%>
	<tr>
		<td colspan="3">조회된 결과가 없음.</td>
	</tr>
	<% 
	}
	%>
	</tbody>
</table>


</body>
</html>