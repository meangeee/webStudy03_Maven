<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet"
	href="${cPath }/postmessage/css/jquery.toastmessage.css" />
<script src="${cPath }/postmessage/jquery.toastmessage.js"></script>
<script src="https://cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>

</head>
<body>
	<form method="post">
			<input type="hidden" name="bo_parent" value="${param.bo_param }" />

	<textarea rows="" cols="">
	</textarea>
	</form>
	<script>
         CKEDITOR.replace( 'bo_editor1' ); 
    </script>
	<script type="text/javascript">
		
		<c:if test="${not empty message }">
			$().toastmessage('showNoticeToast', 'some message here');
		</c:if>
	</script>
</body>
</html>