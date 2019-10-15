<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js">
</script>
</head>
<body>
계산기 폼.
<form id="calForm" method="post">
	<input type="text" name="user" />
	<input type="number" name="leftOp" value="${calVO.leftOp }"/>
	+
	<input type="number" name="rightOp" valeu="${calVO.rightOp }"/>
	<input type="submit" value="=" />
	${calVO.result }
</form>

<script type="text/javascript">
	let calForm = $("#calForm");
	calForm.on("submit", function(evnet){
		event.preventDefault();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		$.ajax({
			url: action?action:"",
			method: method?method:"get",
			data: queryString,
			dataType:"json",
			success:function(resp){
				resp.result
			},
			error:function(xhr){
				console.log(xhr.status);
			}
		});
		return false;
	});

</script>
</body>
</html>