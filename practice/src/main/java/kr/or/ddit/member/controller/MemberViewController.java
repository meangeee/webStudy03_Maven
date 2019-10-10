package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;
@CommandHandler
public class MemberViewController extends HttpServlet {
	// db로부터 데이터를 긁어와야함 = service = 의존관계를 형성한다
	IMemberService service = MemberServiceImpl.getInstance();
	
	@URIMapping(value ="/member/memberView.do")
	public String memberView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터를 가져왐
		String who = req.getParameter("who");
		String viewName = null;
		// 가져온 데이터를 꼭 검증해야지
		if (StringUtils.isBlank(who)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"누그를 조회?");//필수 파라미터가 누락되었다.
			return null;
		} else {
			//선생님코드
//			MemberVO saved = service.retrieveMember(new MemberVO(who, null));
			
			// 로직을 실행하고 나면 돌아오는 데이터가 있다
			MemberVO member = new MemberVO();
			member.setMem_id(who);
			MemberVO saved = service.retrieveMember(member);
			req.setAttribute("member", saved);

			// 그 데이터를 가지고 view(공유영역)로 전달
			viewName = "member/memberView";
			return viewName;
		}
	}
}
