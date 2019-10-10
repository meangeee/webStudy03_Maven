<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src ="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function eventHandler(year, month){
		var form = document.calForm;
		//그 객체의 선언여부를 확인 1.선언확인(있으면 true) 2.null확인 3.유효한지 확인 javascript에서.
		//근데 0이어도 false로 체크함 그래서 밑에처럼
		if(year && month>=0){
			form.year.value = year;
			form.month.value = month;
		}
		form.submit();
	}
</script>

<script type="text/javascript"></script>

<script type="text/javascript"></script>

</head>
<body>
<%
String yearStr = request.getParameter("year");
String monthStr = request.getParameter("month");
String lang = request.getParameter("lang");


Calendar cal = getInstance();
if(yearStr!=null && yearStr.matches("\\d{4}") &&
	monthStr != null && monthStr.matches("\\d{1,2}")){
	cal.set(YEAR, Integer.parseInt(yearStr));
	cal.set(MONTH, Integer.parseInt(monthStr));
}

int year = cal.get(YEAR);
int month = cal.get(MONTH);
int currentDate = cal.get(DAY_OF_MONTH);
//이전달
cal.add(MONTH, -1);
int beforeYear = cal.get(YEAR);
int beforeMonth = cal.get(MONTH);
//다음달
cal.add(MONTH, 2);
int nextYear = cal.get(YEAR);
int nextMonth = cal.get(MONTH);
cal.add(MONTH, -1);
cal.set(DAY_OF_MONTH, 1);
int date = cal.get(DAY_OF_WEEK);   // 일주일 중 몇번째 날이냐
int offset = date-1; // 첫번째 주 공백의 수
int lastDay = cal.getActualMaximum(DAY_OF_MONTH);

Locale[] locales = Locale.getAvailableLocales();
Locale currentLocale = request.getLocale();
if(StringUtils.isNotBlank(lang)){
	currentLocale = Locale.forLanguageTag(lang);
}

DateFormatSymbols dfs = new DateFormatSymbols(currentLocale); //? 뭐라고?
String[] months = dfs.getMonths();
String[] weekDays = dfs.getWeekdays();
%>
<h4><%=String.format("%d년 %d월", year, month+1) %></h4>
<a href="#" onclick="eventHandler(<%=beforeYear%>, <%=beforeMonth%>);">이전달</a>	<!-- 현재 브라우저의 위치. 절대 자신의 주소를 불러온다고 생각하면 안됨. ?는 구분자 #은 그페이지안에서만 논다고요?-->
&nbsp;&nbsp;&nbsp;&nbsp;

<form name ="calForm" method="get">
	<input type="hidden" name="command" value="calendar"/>
	<input type="number" name="year" value ="<%=year %>"
		onblur="eventHandler();"/>년
	<select name="month" onchange="eventHandler();">
		<%
			for(int idx=0; idx<months.length-1; idx++){
			%>
			<option <%=idx==month ? "selected":"" %> value=<%=idx%>><%=months[idx] %></option>
			<%				
			}//selected가 뭐라고요? 이젠알지
		%>
	</select>
	<select name="lang" onchange="eventHandler();">
		<% 
			for(Locale tmp : locales){
				if(StringUtils.isNotBlank(tmp.getDisplayLanguage())){
					
				%>
				<option <%=tmp.equals(currentLocale)?"selected":"" %> value="<%=tmp.toLanguageTag() %>"><%=tmp.getDisplayLanguage(tmp) %></option>
				<%
				}
				
			}
		%>
	</select>
</form>
<a href="javascript:eventHandler(<%=nextYear%>, <%=nextMonth%>);">다음달</a>
<table>
	<thead>
		<tr>
			<%
				for(String tmp : weekDays){
					if(StringUtils.isNotBlank(tmp)){//비어있는지, 비어있지 않으면
						%>
						<th><%=tmp %></th>
						<%
					}
				}
			%>
		</tr>
	</thead>
	<tbody>
   <% 
    
      int count=1;
      for(int row=0 ; row <6 ; row++){
         out.println("<tr>");   // out : 2byte짜리 outputStream
         for(int col=0 ; col<7; col++){
            int display =  count++ - offset;
            String displayStr = display +"";
            if(display<1 || display> lastDay){
               displayStr = "&nbsp;";
            }
            out.println(String.format("<td>%s</td>", displayStr));
         }
         out.println("</tr>");
      }
   %>
	</tbody>
</table>
<script type="text/javascript">
	$("td, th").css({border:"1px solid black"});
	$("td:first-child").css({color:"red"});
	$("th:first-child").css({color:"red"});
	$("td:last-child").css({color:"blue"});
	$("th:last-child").css({color:"blue"});
	$("table").css({width:"100%" , //java에서의 =이 여기선 : //,로 property구분
					height:"500px",
					borderCollapse : "collapse"});
	
	var today = new Date();
	   if(today.getFullYear() ==<%=year%> && today.getMonth()==<%=month%>){
	      console.log("현재 달력임");
	      var date = today.getDate();
	      var tds = $("td");
	      for(var idx=0; idx<tds.length; idx++){
	         if($(tds[idx]).text()==date){
	            $(tds[idx]).css({backgroundColor:"green"});
	         }
	      }
	   } 
</script>
</body>
</html>