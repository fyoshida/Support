package websocket;

import java.io.IOException;
import java.util.List;
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
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;
import repository.IPcRepository;
import repository.RepositoryFactory;
import repository.RepositoryType;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_administrator", configurator = ConfiguratorWithRequest.class)
public class WebsocketForTA {

	public static Set<Session> taSessions = new CopyOnWriteArraySet<>(); // セッションのセット
	public static Set<StudentSession> studentSessions = new CopyOnWriteArraySet<>(); // セッションのセット
	public static StudentManager studentManager;

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) throws IOException {
		String userType="";
		if(config.getUserProperties().get("UserType") !=null) {
			userType = (String) config.getUserProperties().get("UserType");
		}
		userType="TA";
		if (userType.equals("Administrator") || userType.equals("Teacher") || userType.equals("TA")) {
			taSessions.add(session);
		} else {
			session.close();
			return;
		}

		// リポジトリを取得
		IPcRepository repository = RepositoryFactory.getRepository(RepositoryType.Memory);

		// StudentManagerを生成
		List<Pc> pcList = repository.getPcList();
		studentManager = new StudentManager(pcList);

		TeacherResponse teacherResponse = getTeacherResponbse();
		String jsonText = getJsonText(teacherResponse);
		sendMessage(session, jsonText);
	}

	@OnMessage
	public String onMessage(String message, Session session) {
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

		for (StudentSession studentSession : studentSessions) {
			Student student = studentSession.getStudent();
			Session client = studentSession.getSession();
			StudentResponse studentResponse = getStudentResponse(student);
			String jsonText = getJsonText(studentResponse);
			sendMessage(client,jsonText);
		}

		TeacherResponse teacherResponse = getTeacherResponbse();
		String jsonTextTa = getJsonText(teacherResponse);
		broadcastMessageForTa(jsonTextTa);

		return jsonTextTa;
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

	private TeacherResponse getTeacherResponbse() {
		List<Student> studentList = WebsocketForTA.studentManager.getStudentList();
		List<StudentJson> studentJsonList = StudentJsonHelper.getStudentJsonList(studentList);

		List<Student> handupStudentList = WebsocketForTA.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList = StudentJsonHelper.getStudentJsonList(handupStudentList);

		return new TeacherResponse(studentJsonList, handupStudentJsonList);
	}

	private StudentResponse getStudentResponse(Student student) {
		StudentJson studentJson = StudentJsonHelper.getStudentJson(student);
		
		List<Student> handupStudentList = WebsocketForTA.studentManager.getHandUpStudentList();
		List<StudentJson> handupStudentJsonList = StudentJsonHelper.getStundentJsonListForStudent(handupStudentList,
				student.getPc().getIpAddress());

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

	private void broadcastMessageForStudent(String message) {
		for (StudentSession studentSession : studentSessions) {
			try {
				Session client = studentSession.getSession();
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				studentSessions.remove(studentSession); // 送信失敗したセッションは削除
			}
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
