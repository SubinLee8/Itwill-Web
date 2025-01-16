package subin.test.chatting_test;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {
	// 사용자 세션을 저장할 ConcurrentHashMap
	private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		// System.out.println("Received: " + payload); //콘솔출력
		// 받은 메시지를 모든 사용자에게 브로드캐스트
		for (WebSocketSession s : sessions.values()) {
			if (s.isOpen()) {
				s.sendMessage(new TextMessage(payload)); // 전송될 메세지를 textMessage에 보내면 자바스크립트의 onMessage 함수 실행
			}
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		// 사용자 정보 추가 (예: 사용자 이름, 아이디 등)
		// Map<String, Object> sessionAttributes = session.getAttributes();
		// sessionAttributes.put("username", "user_" + session.getId()); // 예시로 세션 ID를
		// 이용해 사용자 이름 지정
		// sessionAttributes.put("room", "5"); // 해당 세션이 속한 채팅방 정보

		// 사용자 세션 저장
		sessions.put(session.getId(), session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 사용자 세션 제거
		sessions.remove(session.getId());
	}
}