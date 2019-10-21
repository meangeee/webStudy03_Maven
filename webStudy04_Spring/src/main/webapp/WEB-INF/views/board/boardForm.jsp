<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<script src="${cPath }/ckeditor/ckeditor.js"></script>	
<form id="boardForm" method="post" enctype="multipart/form-data">
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
				name="board_type" value="${board_type }" />
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
		<c:if test="${not empty board.attatchList }">
			<tr>
				<th>기존 첨부 파일</th>
				<td>
					<c:forEach items="${board.attatchList }" 
							var="attatch" varStatus="vs">
						<span>	
						${attatch.att_filename }
						<a class="attDelBtn" data-attno="${attatch.att_no }"><i class="fas fa-minus-square"></i></a>
						${not vs.last?",":"" }
						</span>
					</c:forEach>
				</td>
			</tr>
		</c:if>
		<tr>
			<th>첨부파일</th>
			<td>
				<div class="form-inline">
				<input type="file" name="bo_file" class="form-control mr-2" />
				<a class="plusBtn"><span style="font-size: 2em;"><i class="fas fa-plus-square"></i></span></a>
				</div>
			</td>
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
 	CKEDITOR.replace( 'bo_content', {
 		filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?command=QuickUpload&type=Images"
 	});
 	let boardForm = $("#boardForm");
	$(".attDelBtn").on("click", function(){
		let att_no = $(this).data("attno");
		boardForm.append(
			$("<input>")
				.attr({
					type:"text"
					, name:"delAttaches"
				}).val(att_no)
		);			
		$(this).parent("span").remove();
	});
	$(".plusBtn").on("click", function(event){
		let tdTag = $(this).closest("td");
		tdTag.append(
			$("<div>").html(
				$("<input>")
					.attr({
						type:"file"
						, name:"bo_file"
					}).addClass("form-control")
			).addClass("form-inline")	
		);
	});
</script>