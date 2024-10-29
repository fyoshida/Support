package websocket;

import java.util.List;

import helper.StudentJson;

public class StudentResponse {
	private StudentJson clientStudent;
	private List<StudentJson> handupStudentList;

	public StudentResponse(StudentJson clientStudent, List<StudentJson> handupStudentList) {
		this.clientStudent = clientStudent;
		this.handupStudentList = handupStudentList;
	}

	public StudentJson getClientStudent() {
		return clientStudent;
	}

	public List<StudentJson> getHandupStudentList() {
		return handupStudentList;
	}


}
