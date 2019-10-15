<%@page import="java.util.Objects"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
li {
	margin-top: 10px;
	margin-right: 10px;
	padding: 5px 0px 5px 5px;
}

li.dir {
	cursor: pointer;
}

ul {
	border: 2px solid black;
	width: 20%;
	height: 500px;
	overflow: auto;
}

ul#srcArea {
	float: left;
}

form#commandForm {
	float: left;
	width: 50%;
	height: 500px;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
}

ul#targetArea {
	float: right;
}

.active {
	border: 2px solid blue;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<%
		String src = Objects.toString(request.getParameter("src"), "");
		String target = Objects.toString(request.getParameter("target"), "");
		String srcFile = Objects.toString(request.getParameter("srcFile"), "");
	%>
	<form id="explorerForm">
		좌패널 위치 : <input type="text" name="src" id="src" value="<%=src%>"
			readonly /> 우패널 위치 : <input type="text" name="target" id="target"
			value="<%=target%>" readonly />
	</form>
	<%!private String getId(File file) {
		String contextRealPath = getServletContext().getRealPath("");
		String abPath = file.getAbsolutePath();
		return abPath.length() > contextRealPath.length()
				? abPath.substring(contextRealPath.length()).replace(File.separatorChar, '/')
				: "";
	}%>
	<ul id="srcArea">
		<%
			String cp = getServletContext().getContextPath();
			File[] leftFiles = (File[]) request.getAttribute("leftFiles");
			for (File leftFile : leftFiles) {
				String displayName = leftFile.getName();
				String id = getId(leftFile);
				displayName = src.length() > id.length() ? ".." : displayName;
				String active = id.equals(srcFile) ? " active" : "";
		%>
		<li class="<%=leftFile.isDirectory() ? "dir" : "file"%><%=active%>"
			id="<%=id%>"><%=displayName%></li>
		<%
			}
		%>
	</ul>
	<form action="?" id="commandForm" method="post">
		<p>
			좌패널 위치 : <input type="text" name="src" id="src" value="<%=src%>"
				readonly /> 우패널 위치 : <input type="text" name="target" id="target"
				value="<%=target%>" readonly />
		</p>
		<p>
			선택한 파일 : <input type="text" name="srcFile" id="srcFile"
				value="<%=srcFile%>" required readonly />
		</p>
		<p>
			<label><input type="radio" name="command" value="COPY"
				required />복사</label> <label><input type="radio" name="command"
				value="MOVE" required />이동</label> <label><input type="radio"
				name="command" value="DELETE" required />삭제</label> <input type="submit"
				value="명령 수행" />
		</p>
	</form>
	<ul id="targetArea">
		<%
			File[] rightFiles = (File[]) request.getAttribute("rightFiles");
			for (File rightFile : rightFiles) {
				String displayName = rightFile.getName();
				String id = getId(rightFile);
				displayName = target.length() > id.length() ? ".." : displayName;
		%>
		<li class="<%=rightFile.isDirectory() ? "dir" : "file"%>" id="<%=id%>"><%=displayName%></li>
		<%
			}
		%>
	</ul>
	<script type="text/javascript">
		var explorerForm = $("#explorerForm");
		var commandForm = $("#commandForm");
		var src = $("#src");
		var target = $("#target");
		var srcFile = $("#srcFile");
		$("ul#srcArea>li.file").on("click", function() {
			var file = $(this).prop("id");
			$(this).siblings("li").removeClass("active");
			$(this).toggleClass("active");
			if ($(this).hasClass("active")) {
				srcFile.val(file);
			} else {
				srcFile.val("");
			}
		});
		$("ul").on("dblclick", "li", function() {
			var thisId = $(this).prop("id");
			var parentId = $(this).parent().prop("id");
			if (parentId == "srcArea") {
				src.val(thisId);
			} else if (parentId == "targetArea") {
				target.val(thisId);
			}
			explorerForm.submit();
		});
	</script>
</body>
</html>



















