<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>100Conn100Process</title>
</head>
<body>
<%   
   long startTime = System.currentTimeMillis(); 
   for(int i=1; i<=100 ; i++){
      // a001 유저의 기본 정보 조회
      StringBuffer sql = new StringBuffer();
      sql.append("select mem_id, mem_name, mem_hp ");
      sql.append("from member                     ");
      sql.append("where mem_id = ? ");//쿼리 파라미터
      MemberVO member = null;
      try(
         Connection conn = ConnectionFactory.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql.toString());
      ){
         pstmt.setString(1, "a001");
         ResultSet rs = pstmt.executeQuery();
         if(rs.next()){
            member = new MemberVO();
            member.setMem_id(rs.getString("mem_id"));
            member.setMem_name(rs.getString("mem_name"));
            member.setMem_hp(rs.getString("mem_hp"));
         }
      }
%>
<%=member %>
<%
   }
%>
<%=System.currentTimeMillis() - startTime %>ms
</body>
</html>