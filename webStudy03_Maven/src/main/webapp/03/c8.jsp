<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">


function selectCheck(form){
	form.submit();
}
// 지난달로 가는 버튼을 누르면 이동해야함
function lastMonth(form){
	if(form.month.value>1){
		form.submit();
	}else{
		form.month.value = 12;
		form.year.value--;
	}
	form.submit();
}

//다음달로 가는 버튼을 누르면 다음달로
function nextMonth(form){
	if(form.month.value<12){
		form.month.value++;
	}else{
		form.month.value = 1;
		form.year.value++;
	}
}
</script>


</head>

<body>
<%
//현재 날짜와 시간 정보를 가진 Calendar객체를 생성한다.
Calendar cal = Calendar.getInstance();
//현재 날짜 정보를 가져온다
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH);
int date = cal.get(Calendar.DATE);


//오늘 날짜
String today = year + " : " + (month+1) + " : " + date;

//입력한 년도와 월
String input_year = request.getParameter("year");
String input_month = request.getParameter("month");

if(input_month != null){
	month = Integer.parseInt(input_month)-1;
}
if(input_year != null){
	year = Integer.parseInt(input_year);
}

// 년도와 월을 세팅하고 월의 시작인 1일을 세팅한다.
cal.set(year, month, 1);
//해당 월의 첫날을 구함
int firstDate = cal.getActualMinimum(Calendar.DATE);
//해당 월의 마지막날을 구함
int lastDate = cal.getActualMaximum(Calendar.DATE);
//1일의 요일을 구함
int firstDay = cal.get(Calendar.DAY_OF_WEEK);

int count = 0;
%>

<!-- 데이터 전송 form 생성 -->
<form action="calendar.jsp" method = "post" name="change">
<!-- 이전달과 다음달로 움직일 수 있는 버튼 생성 , 검색 창 생성-->
<table width="400" border="1" align="center">
	<tr>
		<td width="140" align ="right">
		<input type="button" value="◁" onClick="lastMonth(this.form)"></td>
		</td>
		<td width="140" align="center">
		<select name="year" onchange="selectCheck(this.form)">
		<%
    	for(int i=year-10; i<year+10; i++){
    	    String selected = (i == year)?"selected":"";
    	    String color = (i == year)?"#CCCCCC":"#FFFFFF";
    	    	out.print("<option value="+i+" "+selected+" style=background:"+color+">"+i+"</option>");
    	}
      	%>
		</select>
		<select name="month" onchange="selectCheck(this.form)">
        <%
      	for(int i=1;i<=12;i++){
       		String selected = (i == month+1)?"selected":"";
       		String color = (i == month+1)?"#CCCCCC":"#FFFFFF";
         		out.print("<option value="+i+" "+selected+" style=background:"+color+">"+i+"</option>");       
     	}
        %>

		</select></td>
		<td width="140"><input type="button" value="▷" onclick="nextMonth(this.form)"></td>
	</tr>
	
<!-- 	오늘 날짜 출력 -->
	<tr>
		<td align="right" colspan="3"><a href="calendar.jps"><font size="2">
		오늘 : <%= today %></font></a></td>
	</tr>
</table>
</form>
<!-- 요일,날짜 출력 -->
<table width="400" cellpadding="2" cellspacing="0" align ="center" border="1">
	<tr height ="30">
		<td><font size="2">일</font></td>
		<td><font size="2">월</font></td>
		<td><font size="2">화</font></td>
		<td><font size="2">수</font></td>
		<td><font size="2">목</font></td>
		<td><font size="2">금</font></td>
		<td><font size="2">토</font></td>
	</tr>
	<tr height="30">
	<%
	for(int i=1; i<firstDate;i++){
		count++;
	%>
		<td>&nbsp;</td>
	<%
	}
	for(int i=firstDate; i<lastDate; i++){
		count++;
	%>
		<td><font size="2"><%= i %></font></td>
	<%
		if(count%7 == 0 && i < lastDate){
	%>
	</tr>
	<tr height="30">
	<%
		}
	}
	while(count%7 != 0){
	%>	
		<td>&nbsp;</td>
	<%
		count++;
	}
	%>
	</tr>
</table>


</body>
</html>