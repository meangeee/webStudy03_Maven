<%@page import="kr.or.ddit.utils.MarshallingUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
   	String now = new Date().toString();
	Map<String, Object> target = new HashMap<>();
	target.put("time", now);
%>
<%=new MarshallingUtils().marshalling(target)%>