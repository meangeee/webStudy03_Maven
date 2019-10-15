<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
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
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<select name="searchType">
						<option value>전체</option>
						<option value="id">아이디</option>
						<option value="name">이름</option>
						<option value="address">지역</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색" />
				</form>
				<div id="pagingArea">
				
				</div>
			</td>
		</tr>
	</tfoot>
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
      <div class="modal-body">
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<form id="listForm" action="${pageContext.request.contextPath }/member/memberView.do">
	<input type="hidden" name="who" />
</form>
<script type="text/javascript">
	var listBody = $("#listBody");
	var pagingArea = $("#pagingArea");
	var listForm = $("#listForm");
	var exampleModal = $("#exampleModal");
	var searchForm = $("#searchForm");
	var pageTag = $("[name='page']");
	
	searchForm.on("submit", function(event){
		event.preventDefault();
		var action = $(this).attr("action");
		var method = $(this).attr("method");
		var queryString = $(this).serialize();
		$.ajax({
			url : action,
			method :method,
			data : queryString,
			dataType : "json", // REST 방식
			success : function(resp) {
				let memberList = resp.dataList;
				let trTags = [];
				$(memberList).each(function(index, member) {
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
				pagingArea.html( resp.pagingHTML );
				pageTag.val("1");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
	
		});
		return false;
	});
	
	pagingArea.on("click", "a", function(){
		let page = $(this).data("page");
		paging(page);
	});
	
	exampleModal.on("hidden.bs.modal", function(){
		$(this).find(".modal-body").remove("table");
	});
	
	listForm.on("submit", function(){
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method?method:"get",
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
	
	function paging(page){
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();
	}
	
	listBody.on("click", "tr" ,function(){
		let who = $(this).prop("id");
		listForm.find("[name='who']").val(who);
		listForm.submit();
	});
	
	paging(1);
</script>
</body>
</html>


<!-- 검색 기능 추가 : 아이디, 성명, 지역, 전체 -->




