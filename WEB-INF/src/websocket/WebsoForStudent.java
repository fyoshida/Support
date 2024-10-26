package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.entities.Student;
import domain.valueobjects.IpAddress;
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket", configurator = WebsoForStudent.ConfiguratorWithRequest.class)
public class WebsoForStudent {

	private IpAddress clientIpAddress;
	private Student student;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		WebsocketForAdministrator.clientSessions.add(session);
		String clientIp = (String) config.getUserProperties().get("IpAddress");
		clientIpAddress = new IpAddress(clientIp);

		Optional<Student> optStudent = WebsocketForAdministrator.studentManager.getStudent(clientIpAddress);
		if (optStudent.isEmpty()) {
			return ;
		}
		student = optStudent.get();

		StudentResponse studentResponse = getStudentResponbse();
		String jsonText = getJsonText(studentResponse);
		sendMessage(session,jsonText);
	}
	
	@OnMessage
	public String onMessage(String message, Session session) {
		switch(message) {
		case "None":
			WebsocketForAdministrator.studentManager.handDown(clientIpAddress);
			break;
		case "Troubled":
			WebsocketForAdministrator.studentManager.handUp(clientIpAddress);
			break;
		case "Supporting":
			WebsocketForAdministrator.studentManager.supporting(clientIpAddress);
			break;
		}

		StudentResponse studentResponse = getStudentResponbse();
		String jsonText = getJsonText(studentResponse);
		broadcastMessage(jsonText);
		
		return "Echo: " + message;
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
//		String log = client.getId() + " was error. [" + error.getMessage() + "]";
		error.printStackTrace();
	}

	private StudentResponse getStudentResponbse() {
		StudentJson studentJson =StudentJsonHelper.getStudentJson(student);

		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList =StudentJsonHelper.getStundentJsonListForStudent(handupStudentList,clientIpAddress);

		return new StudentResponse(studentJson,handupStudentJsonList);
	}
	
	private String getJsonText(StudentResponse studentResponse) {
		// JSON形式で出力
		String jsonText = "";
		try {
			jsonText = JsonConverter.getJsonText(studentResponse);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return jsonText;
	}
	
	private void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void broadcastMessage(String message) {
		for (Session client : WebsocketForAdministrator.clientSessions) {
			try {
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				WebsocketForAdministrator.clientSessions.remove(client); // 送信失敗したセッションは削除
			}
		}
	}

	// Configuratorクラスの設定
	public static class ConfiguratorWithRequest extends Configurator {
		@Override
		public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {

			Map<String, List<String>> params = request.getParameterMap();
			if (params.containsKey("ip")) {
				String ipAddress = params.get("ip").get(0);
				config.getUserProperties().put("IpAddress", ipAddress);
			}

		}
	}
}
