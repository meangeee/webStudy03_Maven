package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static java.util.Calendar.*;


/**
 * Servlet implementation class CalculateServlet
 */
@WebServlet("/calculator12")
public class CalculateServlet extends HttpServlet {
   public static enum OperatorType{
	   
      PLUS('+',new Operator() {
         public int opreate(int left, int right) {
            return left+right;
         }

		@Override
		public int operate(int left, int right) {
			return 0;
		}

      }),MINUS('-',(left,right)->{return left-right;}),
      MULTIPLY('*',(left,right)->{return left*right;}),
      DIVIDE('/',(left,right)->{return left/right;});
      
      OperatorType(char sign,Operator oprator){
         this.sign = sign;
         this.operator = oprator;
      }
      char sign;
      
      private Operator operator; //setter를 만들거나 생성자를 만들어서 전략을 넣는다 
      
      public char getSign(){
         return sign;
      }
      
      public int operate(int left,int right) {
         //연산
         int result= operator.operate(left, right);
         return result;
      }
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      String leftParam = request.getParameter("leftOp");
      String rightParam = request.getParameter("rightOp");
      String opParam = request.getParameter("operator");
      
      int status = 200;
      if(leftParam ==null || !leftParam.matches("\\d+") || rightParam==null || !rightParam.matches("\\d+")) {
         status = 400;
      }
      
      String plain = null;
      
      try {
      OperatorType op = OperatorType.valueOf(opParam);
      int left = Integer.parseInt(leftParam);
      int right = Integer.parseInt(rightParam);
      int result = op.operate(left, right);
      plain = String.format("%d %s %d=%d ", left,op.getSign(),right,result);
      }catch (Exception e) {
         status = 400;
      }
      if(status!=200) {
         response.sendError(status);
      }else {
         response.setContentType("text/plain");
         try(
         PrintWriter out = response.getWriter();
         ){      
         out.println(plain);
         }
      }
      
   }

}