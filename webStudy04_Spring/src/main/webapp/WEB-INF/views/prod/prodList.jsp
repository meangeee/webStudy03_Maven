<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/css/bootstrap.min.css">
<style type="text/css">
.error {
   color: red;
}
</style>
<script type="text/javascript"
   src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath }/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath }/js/generateLprodAndBuyer.js"></script>
</head>
<body>
   <input style="width: 50px; height: 50px" type="image" src="<c:url value="/images/k.png" />"
   		onclick="location.href='?lang=ko';"
   />
   <input style="width: 50px; height: 50px" type="image" src="<c:url value="/images/a.png" />"
   		onclick="location.href='?lang=en';"
   />
   <form id="searchForm"> <!-- 숨어있는 진짜 폼 : 전송목적 -->
      <input type="hidden" name="page" /> 
      <input type="hidden" name="prod_lgu" value="${pagingVO.searchVO.prod_lgu }"/> 
      <input type="hidden" name="prod_buyer" value="${pagingVO.searchVO.prod_buyer }"/> 
      <input type="hidden" name="prod_name" value="${pagingVO.searchVO.prod_name }"/>
   </form>
   
   <div id="searchUI">  <!-- 입력 받기 위한 div-->
      <select id="prod_lgu">
         <option value>분류선택</option>
      </select> 
      <select id="prod_buyer">
         <option value>거래처선택</option>
      </select> 
      <input type="text" id="prod_name" value="${pagingVO.searchVO.prod_name }">
      <input type="button" value="검색" id="searchBtn">
   </div>
   
   <c:url value="/prod/prodInsert.do" var="insertURL" />
   <button class="btn btn-info" type="button"
      onclick="location.href='${insertURL}';">신규상품 등록</button>
   <table class="table table-bordered table-striped">
      <thead>
         <tr>
            <th><spring:message code="prod.prod_id"/></th>
            <th><spring:message code="prod.prod_name"/></th>
            <th><spring:message code="prod.prod_lgu"/></th>
            <th><spring:message code="prod.prod_buyer"/></th>
            <th><spring:message code="prod.prod_cost"/></th>
            <th><spring:message code="prod.prod_price"/></th>
            <th><spring:message code="prod.prod_mileage"/></th>
         </tr>
      </thead>
      <tbody>
         <c:set var="prodList" value="${pagingVO.dataList }" />
         <c:forEach var="prod" items="${prodList }">
            <c:url value="/prod/${prod.prod_id }" var="viewURL" />
            <tr>
               <td>${prod.prod_id }</td>
               <td><a href="${viewURL }">${prod.prod_name }</a></td>
               <td>${prod.lprod_nm }</td>
               <td>${prod.buyer_name }</td>
               <td>${prod.prod_cost }</td>
               <td>${prod.prod_price }</td>
               <td>${prod.prod_mileage }</td>
            </tr>
         </c:forEach>
      </tbody>
      <tfoot>
         <tr>
            <td colspan="7">
               <div id="pagingArea">${pagingVO.pagingHTML }</div>
            </td>
         </tr>
      </tfoot>
   </table>
   <script type="text/javascript">
   var prod_lguTag = $("#prod_lgu"); 
   var prod_buyerTag = $("#prod_buyer"); 
   var searchUI = $("#searchUI");
   var searchBtn = $("#searchBtn");
   var searchForm = $("#searchForm");
   var pageTag = $("[name='page']");
   
    $(prod_lguTag).generateLprod("${pageContext.request.contextPath}","${pagingVO.searchVO.prod_lgu}");

   
   $(prod_lguTag).on("change", function(){
      let lgu = $(this).val();
      $(prod_buyerTag).generateBuyer({
         cPath : "${pageContext.request.contextPath}",
         lgu : lgu,
         selectedBuyer : "${pagingVO.searchVO.prod_buyer}"
      });
   })
    $(prod_lguTag).trigger("change");  // 페이지 시작할때 prod_buyer 리스트 전부를 <option>에 넣는 이벤트 발생시키기
   
   
   $("#pagingArea").on("click", "a", function(){
      let page = $(this).data("page");
      //if(page > 0)
      //location.href="?page="+page;
      if(page <= 0) return;
         pageTag.val(page);
         searchForm.submit();
   })
   
   searchBtn.on("click", function(){
      let child = searchUI.find(":input");
      
      $(child).each(function(index, element){
         let id = $(this).prop("id");
         let value = $(this).val();
         searchForm.find("[name='"+id+"']").val(value);
      });
      searchForm.submit();
   })
   
</script>
</body>
</html>