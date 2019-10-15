<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	public long factorial(int leftOp){
		if(leftOp<=0) throw new IllegalArgumentException("피연산자 확인 " + leftOp);
		if(leftOp==1){
			return 1;
		}else{
			return leftOp * factorial(leftOp-1);
		}
			
	}
%>
 <%
 	request.setCharacterEncoding("UTF-8");
 	String leftParam = request.getParameter("leftOp");
 	long result = -1;
 	if(leftParam!=null && leftParam.matches("^1?[0-9]$")){
 		int leftOp = Integer.parseInt(leftParam);
 		result = factorial(leftOp);
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/factorial.jsp</title>
<script type="text/javascript" src ="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
action, href 처럼 다음요청의 주소를 기술하는 속성에, 값이 생략되면, 현재 주소가 그대로 반영.

<form id="facForm">
   피연산자 : <input type ="number" name="leftOp" min="1" max="19"/>
   <input type ="submit" value="submit"> 
<!--       이벤트 : click - submit - 끝 (target-click : 자신, target-submit : form) -->
   <input type ="reset" value="reset"> 
<!--       이벤트 : click - reset - 끝 (target-click : 자신, target-reset : form) -->
   <input type ="button" value="button">  
<!--       이벤트 : click - 끝 (target-click : 자신) -->
<!--    현재 브라우저가 가지고 있는 주소 그대로를 가져 온다. -->
</form>
<%
	if(result!=-1){
		%>
		<div>
			<%=String.format("%s!=%d", leftParam, result)%>
		</div>
		<%
	}
%>

<script type="text/javascript">
   $("#facForm").on("submit", (event)=>{
//       console.log(event.target);
//       console.log($(this));
      var form = event.target;
      console.log(form);
      console.log(form.leftOp);
      console.log(form.leftOp.value);
      var leftOp = form.leftOp.value;
      //타입확인
      console.log(typeof leftOp);
      //1~19
      var regex = /^1?[0-9]$/igm;
      var valid = regex.test(leftOp);
      
      if(!vaild){
    	  alert("피연산자 확인");
      }
      return valid;   // 상황종료
//    this==event.target == form
   });   
</script>
</body>
</html>