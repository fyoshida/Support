package websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.aggregate.StudentManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.IpAddress;
import helper.ResponseHelper;
import repository.IPcRepository;
import repository.RepositoryFactory;
import repository.RepositoryType;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_administrator", configurator = ConfiguratorWithRequest.class)
public class WebsocketForTA {

	public static Set<Session> taSessions = new CopyOnWriteArraySet<>(); // セッションのセット
	public static Map<Student,Session> studentSessionMap = new HashMap <Student,Session>(); // セッションのセット
	public static StudentManager studentManager;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		String userType="";
		if(config.getUserProperties().get("UserType") !=null) {
			userType = (String) config.getUserProperties().get("UserType");
		}
		
		userType="TA"; // 一時的に設定
		
		// Sessionをリストに保存
		if (userType.equals("Administrator") || userType.equals("Teacher") || userType.equals("TA")) {
			taSessions.add(session);
		} else {
			session.close();
			return;
		}

		//-------------------
		// 一時的に設定
		//-------------------

		// リポジトリを取得
		IPcRepository repository = RepositoryFactory.getRepository(RepositoryType.Memory);

		// StudentManagerを生成
		List<Pc> pcList = repository.getPcList();
		studentManager = new StudentManager(pcList);

		//-------------------
		// ここまで
		//-------------------

		String jsonText = ResponseHelper.getJsonForTeacher(studentManager.getStudentList(), studentManager.getHandUpStudentList());
		sendMessage(session,jsonText);
	}

	@OnMessage
	public String onMessage(String message, Session session) throws JsonProcessingException {
		String[] messages = message.split(":");
		String command = messages[0];
		String ipAddress = messages[1];
		IpAddress clientIpAddress = new IpAddress(ipAddress);
		switch (command) {
		case "Initialize":
			IPcRepository repository = RepositoryFactory.getRepository(RepositoryType.Memory);
			List<Pc> pcList = repository.getPcList();
			studentManager = new StudentManager(pcList);
			break;
		case "Clear":
			studentManager = null;
		case "None":
			studentManager.handDown(clientIpAddress);
			break;
		case "Troubled":
			studentManager.handUp(clientIpAddress);
			break;
		case "Supporting":
			studentManager.supporting(clientIpAddress);
			break;
		}

		broadcastStateForStudents();
		broadcastStateForTa();

		return ResponseHelper.getJsonForTeacher(studentManager.getStudentList(), studentManager.getHandUpStudentList());
	}

	@OnClose
	public void onClose(Session session) {
		taSessions.remove(session);
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
		//		String log = client.getId() + " was error. [" + error.getMessage() + "]";
		error.printStackTrace();
	}

	private void broadcastStateForTa() throws JsonProcessingException {
		List<Student> studentList=studentManager.getStudentList();
		List<Student> handupStudentList=studentManager.getHandUpStudentList();

		String taBroadcastJson = ResponseHelper.getJsonForTeacher(studentList, handupStudentList);
		broadcastMessageForTa(taBroadcastJson);
	}

	
	private void broadcastStateForStudents() throws JsonProcessingException {
		List<Student> handupStudentList=studentManager.getHandUpStudentList();

		Map<Student,String> jsonMap=ResponseHelper.getJsonListForStudent(studentSessionMap.keySet(), handupStudentList);
		jsonMap.forEach((student,json) ->sendMessage(studentSessionMap.get(student),json));
	}

	
	private void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
		} catch (Exception e) {
			e.printStackTrace();
			
			taSessions.removeIf( client -> client.equals(session));
			studentSessionMap.entrySet().removeIf(entry -> session.equals(entry.getValue()));
		}
	}

	private void broadcastMessageForTa(String message) {
		for (Session client : taSessions) {
			try {
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				taSessions.remove(client); // 送信失敗したセッションは削除
			}
		}
	}

}
