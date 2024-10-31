package websocket;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.valueobjects.IpAddress;
import json.JsonHelper;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_ta", configurator = ConfiguratorWithRequest.class)
public class WebsocketForTA {

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		// ユーザー情報を取得
		String userType="";
		if(config.getUserProperties().get("UserType") !=null) {
			userType = (String) config.getUserProperties().get("UserType");
		}
		
		userType="TA"; // 一時的に設定
		
		// TA、教員でなければSessionを終了
		if (!userType.equals("Administrator") && !userType.equals("Teacher") && !userType.equals("TA")) {
			session.close();
			return;
		}
		
		// Sessionを保存
		WebsocketForAdministrator.taSessions.add(session);

		String jsonText = JsonHelper.getJsonForTeacher(WebsocketForAdministrator.studentManager.getStudentList(), WebsocketForAdministrator.studentManager.getHandUpStudentList());
		WebsocketForAdministrator.sendMessage(session,jsonText);
	}

	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
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

		WebsocketForAdministrator.broadcastStateForStudents();
		WebsocketForAdministrator.broadcastStateForTa();

		return JsonHelper.getJsonForTeacher(WebsocketForAdministrator.studentManager.getStudentList(), WebsocketForAdministrator.studentManager.getHandUpStudentList());
	}

	@OnClose
	public void onClose(Session session) {
		WebsocketForAdministrator.taSessions.remove(session);
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
		//		String log = client.getId() + " was error. [" + error.getMessage() + "]";
		error.printStackTrace();
	}

}
