package helper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import domain.entities.Student;
import domain.valueobjects.IpAddress;

public class StudentJsonHelper {

	public static StudentJson getStudentJson(Student student) {
		StudentJson pcJson = new StudentJson();
		pcJson.setPcId(student.getPc().getHostName());
		pcJson.setIpAdress(student.getPc().getIpAddress().getDisplayName());
		pcJson.setHelpStatus(student.getHelpStatus().getDisplayName());
		pcJson.setWaitingTimeBySecond(student.getWaitingTimeBySecond(LocalDateTime.now()));
		pcJson.setHandPriority(student.getPriority());
		return pcJson;
	}
	
	public static StudentJson getStudentJsonWithoutPcInfo(Student student) {
		StudentJson pcJson = new StudentJson();
		pcJson.setPcId("");
		pcJson.setIpAdress("");
		pcJson.setHelpStatus(student.getHelpStatus().getDisplayName());
		pcJson.setWaitingTimeBySecond(student.getWaitingTimeBySecond(LocalDateTime.now()));
		pcJson.setHandPriority(student.getPriority());
		return pcJson;
	}

	public static StudentJson getStudentJsonForStudent(Student student, IpAddress ipAddress) {
		if (student.getPc().getIpAddress().equals(ipAddress)) {
			return getStudentJson(student);
		} else {
			return getStudentJsonWithoutPcInfo(student);
		}
	}

	public static List<StudentJson> getStundentJsonListForStudent(List<Student> studentList, IpAddress ipAddress) {
		List<StudentJson> pcJsonList = new LinkedList<StudentJson>();

		for (Student student : studentList) {
			StudentJson pcJson = getStudentJsonForStudent(student,ipAddress);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

	public static List<StudentJson> getStudentJsonList(List<Student> studentList) {
		List<StudentJson> pcJsonList = new LinkedList<StudentJson>();

		for (Student student : studentList) {
			StudentJson pcJson = getStudentJson(student);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

//	public static StudentJson findPcJson(List<StudentJson> pcJsonList, String ipAddress) {
//		for (StudentJson pcJson : pcJsonList) {
//			if (pcJson.getIpAdress().equals(HttpClientFactory.ipAddress)) {
//				return pcJson;
//			}
//		}
//		return null;
//	}
}
