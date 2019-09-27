<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
   <head>
      <title>03/responseDesc.jsp</title>
   </head>
   <body>
      <h4>Http 프로토콜의 응답 데이터 패키징 방식</h4>
      <pre>
         1. Response Line : Status code(응답 상태 코드) Protocol
                  ** status code: 명령에 처리 결과를 의미하는 숫자 체계
                        100 (since 1.1) : ING...(websocket)
                                 http : stateless(무상태, 비상태유지)
                         200 : OK(success)
                         300 : 완전한 처리를 위해 사용자의 추가 액션이 필요한 상황
                         400 : client side fail ,
                         400(Bad Request),404(Not Found),
                         405(Method Not Allowed),415(Media Not Supported)
                         403(Forbidden),401(UnAuthorized)
                         500 : server side fail , 500(Internal Server Error) 
                         HttpServletResponse.setStatus/sendError(**)
                         
          2. Response Header : response body의 데이터에 대한 Metadata
                   1) mime 변경 : Content-Type
                         - page 지시자의 contentType속성
                         - response.setContentType
                         - response.setHeader
                   2) 캐시 제어 : Pragma(1,0), Cache-Control(1,1), Expires(all)
                     <%
                        response.setHeader("Pragma", "no-cache");
                        response.setHeader("Cache-Control", "no-cache");
                        response.setHeader("Cache-Control", "no-store");
                        response.setDateHeader("Exprises", 0);
                     %>
                                  
                   3) auto request : Refresh
                      : <a href="<%=request.getContextPath() %>/03/autoRequest.jsp">/03/autoRequest</a>
                   4)*** 페이지 이동 : Location, 302/307
                   		<a href="${pageContext.request.contextPath}/05/flowControl.jsp">/05/flowControl.jsp참고</a>
            3. Response Body(message Body)
   </pre>
   <img src="<%=request.getContextPath() %>/images/Jellyfish.jpg"/>
      </pre>
   </body>
</html>