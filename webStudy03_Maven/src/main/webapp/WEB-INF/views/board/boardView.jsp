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
</head>
<body>
	<h4>${board.board_name }</h4>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<td>${board.bo_no }</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td>${board.bo_title }</td>
		</tr>
		<tr>
			<th>작성자(${board.bo_pass })</th>
			<td>${board.bo_writer }</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.bo_ip }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.bo_date }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.bo_hit }</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${board.bo_like }</td>
		</tr>
		<tr>
			<th>원글번호</th>
			<td>${board.bo_parent }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.bo_content }</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-info" value="수정" />
				<input type="button" class="btn btn-info" value="삭제" />
				<c:url value="/board/boardInsert.do" var="insertURL">
					<c:param name="bo_parent" value="${board.bo_no }" />
				</c:url>
				<input type="button" class="btn btn-info"
					value="답글쓰기" 
					onclick="location.href='${insertURL }';"	
				/>
				<input type="button" class="btn btn-info" 
						value="뒤로가기"  onclick="history.back();"/>
				<input type="button" class="btn btn-info" 
						value="목록으로"  onclick="location.href='<c:url value="/board/boardList.do"/>';"/>
			</td>
		</tr>
		
		<tfoot>
			<tr>
				<td>
					<form id="replyForm">
						<input name="bo_no" type="text" />
					</form>
				</td>
			</tr>
		</tfoot>
	</table>
	<div id="pagingArea">
			${pagingVO.pagingHTML }
	</div>
</body>

<script type="text/javascript">
	var queryString = $("#replyForm").serialize();
	
		$.ajax({
			url : "${pageContext.request.contextPath }/board/replyList.do",
			data : queryString,
			dataType : 'json',
			success : function(resp) {
				let repList = resp.repList;
				let trTags = [];
				$(repList).each(
						function(index, reply) {
							let trTag = $("<tr>").append(
									$("<td>").text(resp.rep_no),
									$("<td>").text(resp.rep_ip),
									$("<td>").text(resp.rep_content),
									$("<td>").text(resp.rep_ip),
									$("<td>").text(resp.rep_ip));
							trTags.push(trTag);
						});
				foot.html(trTags);

			},
			error : function(xhr) {
				console.log("상태 : " + xhr.status);
			}

	})
</script>
</html>



