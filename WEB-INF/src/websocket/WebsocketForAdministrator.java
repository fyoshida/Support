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
import json.JsonHelper;
import repository.IPcRepository;
import repository.RepositoryFactory;
import repository.RepositoryType;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_administrator", configurator = ConfiguratorWithRequest.class)
public class WebsocketForAdministrator {

	public static Set<Session> taSessions = new CopyOnWriteArraySet<>(); // セッションのセット
	public static Map<Student,Session> studentSessionMap = new HashMap <Student,Session>(); // セッションのセット
	public static StudentManager studentManager;

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
		
		// リポジトリを取得
		IPcRepository repository = RepositoryFactory.getRepository(RepositoryType.Memory);

		// StudentManagerを生成
		List<Pc> pcList = repository.getPcList();
		studentManager = new StudentManager(pcList);

	}

	@OnMessage
	public String onMessage(String message, Session session){
		return "";
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("WebSocket connection closed: " + session.getId());
	}

	@OnError
	public void onError(final Session client, final Throwable error) {
		error.printStackTrace();
	}

	// メッセージ送信
	public static void sendMessage(Session session, String message) {
		try {
			session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
		} catch (Exception e) {
			e.printStackTrace();
			
			taSessions.removeIf( client -> client.equals(session));
			studentSessionMap.entrySet().removeIf(entry -> session.equals(entry.getValue()));
		}
	}

	// Ta・教員 全員にメッセージ送信
	private static void broadcastMessageForTa(String message) {
		for (Session client : taSessions) {
			try {
				client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				taSessions.remove(client); // 送信失敗したセッションは削除
			}
		}
	}
	
	// Ta・教員 全員に現在の状態を送信
	public static void broadcastStateForTa() throws JsonProcessingException {
		List<Student> studentList=studentManager.getStudentList();
		List<Student> handupStudentList=studentManager.getHandUpStudentList();

		String taBroadcastJson = JsonHelper.getJsonForTeacher(studentList, handupStudentList);
		broadcastMessageForTa(taBroadcastJson);
	}
	
	// 接続している学生全員に現在の状態を送信
	public static void broadcastStateForStudents() throws JsonProcessingException {
		List<Student> studentList=studentManager.getStudentList();
		List<Student> handupStudentList=studentManager.getHandUpStudentList();

		Map<Student,String> jsonMap=JsonHelper.getJsonListForStudent(studentSessionMap.keySet(), studentList,handupStudentList);
		jsonMap.forEach((student,json) ->sendMessage(studentSessionMap.get(student),json));
	}


}
