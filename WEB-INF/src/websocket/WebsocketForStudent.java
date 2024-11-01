package websocket;

import java.util.List;
import java.util.Optional;

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
@ServerEndpoint(value = "/websocket", configurator = ConfiguratorWithRequest.class)
public class WebsocketForStudent {

	private IpAddress clientIpAddress;
	private Student student;
	private MessageSender messageSender;
	private JsonHelper jsonHelper;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws JsonProcessingException {
	
		// クライアントのIPアドレスを取得
		String strIpAddress = (String) config.getUserProperties().get("IpAddress");
		clientIpAddress=new IpAddress(strIpAddress);
		clientIpAddress=new IpAddress("133.44.118.158"); // 一時的に設定

		// IPから該当するStudentを取得
		Optional<Student> optStudent = WebsocketForAdministrator.studentManager.getStudent(clientIpAddress);
		if (optStudent.isEmpty()) {
			return ;
		}
		student = optStudent.get();
		
		// その学生用のSessionを登録
		WebsocketForAdministrator.studentSessionMap.put(student, session);

		// MessageSender、JsonHelperを生成
		messageSender=new MessageSender();
		jsonHelper = new JsonHelper();

		// 現在の状態を送信
		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();
		String jsonText = jsonHelper.getJsonForStudent(student,handupStudentList);
		messageSender.sendMessage(session,jsonText);
	}
	
	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
		
		// コマンド実行
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

		// 全教員、全学生に現在の状態を送信
		List<Student> studentList = WebsocketForAdministrator.studentManager.getStudentList();
		List<Student> handupStudentList = WebsocketForAdministrator.studentManager.getHandUpStudentList();
		messageSender.broadcastStateForTa(WebsocketForAdministrator.taSessionSet, studentList, handupStudentList);
		messageSender.broadcastStateForStudents(WebsocketForAdministrator.studentSessionMap, studentList, handupStudentList);
		
		return 	jsonHelper.getJsonForStudent(student,handupStudentList);

	}

	@OnClose
	public void onClose(Session session) {
		WebsocketForAdministrator.studentSessionMap.entrySet().removeIf(entry -> session.equals(entry.getValue()));
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
//		String log = client.getId() + " was error. [" + error.getMessage() + "]";
		error.printStackTrace();
	}

}
