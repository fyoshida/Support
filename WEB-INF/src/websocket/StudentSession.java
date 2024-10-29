package websocket;

import javax.websocket.Session;

import domain.entities.Student;

public class StudentSession {
	private Student student;
	private Session session;

	public StudentSession(Student student, Session session) {
		this.student = student;
		this.session = session;
	}

	public Student getStudent() {
		return student;
	}

	public Session getSession() {
		return session;
	}
}
