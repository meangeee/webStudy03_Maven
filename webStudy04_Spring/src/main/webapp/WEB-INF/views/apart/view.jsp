<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<!-- 서울지역의 201909의 아파트 거래가 정보를 출력 -->
<button id="btn"> 가져오기 </button>
<div id="resultArea">
	<table>
		<thead>
			<tr id="headerArea"></tr>
		</thead>
		<tbody id="listBody">

		</tbody>
	</table>
</div>
<script type="text/javascript">
   let headerArea = $("#headerArea");
   let listBody = $("#listBody");
   
   $("#btn").on("click",function(){
      $.ajax({
         dataType:"json",
         success:function(resp){
            console.log(resp);
            let response = resp.response;
            let header = response.header;
            let body = response.body;
            if(header.resultCode!="00"){
               alert(header.resultMsg);
               return;
            }
            let items = body.items.item;
            let trHeaders = [];
//             let listBodyTags = [];
            let trTags = [];
            $(items).each(function(index,item){
               let count = 0;
               let trTag = $("<tr>");
               trTags.push(trTag);
               for(let propname in item){
                   if(count==0){
					   let thTag = 
                	   trHeaders.push($("<th>").text(propname));
                   }
            	   console.log(propname+":"+item[propname]);
            	   trTag.append($("<td>").text(item[propname]));
               }
               count++;
            });
            listBody.append(trTags);
            headerArea.html(trHeaders);
         },
         error:function(err){
            console.log(err.status);
         }
      })
   })
   
</script>
</body>
</html>