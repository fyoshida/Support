package websocket;

import java.io.IOException;
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
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket", configurator = ConfiguratorWithRequest.class)
public class WebsoForStudent {

	private IpAddress clientIpAddress;
	private Student student;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	
		String strIpAddress = (String) config.getUserProperties().get("IpAddress");
		clientIpAddress=new IpAddress(strIpAddress);
		clientIpAddress=new IpAddress("133.44.118.158");

		Optional<Student> optStudent = WebsocketForTA.studentManager.getStudent(clientIpAddress);
		if (optStudent.isEmpty()) {
			return ;
		}
		student = optStudent.get();
		
		StudentSession studentSession = new StudentSession(student,session);
		WebsocketForTA.studentSessions.add(studentSession);

		StudentResponse studentResponse = getStudentResponse(student);
		String jsonText = getJsonText(studentResponse);
		sendMessage(session,jsonText);
	}
	
	@OnMessage
	public String onMessage(String message, Session session) {
		switch(message) {
		case "None":
			WebsocketForTA.studentManager.handDown(clientIpAddress);
			break;
		case "Troubled":
			WebsocketForTA.studentManager.handUp(clientIpAddress);
			break;
		case "Supporting":
			WebsocketForTA.studentManager.supporting(clientIpAddress);
			break;
		}

		TeacherResponse teacherResponse = getTeacherResponbse();
		String jsonTextTa = getJsonText(teacherResponse);
		broadcastMessageForTa(jsonTextTa);
		
		for(StudentSession studentSession : WebsocketForTA.studentSessions) {
			Student student = studentSession.getStudent();
			Session client = studentSession.getSession();
			
			StudentResponse studentResponse = getStudentResponse(student);
			String jsonText = getJsonText(studentResponse);
			sendMessage(client, jsonText);
		}
		
		StudentResponse studentResponse = getStudentResponse(student);
		String jsonText = getJsonText(studentResponse);

		return jsonText;
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
		List<Student> studentList = WebsocketForTA.studentManager.getStudentList();
		List<StudentJson> studentJsonList =StudentJsonHelper.getStudentJsonList(studentList);

		List<Student> handupStudentList = WebsocketForTA.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList =StudentJsonHelper.getStudentJsonList(handupStudentList);

		return new TeacherResponse(studentJsonList,handupStudentJsonList);
	}
	
	private StudentResponse getStudentResponse(Student student) {
		StudentJson studentJson =StudentJsonHelper.getStudentJson(student);

		List<Student> handupStudentList = WebsocketForTA.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList =StudentJsonHelper.getStundentJsonListForStudent(handupStudentList,clientIpAddress);

		return new StudentResponse(studentJson,handupStudentJsonList);
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

	private void broadcastMessageForTa(String message) {
		for (Session client : WebsocketForTA.taSessions) {
			try {
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				WebsocketForTA.taSessions.remove(client); // 送信失敗したセッションは削除
			}
		}
	}
	
	private void broadcastMessageForStudent(String message) {
		for (StudentSession studentSession : WebsocketForTA.studentSessions) {
			try {
				Session client = studentSession.getSession();
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				 WebsocketForTA.studentSessions.remove(studentSession); // 送信失敗したセッションは削除
			}
		}
	}
	

}
