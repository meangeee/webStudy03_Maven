package kr.or.ddit.common.events;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.websocket.EchoHandler.SendVO;

@Component
public class CustomEventListener {
	@Inject
	WebApplicationContext container;
	@Resource(name="userList")
	Set<SendVO> userList;
	@Resource(name="webSocketSessionList")
	List<WebSocketSession> wsList;
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void handleEvent(ContextRefreshedEvent event) {
		System.err.println("이벤트 처리");
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
	}
	
	@EventListener(classes=AuthenticateSuccessEvent.class)
	public void handerSuccessEvent(AuthenticateSuccessEvent event) throws IOException{
		MemberVO authMember = event.getAuthMember();
		SendVO send = new SendVO(authMember.getMem_id(), authMember.getMem_name());
		userList.add(send);
		broadcast();
	}
	
	private void broadcast() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(userList);
		for(WebSocketSession session : wsList) {
			session.sendMessage(new TextMessage(json));
		}
	}

	@EventListener(classes=LogoutSuccessEvent.class)
	public void logoutEvent(LogoutSuccessEvent event) throws IOException {
		MemberVO authMember = event.getAuthMember();
		if(authMember==null) return;
		SendVO send = 
				new SendVO(authMember.getMem_id(), authMember.getMem_name());
		userList.remove(send);
		broadcast();		
	}
}