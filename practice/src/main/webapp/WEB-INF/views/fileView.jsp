<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>

[
<%

List<FileWrapper> leftFiles = (List)request.getAttribute("leftFiles");

	int i = 0;
for( FileWrapper tmp : leftFiles){
	if(i>0) out.print(",");
%>
{ 	"id" : "<%=tmp.getId()%>", 
	"isDirectory" : "<%= tmp.isDirectory()?"dir" : "file"%>",
	"name" : "<%=tmp.getDisplayName() %>" }

<% 
	i++;
}

%>


]
