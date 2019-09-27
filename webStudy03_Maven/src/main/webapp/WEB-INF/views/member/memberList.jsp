<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap-4.3.1-dist/css/bootstrap.min.css">
	
<style type="text/css">
	.error{
		color : red;
	}

</style>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
</head>

<body>
<table class="table">
	<thead>
		<tr>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
</table>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">상세 조회</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
        </form>
    </div>
  </div>
</div>


<form  id="listForm" action="${pageContext.request.contextPath}/member/memberView.do">
	<input type="hidden" name="who"/>
</form>


<script type="text/javascript">
	var listBody = $("#listBody");
	var listForm = $("#listForm");
	var exampleModal = $("#exampleModal");
	exampleModal.on("hidden.bs.modal",function(){
		$(this).find(".modal-body").remove("table");
	});
	listForm.on("submit",function(){
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method?method : "get",
			data : queryString,
			dataType : "html",
			success : function(resp) {
				exampleModal.find(".modal-body").html(resp);
				exampleModal.modal("show");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		return false;
	});
	$.ajax({
// 		data : "",
		dataType : "json", // REST 방식
		success : function(resp) {
			let trTags = [];
			$(resp).each(function(index, member) {
				let trTag = $("<tr>").append(
								$("<td>").text(member.mem_id),
								$("<td>").text(member.mem_name),
								$("<td>").text(member.mem_hp),
								$("<td>").text(member.mem_mail),
								$("<td>").text(member.mem_add1),
								$("<td>").text(member.mem_mileage)
							).prop("id", member.mem_id);
				trTags.push(trTag);
			});
			listBody.html(trTags);
		},
		error : function(errorResp) {
			console.log(errorResp.status);
		}

	});
	listBody.on("click", "tr" ,function(){
		//상세정보 받기
		let who = ($(this).prop("id"));
		listForm.find("[name='who']").val(who);
		listForm.submit();
	});
</script>
</body>
</html>
