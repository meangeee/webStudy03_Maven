package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.events.LogoutSuccessEvent;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LogOutController {
   
   @Inject
   ApplicationEventPublisher publisher;
   
   @RequestMapping(value = "/logout", method = RequestMethod.POST)
   public String doPost(HttpSession session, HttpServletResponse resp) throws IOException {

      if (session.isNew()) {
         resp.sendError(400);
         return null;
      }
      MemberVO authMember = (MemberVO) session.getAttribute("authMember");
      publisher.publishEvent(new LogoutSuccessEvent(this, authMember));
      
      session.invalidate();
      // session의 정보를 다 지우고 session의 아이디를 바꿔줘
      return "redirect:/";
   }

}