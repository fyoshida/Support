package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import domain.entities.Student;
import httpclient.HttpClientFactory;
import httpclient.IHttpClient;
import repository.IPcRepository;
import repository.RepositoryFactory;
import services.StudentService;

//@ServerEndpoint(value="/websocket/_initialize")
@ServerEndpoint(value="/websocket/_initialize", configurator = WebsocketInitialize.ConfiguratorWithRequest.class)
public class WebsocketInitialize {

    private static Set<Session> clientSessions = new CopyOnWriteArraySet<>(); // セッションのセット
	private static StudentService studentService;
    private HttpSession httpSession;
    private String clientIp;
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
    	clientSessions.add(session);
        clientIp = (String) config.getUserProperties().get("IpAddress");

        System.out.println("WebSocket connection opened: " + clientIp);

        // リポジトリを取得
		IPcRepository repository=RepositoryFactory.getRepository();
		IHttpClient httpClient = HttpClientFactory.getNetwork(clientIp);

		// StudentManagerを生成
		studentService=new StudentService(repository,httpClient);
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("Received message: " + message + " from client: " + session.getId());
        Optional<Student> optStudent = studentService.getClientStudent();
        if(optStudent.isEmpty()) {
        	return "";
        }
        Student student = optStudent.get();

        sendMessage(session,"aaa");
        return "Echo: " + message;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket connection closed: " + session.getId());
    }
    
    @OnError
    public void onError(final Session client, final Throwable error) {
        String log = client.getId() + " was error. [" + error.getMessage() + "]";
        error.printStackTrace();
    }    
   
    // Configuratorクラスの設定
    public static class ConfiguratorWithRequest extends Configurator {
        @Override
        public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {

            Map<String, List<String>> params = request.getParameterMap();
            if (params.containsKey("ip")) {
                String ipAddress = params.get("ip").get(0);
                config.getUserProperties().put("IpAddress", ipAddress);
            }

        }
    }
    
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void broadcastMessage(String message) {
        for (Session client : clientSessions) {
            try {
                client.getBasicRemote().sendText(message); // 各クライアントにメッセージを送信
            } catch (IOException e) {
                e.printStackTrace();
                clientSessions.remove(client); // 送信失敗したセッションは削除
            }
        }
    }
   
}
