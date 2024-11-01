package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import domain.aggregate.StudentManager;
import domain.entities.Pc;
import domain.entities.Student;
import repository.IPcRepository;
import repository.RepositoryFactory;
import repository.RepositoryType;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value = "/websocket/_administrator", configurator = ConfiguratorWithRequest.class)
public class WebsocketForAdministrator {

	public static Set<Session> taSessionSet = new CopyOnWriteArraySet<Session>(); // セッションのセット
	public static Map<Student,Session> studentSessionMap = new ConcurrentHashMap <Student,Session>(); // セッションのセット
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

}
