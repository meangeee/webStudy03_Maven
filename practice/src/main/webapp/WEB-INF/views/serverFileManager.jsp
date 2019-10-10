<%@page import="kr.or.ddit.enums.CommandType"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.servlet03.FileWrapper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	li.active{
		background-color: blue;
	}
</style>
<h4>서버 파일 매니저</h4>
<%
	List<FileWrapper> leftFiles = (List) request.getAttribute("leftFiles");
	List<FileWrapper> rightFiles = (List) request.getAttribute("rightFiles");
	String leftSrc = request.getParameter("leftSrc");
	String rightTarget = request.getParameter("rightTarget");
	String srcFile = request.getParameter("srcFile");
%>
<form id="serverFileForm" class="managerForm">
	<input type="text" readonly name="leftSrc" class="leftSrc" 
		value="<%=Objects.toString(leftSrc, "") %>"
	/>
	<input type="text" readonly name="rightTarget" class="rightTarget" 
		value="<%=Objects.toString(rightTarget, "") %>"
	/>
</form>

<ul id="leftArea" class="groupUL">
	<%
		for(FileWrapper tmp : leftFiles){
			
			%>
			<li id="<%=tmp.getId() %>" class="<%=tmp.isDirectory()?"dir":"file"%>"><%=tmp.getDisplayName() %></li>
			<%
		}
	%>
</ul>
<form id="commandForm" action="?" method="post"  class="managerForm">
	<p>
	<input type="text" readonly name="leftSrc" class="leftSrc" 
		value="<%=Objects.toString(leftSrc, "") %>"
	/>
	<input type="text" readonly name="rightTarget" class="rightTarget" 
		value="<%=Objects.toString(rightTarget, "") %>"
	/>
	</p>
	<p>
	<input type="text" readonly name="srcFile" id="srcFile"
		required
		value="<%=Objects.toString(srcFile, "") %>"
	/>
	<%
		for(CommandType command : CommandType.values()){
			%>
			<input type="radio" required name="command" value="<%=command.name() %>" />
			<%=command.name() %>
			<%
		}
	%>
	<input type="submit" value="전송" />
	<p>
</form>
<ul id="rightArea" class="groupUL">
	<%
		for(FileWrapper tmp : rightFiles){
			
			%>
			<li id="<%=tmp.getId() %>" class="<%=tmp.isDirectory()?"dir":"file"%>"><%=tmp.getDisplayName() %></li>
			<%
		}
	%>
</ul>
<!-- 스티브사우더스의 웹사이트 최적화 원칙 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/serverManager.js"></script>














