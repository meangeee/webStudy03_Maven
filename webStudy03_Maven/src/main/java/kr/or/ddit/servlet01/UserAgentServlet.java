package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enums.BrowserType;
import kr.or.ddit.enums.OsType;

/**
 * 클라이언트의 시스템을 파악하고,
 * 최종적으로 "당신의 OS는 XXX이고, 당신의 브라우저는 XXX입니다."
 * 라는 메시지를 alert 창으로 띄울것.
 * linux / window
 * chrome / firefox / MSIE
 */
@WebServlet("/userAgent")
public class UserAgentServlet extends HttpServlet{
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      resp.setContentType("text/html;charset=UTF-8");
      
      String userAgent = req.getHeader("User-Agent").toUpperCase();
      System.out.println(userAgent);
      
      OsType os = OsType.searchOS(userAgent);
      BrowserType browser  = BrowserType.searchBrowser(userAgent);
      
      String osName = os.getName();
      String browserName = browser.getName();
      
      String msgPtrn = "alert('당신의 OS는 %s 이고, 당신의 브라우저는 %s 입니다.')";
      StringBuffer html = new StringBuffer();
      html.append("<html>");
      html.append("<body>");
      html.append("<script type='text/javascript'>");
      html.append(String.format(msgPtrn, osName, browserName));
      html.append("</script>");
      html.append("</body>");
      html.append("</html>");
      
      try(
         PrintWriter out = resp.getWriter();
      ){
         out.println(html);
      }
      
   }
}