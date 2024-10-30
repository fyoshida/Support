package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import helper.ResponseHelper;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket", configurator = ConfiguratorWithRequest.class)
public class WebsocketForStudent {

	private IpAddress clientIpAddress;
	private Student student;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws JsonProcessingException {
	
		String strIpAddress = (String) config.getUserProperties().get("IpAddress");
		clientIpAddress=new IpAddress(strIpAddress);
		clientIpAddress=new IpAddress("133.44.118.158");

		Optional<Student> optStudent = WebsocketForTA.studentManager.getStudent(clientIpAddress);
		if (optStudent.isEmpty()) {
			return ;
		}
		student = optStudent.get();
		
		WebsocketForTA.studentSessionMap.put(student, session);

		String jsonText = ResponseHelper.getJsonForStudent(student,WebsocketForTA.studentManager.getHandUpStudentList());
		sendMessage(session,jsonText);
	}
	
	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
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

		broadcastStateForTa();
		broadcastStateForStudents();
		
		return 	ResponseHelper.getJsonForStudent(student,WebsocketForTA.studentManager.getHandUpStudentList());

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

	private void broadcastStateForTa() throws JsonProcessingException {
		List<Student> studentList=WebsocketForTA.studentManager.getStudentList();
		List<Student> handupStudentList=WebsocketForTA.studentManager.getHandUpStudentList();

		String taBroadcastJson = ResponseHelper.getJsonForTeacher(studentList, handupStudentList);
		broadcastMessageForTa(taBroadcastJson);
	}

	
	private void broadcastStateForStudents() throws JsonProcessingException {
		List<Student> handupStudentList=WebsocketForTA.studentManager.getHandUpStudentList();

		Map<Student,String> jsonMap=ResponseHelper.getJsonListForStudent(WebsocketForTA.studentSessionMap.keySet(), handupStudentList);
		for(Entry<Student,String> entry: jsonMap.entrySet()) {
			Student student = entry.getKey();
			String json = entry.getValue();
			Session session = WebsocketForTA.studentSessionMap.get(student);
			sendMessage(session,json);
		}
	}

	
	private void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
		} catch (Exception e) {
			e.printStackTrace();
			WebsocketForTA.taSessions.removeIf( client -> client.equals(session));
			WebsocketForTA.studentSessionMap.entrySet().removeIf(entry -> session.equals(entry.getValue()));
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
	

}
