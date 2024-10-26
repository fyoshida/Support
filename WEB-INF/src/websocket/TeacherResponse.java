package websocket;

import java.util.List;

import helper.StudentJson;

public class TeacherResponse {
	private List<StudentJson> handupStudentList;
	private List<StudentJson> studentList;

	public TeacherResponse(List<StudentJson> studentList,List<StudentJson> handupStudentList) {
		this.handupStudentList = handupStudentList;
		this.studentList = studentList;
	}

	public List<StudentJson> getHandupStudentList() {
		return handupStudentList;
	}

	public List<StudentJson> getStudentList() {
		return studentList;
	}

}
