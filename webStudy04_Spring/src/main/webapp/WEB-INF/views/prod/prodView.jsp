<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<h4 class="text-center">${prod.prod_name }</h4>
	<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>${prod.prod_id }</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prod_name }</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>${prod.lprod_nm }</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>거래처코드</th>
							<th>거래처명</th>
							<th>담당자</th>
							<th>연락처</th>
							<th>소재지</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${prod.buyer.buyer_id }</td>
							<td>${prod.buyer.buyer_name }</td>
							<td>${prod.buyer.buyer_charger }</td>
							<td>${prod.buyer.buyer_comtel }</td>
							<td>${prod.buyer.buyer_add1 }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prod_cost }</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prod_price }</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prod_sale }</td>
		</tr>
		<tr>
			<th>OUTLINE</th>
			<td>${prod.prod_outline }</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prod_detail }</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><img src="${pageContext.request.contextPath }/prodImages/${prod.prod_img }" /></td>
		</tr>
		<tr>
			<th>상품재고</th>
			<td>${prod.prod_totalstock }</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prod_insdate }</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prod_properstock }</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prod_size }</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prod_color }</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prod_delivery }</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prod_unit }</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prod_qtyin }</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>${prod.prod_qtysale }</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prod_mileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<sec:authorize url="/prod/prodUpdate.do">
					<c:url value="/prod/prodUpdate.do" var="updateURL">
						<c:param name="what" value="${prod.prod_id }" />
					</c:url>
				<button type="button" class="btn btn-info"
					onclick="location.href='${updateURL}';">
					상품수정</button>
			</sec:authorize>
		</td>
		</tr>  
	</table>
	<h4>구매 기록</h4>
	<table>
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>휴대폰</th>
				<th>이메일</th>
				<th>소재지</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty prod.memberList }">
					<c:forEach items="${prod.memberList }" var="member">
						<tr>
							<td>${member.mem_id }</td>
							<td>${member.mem_name }</td>
							<td>${member.mem_hp }</td>
							<td>${member.mem_mail }</td>
							<td>${member.mem_add1 }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">
							구매자가 없음. 안팔려...
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>




