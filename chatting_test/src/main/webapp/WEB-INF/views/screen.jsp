<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
</head>
<body>
    <form>
        <!-- 송신 메시지를 작성하는 텍스트 박스 -->
        <input id="textMessage" type="text">
        <!-- 메시지 송신을 하는 버튼 -->
        <input onclick="sendMessage()" value="Send" type="button">
        <!-- WebSocket 접속 종료하는 버튼 -->
        <input onclick="disconnect()" value="Disconnect" type="button">
    </form>
    <br />
    <!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
    <textarea id="messageTextArea" rows="10" cols="50"></textarea>
    <script type="text/javascript">
					// 「WebSocketEx」는 프로젝트 명      
					// 「websocket」는 호스트 명      
					// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)      
					var webSocket = new WebSocket(
							"ws://192.168.14.22:8080/chatting_test/websocket");
					
					// 콘솔 텍스트 에리어 오브젝트      
					var messageTextArea = document
							.getElementById("messageTextArea");
					
					// WebSocket 서버와 접속이 되면 호출되는 함수      
					webSocket.onopen = function(event) {
						// 콘솔 텍스트에 메시지를 출력한다.        
						messageTextArea.value += "Server connect...\n";
					};
					// WebSocket 서버와 접속이 끊기면 호출되는 함수      
					webSocket.onclose = function(message) {
						// 콘솔 텍스트에 메시지를 출력한다.        
						messageTextArea.value += "Server Disconnect...\n";
					};
					// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수      
					webSocket.onerror = function(message) {
						// 콘솔 텍스트에 메시지를 출력한다.        
						messageTextArea.value += "error...\n";
					};
					
					
					// WebSocket 서버로 부터 메시지가 오면 호출되는 함수      
					webSocket.onmessage = function(message) {
						// 콘솔 텍스트에 메시지를 출력한다.        
						messageTextArea.value += "Recieve From Server => "
								+ message.data + "\n";
					};  
					
					
					
					// Send 버튼을 누르면 호출되는 함수    
					function sendMessage() {
						// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득한다.      
						var message = document.getElementById("textMessage");
						// 콘솔 텍스트에 메시지를 출력한다.      
						messageTextArea.value += "Send to Server => "
								+ message.value + "\n";
						// WebSocket 서버에 메시지를 송신한다.      
						webSocket.send(message.value);      
						// 송신 메시지를 작성하는 텍스트 박스를 초기화한다.     
						message.value = "";    } 
					
					
					
					
						// Disconnect 버튼을 누르면 호출되는 함수    
						function disconnect() {      
						// WebSocket 접속 해제      
						webSocket.close();
					}
				</script>
</body>
</html>

