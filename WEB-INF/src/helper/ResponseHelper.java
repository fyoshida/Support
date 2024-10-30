package helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;

import domain.entities.Student;
import domain.valueobjects.IpAddress;

public class ResponseHelper {

	public static String getJsonForTeacher(List<Student> studentList, List<Student> handupStudentList)
			throws JsonProcessingException {
		TeacherResponse teacherResponse = new TeacherResponse();

		teacherResponse.studentList = StudentJsonHelper.getStudentJsonList(studentList);
		teacherResponse.handupStudentList = StudentJsonHelper.getStudentJsonList(handupStudentList);

		return JsonConverter.getJsonText(teacherResponse);
	}

	public static Map<Student, String> getJsonListForStudent(Set<Student> studentList,
			List<Student> handupStudentList) throws JsonProcessingException {
			
		Map<Student, String> jsonMap = new HashMap<Student, String>();

		for (Student student : studentList) {
			String jsonText = getJsonForStudent(student,handupStudentList);
			jsonMap.put(student, jsonText);
		}
		return jsonMap;
	}

	public static String getJsonForStudent(Student student, List<Student> handupStudentList)
			throws JsonProcessingException {

		IpAddress ipAddress = student.getPc().getIpAddress();

		StudentResponse studentResponse = new StudentResponse();

		studentResponse.clientStudent = StudentJsonHelper.getStudentJson(student);
		studentResponse.handupStudentList = StudentJsonHelper.getStundentJsonListForStudent(handupStudentList,
				ipAddress);

		return JsonConverter.getJsonText(studentResponse);
	}

}
