package websocket;

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

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws JsonProcessingException {
	
		String strIpAddress = (String) config.getUserProperties().get("IpAddress");
		clientIpAddress=new IpAddress(strIpAddress);
		clientIpAddress=new IpAddress("133.44.118.158"); // 一時的に設定

		Optional<Student> optStudent = WebsocketForAdministrator.studentManager.getStudent(clientIpAddress);
		if (optStudent.isEmpty()) {
			return ;
		}
		student = optStudent.get();
		
		WebsocketForAdministrator.studentSessionMap.put(student, session);

		String jsonText = JsonHelper.getJsonForStudent(student,WebsocketForAdministrator.studentManager.getHandUpStudentList());
		WebsocketForAdministrator.sendMessage(session,jsonText);
	}
	
	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
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

		WebsocketForAdministrator.broadcastStateForTa();
		WebsocketForAdministrator.broadcastStateForStudents();
		
		return 	JsonHelper.getJsonForStudent(student,WebsocketForAdministrator.studentManager.getHandUpStudentList());

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

}
