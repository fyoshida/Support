package websocket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.entities.Student;
import json.JsonHelper;

public class MessageSender {
	private JsonHelper jsonHelper=new JsonHelper();

	// Ta・教員 全員にメッセージ送信
	public void broadcastStateForTa(Set<Session> sessionSet,List<Student> studentList,List<Student> handupStudentList) throws JsonProcessingException {

		String jsonText = jsonHelper.getJsonForTeacher(studentList, handupStudentList);
		
		for (Session client : sessionSet) {
			try {
				client.getBasicRemote().sendText(jsonText); // 各クライアントにメッセージを送信
			} catch (IOException e) {
				e.printStackTrace();
				sessionSet.removeIf(s -> s.equals(client)); // 送信失敗したセッションは削除
			}
		}
	}
	

	// 接続している学生全員に現在の状態を送信
	public  void broadcastStateForStudents(Map<Student,Session> studentSessionMap,List<Student> studentList,List<Student>handupStudentList) throws JsonProcessingException {

		Map<Student,String> jsonMap=jsonHelper.getJsonListForStudent(studentSessionMap.keySet(), studentList,handupStudentList);

		for(Entry<Student, Session> entry : studentSessionMap.entrySet()) {
			Student student =entry.getKey();
			Session session = entry.getValue();
			String jsonText= jsonMap.get(student);
			try {
				session.getBasicRemote().sendText(jsonText);
			} catch (Exception e) {
				e.printStackTrace();
				studentSessionMap.entrySet().removeIf(ent -> session.equals(ent.getValue()));
			}

		}
	}

	// メッセージ送信
	public void sendMessage(Session session,String message) {
		try {
			session.getBasicRemote().sendText(message); // 非同期でメッセージを送信
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
