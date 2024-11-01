package websocket;

import java.io.IOException;
import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.entities.Student;
import domain.valueobjects.IpAddress;
import json.JsonHelper;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_ta", configurator = ConfiguratorWithRequest.class)
public class WebsocketForTA {
	
	private MessageSender messageSender;
	private JsonHelper jsonHelper;

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
		WebsocketForAdministrator.taSessionSet.add(session);
		
		// MessageSender、JsonHelperを生成
		messageSender=new MessageSender();
		jsonHelper = new JsonHelper();

		// 現在の状態を送信
		List<Student> studentList = WebsocketForAdministrator.studentManager.getStudentList();
		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();

		String jsonText = jsonHelper.getJsonForTeacher(studentList,handupStudentList);
		messageSender.sendMessage(session,jsonText);
	}

	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
		// 受信したメッセージからコマンドとＩＰアドレスを取得
		String[] messages = message.split(":");
		String command = messages[0];
		String ipAddress = messages[1];
		IpAddress clientIpAddress = new IpAddress(ipAddress);
		
		// コマンド実行
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

		// 全教員、全学生に現在の状態を送信
		List<Student> studentList = WebsocketForAdministrator.studentManager.getStudentList();
		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();

		messageSender.broadcastStateForTa(WebsocketForAdministrator.taSessionSet, studentList, handupStudentList);
		messageSender.broadcastStateForStudents(WebsocketForAdministrator.studentSessionMap, studentList, handupStudentList);

		return jsonHelper.getJsonForTeacher(studentList, handupStudentList);
	}

	@OnClose
	public void onClose(Session session) {
		WebsocketForAdministrator.taSessionSet.removeIf(s -> s.equals(session));
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
		//		String log = client.getId() + " was error. [" + error.getMessage() + "]";
		error.printStackTrace();
	}

}
