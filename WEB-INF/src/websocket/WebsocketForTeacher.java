package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
@ServerEndpoint(value = "/websocket/teacher", configurator = WebsocketForTeacher.ConfiguratorWithRequest.class)
public class WebsocketForTeacher {


	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		TeacherResponse teacherResponse = getTeacherResponbse();
		String jsonText = getJsonText(teacherResponse);
		sendMessage(session,jsonText);
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		String[] messages = message.split(":");
		String command = messages[0];
		String ipAddress = messages[1];
		IpAddress clientIpAddress = new IpAddress(ipAddress);
		switch (command) {
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

		TeacherResponse teacherResponse = getTeacherResponbse();
		String jsonText = getJsonText(teacherResponse);
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

	
	private TeacherResponse getTeacherResponbse() {
		List<Student> studentList = WebsocketForAdministrator.studentManager.getStudentList();
		List<StudentJson> studentJsonList =StudentJsonHelper.getStudentJsonList(studentList);

		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList =StudentJsonHelper.getStudentJsonList(handupStudentList);

		return new TeacherResponse(studentJsonList,handupStudentJsonList);
	}
	
	private String getJsonText(TeacherResponse teacherResponse) {
		// JSON形式で出力
		String jsonText = "";
		try {
			jsonText = JsonConverter.getJsonText(teacherResponse);
		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return jsonText;
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

}
