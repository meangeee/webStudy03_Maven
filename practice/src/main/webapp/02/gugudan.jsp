<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/gugudna.jsp</title>
<style>
   .blue{
      background-color:blue;
   }
   table, tr, td {
      border : 1px solid;
      border-collapse : collapse; 
   }
</style>
</head>
<body>
<h4>구구단</h4>
<!-- 2~9 단까지의 구구단을 table 태그로 출력 -->
<!-- 3단의 배경색은 파란색 -->

<%! 
   private String gugudan(int dan, int mul){
      String ptrn = "%d*%d=%d";
      String result = String.format(ptrn, dan, mul, dan*mul);
      return result;
   }

   private StringBuffer tonggugudan(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException{
      
      int min = 0;
      int max = 0;
      String minDan = req.getParameter("minDan");
      String maxDan = req.getParameter("maxDan");
      if(minDan!=null && minDan.matches("[0-9]+") && 
            maxDan!=null && maxDan.matches("[0-9]+")){
//          min = Integer.parseInt(minDan);
//          max = Integer.parseInt(maxDan);
         min = new Integer(minDan);
         max = new Integer(maxDan);
      } else {
         resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
         return null;
         // 400 번대 에러코드 : 클라이언트 잘못
         // 500 번대 에러코드 : 서버 잘못
      }
      
      StringBuffer table = new StringBuffer();
      table.append("<table>");
      for(int dan=min ; dan <=max ; dan++){
         String clzName = "normal";
         if(dan ==3){
            clzName = "blue";
         }
         table.append("<tr class="+ clzName +">");
         for(int mul=1 ; mul <=9 ; mul++){
            table.append("<td>"+gugudan(dan, mul)+"</td>");
         }
         table.append("</tr>");
      }
      table.append("</table>");
      return table;
   }
   
      
%>
<table>
<%   
   int min = 0;
   int max = 0;
   String minDan = request.getParameter("minDan");
   String maxDan = request.getParameter("maxDan");
   if(minDan!=null && minDan.matches("[0-9]+") && 
         maxDan!=null && maxDan.matches("[0-9]+")){
//       min = Integer.parseInt(minDan);
//       max = Integer.parseInt(maxDan);
      min = new Integer(minDan);
      max = new Integer(maxDan);
   } else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;
      // 400 번대 에러코드 : 클라이언트 잘못
      // 500 번대 에러코드 : 서버 잘못
   }
   
   for(int dan=min ; dan <=max ; dan++){
      String clzName = "normal";
      if(dan ==3){
         clzName = "blue";
      }
      %>
      <tr class="<%=clzName%>">
      <%
      for(int mul=1 ; mul <=9 ; mul++){
         %>
         <td><%=gugudan(dan, mul)%></td>
         <%
      }
      %>
      </tr>
      <%
   }
%>
</table>
<%=tonggugudan(request, response) %>
</body>
</html>