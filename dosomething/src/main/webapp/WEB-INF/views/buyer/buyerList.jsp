<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
	
	<form id="searchForm"> <!-- 입력 받기 위한 div -->
		<input type="hidden" name="page" />
		<select name ="searchType">
			<option value>전체</option>
			<option value="id">아이디</option>
			<option value="name">이름</option>
			<option value="addr">지역</option>
		</select>
		<input type="text" name="searchWord"/>
		<input type="submit" value="검색"/>
	</form>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>지역</th>
			</tr>
		</thead>

		<tbody id="body">
		</tbody>
		<tfoot>
			<tr>
				<td>
					<div id="detail">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div id="pagingArea">
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<button id="insert" type="button" class="btn btn-primary"
		data-toggle="modal" data-target="#myModal">생성</button>
	<button id="modify" type="button">수정</button>
	<button id="deleteBtn" type="button">삭제</button>

	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Modal Heading</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form id="buyer_form" method="post">
					<!-- Modal body -->
					<div class="modal-body">
						BUYER_ID : <input id="bid" name="buyer_id" type="text" /><br>
						BUYER_NAME : <input id="bname" name="buyer_name" type="text" /><br>
						BUYER_LGU : <input id="blgu" name="buyer_lgu" type="text" /><br>
						BUYER_BANKNAME : <input id="bbank" name="buyer_bankname" type="text" /><br> 
						BUYER_ZIP : <input id="bzip" name="buyer_zip" type="text" /><br> 
						BUYER_ADD1 : <input id="badd" name="buyer_add1" type="text" /><br>
						BUYER_MAIL : <input id="bmail" name="buyer_mail" type="text" /><br>
						BUYER_IMG : <input type="file" name="buyer_image" accept="image/*" />
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button id="confirm" type="button" class="btn btn-danger"
							data-dismiss="modal">submit</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					</div>
				</form>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	
		var body = $('#body');
		var pagingArea = $("#pagingArea");
		var pageTag = $("[name='page']");
		var searchForm = $("#searchForm");
		
		searchForm.on("submit", function(event){
			event.preventDefault();
			var action = $(this).attr("action");
			var method = $(this).attr("method");
			var queryString = $(this).serialize();
			
			$.ajax({
				data : queryString,
				dataType : "json",
				success : function(resp) {
					let buyerList = resp.dataList; //VO에있는거
					let trTags = [];
					$(buyerList).each(function(index, buyer){
						let trTag = $("<tr>").append(
							$("<td>").text(buyer.buyer_id),		
							$("<td>").text(buyer.buyer_name),		
							$("<td>").text(buyer.buyer_add1)		
						).prop("id", buyer.buyer_id);
						trTags.push(trTag);
					});
					body.html(trTags);
					pagingArea.html(resp.pagingHTML);
					pageTag.val("1");
				},
				error : function(errorResp) {
					console.log(errorResp.status);
				}
			});
			return false;
		});
		
		//list뽑기
		$.ajax({
			dataType : "json",
			success : function(resp) {
				
				code = "";
				$(resp).each(function(i, v) {
					//tbody에 1행1열씩 반복되며 값을 넣어줘야함.
// 					alert(v.buyer_name);
					code += "<tr>";
					code += "<td id='"+ v.buyer_id +"'>"+ v.buyer_id + "</td>";
					code += "<td id='"+ v.buyer_id +"'>"+ v.buyer_name + "</td>";
					code += "<td id='"+ v.buyer_id +"'>"+ v.buyer_add1 + "</td>";
					code += "</tr>";
				});
				body.html(code);
				pagingArea.html(resp.pagingHTML);
				pageTag.val("1");
			},
			error : function(errorResp) {
				console.log(errorResp.stauts);
			}
			
		});
		
		//생성
		var confirm = $('#confirm');
		confirm.on('click', function(){
			data = $('#buyer_form').serializeArray();
			console.log(data);
			$.ajax({
				url : "${pageContext.request.contextPath }/buyer/buyerInsert.do",
				method : "post",
				data : data,
				dataType: 'text',
				contentType : false,
	            processData : false,
	            enctype: 'multipart/form-data',
				success : function(resp){
					alert ("글쓰기 성공");
					$('#mymodal').modal('hide');
				},
				errors : function(errorResp){
					console.log(errorResp.stauts);
				}
			})		
		})
		

		
// 		modify.on('click',function(){
// 			modiData = $('#modiTable').serializeArray();
// 			console.log(modiData);

// 		)};
		
		
// 	  nameList.on("click","p",function(){
//       var id = $(this).prop("id");
//      	 $.ajax({
<%--          url : "<%=request.getContextPath()%>/buyer/buyerDetail.do", --%>
//          data : {"buyer_id" : id},
//          dataType : "json",
//          success : function(resp){
//             code="";
//             code+="<tr><td>BUYER_ID</td>";
//             code+="<td><input name='buyer_id' value='"+resp.buyer_id+"' ></td><tr>";
            
//             code+="<tr><td>BUYER_NAME</td>";
//             code+="<td><input nam
            

		body.on('click','tr',function(){
			var id = $(this).prop("id");
			$.ajax({
				url : "${pageContext.request.contextPath }/buyer/detail",
				data : {"id" : id},
				dataType : "json",
				success : function(resp){
					code = "<form id='modiForm'>";
					code +="<table>";
					code += "<tr><td>BUYER_ID</td>";
					code += "<td><input id='bId' name='buyer_id' readonly type='text' value='"+resp.buyer_id+"'></td></tr>";
					
					code += "<tr><td>BUYER_NAME</td>";
					code += "<td><input name='buyer_name' type='text' value='"+resp.buyer_name+"' ></td></tr>";
					
					code += "<tr><td>BUYER_LGU</td>";
					code += "<td><input name='buyer_lgu' type='text' value='"+resp.buyer_lgu+"' ></td></tr>";
					
					code += "<tr><td>BUYER_bankname</td>";
					code += "<td><input name='buyer_bankname' type='text' value='"+resp.buyer_bankname+"' ></td></tr>";
					
					code += "<tr><td>BUYER_ZIP</td>";
					code += "<td><input name='buyer_zip' type='text' value='"+resp.buyer_zip+"' ></td></tr>";
					
					code += "<tr><td>BUYER_ADD1</td>";
					code += "<td><input name='buyer_add1' type='text' value='"+resp.buyer_add1+"' ></td></tr>";
	
					code += "<tr><td>BUYER_MAIL</td>";
					code += "<td><input name='buyer_mail' type='text' value='"+resp.buyer_mail+"' ></td></tr>";
					
					code += "<tr><td>BUYER_IMG</td>";
					code += "<td><img src=${pageContext.request.contextPath}/buyerImages/${buyer.buyer_img}' /></td></tr>";
					
					code += "</table>"
					code += "</form>"
					$('#detail').html(code);
				},
				error : function(errorResp){
					console.log(errorResp.stauts);
				}
		
			})
		})
		
		//수정
		var modify = $('#modify');
		modify.on('click', function(){
			modiData = $('#modiForm').serializeArray();
			console.log(modiData);
			$.ajax({
				url:"${pageContext.request.contextPath}/buyer/buyerUpdate.do",
				data : modiData,
				type : 'post',
				dataType : 'text',
				success : function(resp){
					alert("수정 완료");
				},
				error : function(errorResp){
					console.log(errorResp.status);
				}
				
			})
		})
		
		//삭제
		var deleteBtn = $('#deleteBtn');
		deleteBtn.on('click', function(){
			bId = $('#bId').val();
			alert(bId);
			$.ajax({
				url:"${pageContext.request.contextPath}/buyer/buyerDelete.do",
				data : { "id" : bId},
				dataType : 'text',
				success : function(resp){
					alert("삭제완료");
				},
				error : function(errorResp){
					console.log(errorResp.status);					
				}
			})	
		})
		
		function paging(page){
			if(page<1) return false;
			pageTag.val(page);
			searchForm.submit();
		}
		
		pagingArea.on("click", "a", function(){
			let page = $(this).data("page");
			paging(page);
		});
		
		paging(1);
	</script>

</body>
</html>