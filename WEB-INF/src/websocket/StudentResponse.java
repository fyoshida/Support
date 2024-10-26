package websocket;

import java.util.List;

import helper.StudentJson;

public class StudentResponse {
	private StudentJson clientStudent;
	private List<StudentJson> studentList;

	public StudentResponse(StudentJson clientStudent, List<StudentJson> studentList) {
		this.clientStudent = clientStudent;
		this.studentList = studentList;
	}

	public StudentJson getClientStudent() {
		return clientStudent;
	}

	public List<StudentJson> getStudentList() {
		return studentList;
	}


}
