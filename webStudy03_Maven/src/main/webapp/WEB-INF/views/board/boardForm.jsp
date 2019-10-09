<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	a{
		cursor: pointer;
	}
</style>	
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${cPath }/toastmessage/css/jquery.toastmessage.css" />	
<script type="text/javascript" src="${cPath }/toastmessage/jquery.toastmessage.js"></script>
<script src="https://cdn.ckeditor.com/4.13.0/standard/ckeditor.js"></script>	
</head>
<body>
	<form method="post">
		<table class="table table-bordered">
			<tr>
				<th>글번호</th>
				<td>${board.bo_no } 입력받지 않음.
				<input type="hidden" name="bo_no" value="${board.bo_no }" />
				<span class="error">${errors.bo_no }</span></td>
			</tr>
			<tr>
				<th>게시판종류???</th>
				<td><input type="text" required class="form-control"
					name="board_type" value="B01" />
				<span class="error">${errors.board_type }</span></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input type="text" required class="form-control"
					name="bo_title" value="${board.bo_title }" />
				<span  class="error">${errors.bo_title }</span></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" required class="form-control"
					name="bo_writer" value="${board.bo_writer }" />
				<span class="error">${errors.bo_writer }</span></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" required 
					class="form-control" name="bo_pass" />
				<span  class="error">${errors.bo_pass }</span></td>
			</tr>
			<tr>
				<th>아이피</th>
				<td><input type="text" required readonly class="form-control"
					name="bo_ip" value="${pageContext.request.remoteAddr }" />
				<span class="error">${errors.bo_ip }</span></td>
			</tr>
			<tr>
				<th>원글번호</th>
				<td><input type="number" readonly class="form-control" name="bo_parent"
					value="${param.bo_parent }" />
				<span class="error">${errors.bo_parent }</span></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
				<textarea cols="50" rows="5" name="bo_content">${board.bo_content }</textarea>
				<span class="error">${errors.bo_content }</span></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-info" value="저장" />
					<input type="reset" class="btn btn-warning" value="취소" />
					<input type="button" class="btn btn-info" value="목록으로" 
						onclick="location.href='<c:url value="/board/boardList.do"/>';"
					/>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	 	CKEDITOR.replace( 'bo_content' );
		<c:if test="${not empty message }">
			$(document).toastmessage('showNoticeToast', 'some message here');
		</c:if>
	</script>
</body>
</html>




