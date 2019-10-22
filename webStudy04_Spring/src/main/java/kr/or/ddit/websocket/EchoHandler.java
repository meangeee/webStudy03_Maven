package kr.or.ddit.websocket;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class EchoHandler extends TextWebSocketHandler{
	@Resource(name="userList")
	Set<MemberVO> userList;
	@Resource(name="webSocketSessionList")
	List<WebSocketSession> wsList;
	
	@Data
	@AllArgsConstructor
	@EqualsAndHashCode(of="mem_id")
	public static class SendVO implements Serializable{
		private String mem_id;
		private String mem_name;
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("연결수립");
		wsList.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.err.println("연결 종료");
		wsList.remove(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		session.sendMessage(new TextMessage(msg));
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println(exception.getMessage());
	}
}
