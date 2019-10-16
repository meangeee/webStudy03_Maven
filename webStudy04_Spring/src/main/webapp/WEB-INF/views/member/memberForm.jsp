<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>신규 가입</title>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
	</script>
</c:if>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
	.error{
		color: red;
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
	<form:form modelAttribute="member" method="post" enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th>회원아이디</th>
				<td><input type="text" class="form-control"
					name="mem_id" id="mem_id" readonly 
					value="${member.mem_id }" />
					<button type="button" id="idCheck">중복확인</button>
					<form:errors path="mem_id" element="span" cssClass="error"/>
					</td>
<%-- 					<span class="error">${errors["mem_id"] }</span></td> --%>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="text"  class="form-control"
					name="mem_pass" value="${member.mem_pass }" />
					<form:errors path="mem_pass" element="span" cssClass="error"/>
					</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" class="form-control"
					name="mem_name" value="${member.mem_name }" />
					<form:errors path="mem_name" element="span" cssClass="error"/>
				</td>
			</tr>
			
			<tr>
				<th>이미지</th>
				<td>
					<input type="file" name="mem_image" />
					<form:errors path="mem_image" element="span" cssClass="error"/>
					</td>
			</tr>
			
			<tr>
				<th>주민번호1</th>
				<td><input type="text" class="form-control" name="mem_regno1"
					value="${member.mem_regno1 }" />
					<form:errors path="mem_regno1" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" class="form-control" name="mem_regno2"
					value="${member.mem_regno2 }" />
					<form:errors path="mem_regno2" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" class="form-control" name="mem_bir"
					value="${member.mem_bir }" />
					<form:errors path="mem_bir" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text"  class="form-control"
					name="mem_zip" value="${member.mem_zip }" />
					<form:errors path="mem_zip" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text"  class="form-control"
					name="mem_add1" value="${member.mem_add1 }" />
					<form:errors path="mem_add1" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" class="form-control"
					name="mem_add2" value="${member.mem_add2 }" />
					<form:errors path="mem_add2" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>집전번</th>
				<td><input type="text" class="form-control" name="mem_hometel"
					value="${member.mem_hometel }" />
					<form:errors path="mem_hometel" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>회사전번</th>
				<td><input type="text" class="form-control" name="mem_comtel"
					value="${member.mem_comtel }" />
					<form:errors path="mem_comtel" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="text" class="form-control" name="mem_hp"
					value="${member.mem_hp }" />
					<form:errors path="mem_hp" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" class="form-control"
					name="mem_mail" value="${member.mem_mail }" />
					<form:errors path="mem_mail" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" class="form-control" name="mem_job"
					value="${member.mem_job }" />
					<form:errors path="mem_job" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" class="form-control" name="mem_like"
					value="${member.mem_like }" />
					<form:errors path="mem_like" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="text" class="form-control" name="mem_memorial"
					value="${member.mem_memorial }" />
					<form:errors path="mem_memorial" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>기념일자</th>
				<td><input type="date" class="form-control"
					name="mem_memorialday" value="${member.mem_memorialday }" />
					<form:errors path="mem_memorialday" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="number" class="form-control"
					name="mem_mileage" value="${member.mem_mileage }" />
					<form:errors path="mem_mileage" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><input type="text" class="form-control" name="mem_delete"
					value="${member.mem_delete }" />
					<form:errors path="mem_delete" element="span" cssClass="error"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="가입" />
					<button type="reset">취소</button></td>
			</tr>
		</table>
	</form:form>
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">아이디 확인</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form id="idCheckForm" method="post" action="${pageContext.request.contextPath }/member/idCheck.do">
       	<input type="text" name="mem_id" id="checkMemId"/>
       	<span class="singid"></span>
       </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" id="useId" class="btn btn-primary">아이디 사용하기</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	var exampleModal = $("#exampleModal");
	var idCheck = $("#idCheck");
	var checkMemId = $("#checkMemId");
	var idCheckForm = $("#idCheckForm");
	var mem_id = $("#mem_id");
	var useId = $("#useId").hide();
	exampleModal.on("hidden.bs.modal", function(){
		checkMemId.val("");
		checkMemId.next(".signid").html("");
	});
	useId.on("click", function(){
		mem_id.val( checkMemId.val() );
		exampleModal.modal("hide");
	});
	idCheckForm.on("submit", function(event){
		event.preventDefault();
		let action = $(this).attr("action");
		let method = $(this).attr("method");
		let queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method?method:"get",
			data : queryString,
			dataType : "json",
			success : function(resp) {
				if(resp.valid){
					useId.show();
				}else{
					checkMemId.next(".singid").html("아이디 중복,");
					checkMemId.focus();
				}				
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}

		});
		return false;
	});
	idCheck.on("click", function(){
		exampleModal.modal("show");
	});
	checkMemId.on("blur", function(){
		// 영문소문자로 시작, 영숫자, 4~12
		let regex = /^([a-z][a-zA-Z0-9]{3,11})$/m;
// 		Look around (look ahead, look behind)
// 		(?!=regex1)(?=regex2)regex3
// 		boolean test, array exec, array match
		let target = $(this).val();
		let match = regex.exec(target);
		if(!match){
			$(this).next(".singid").html("아이디 형식 확인");
			$(this).focus();
		}else{
			exampleModal.find("form").submit();	
		}
	});
</script>
</body>
</html>






