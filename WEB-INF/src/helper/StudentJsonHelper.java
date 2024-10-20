package helper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import domain.entities.Student;
import domain.valueobjects.IpAddress;
import httpclient.HttpClientFactory;

public class StudentJsonHelper {

	public static StudentJson fromStudent(Student student) {
		StudentJson pcJson = new StudentJson();
		pcJson.setPcId(student.getPc().getHostName());
		pcJson.setIpAdress(student.getPc().getIpAddress().getDisplayName());
		pcJson.setHelpStatus(student.getHelpStatus().getDisplayName());
		pcJson.setWaitingTimeBySecond(student.getWaitingTimeBySecond(LocalDateTime.now()));
		pcJson.setHandPriority(student.getPriority());
		return pcJson;
	}
	
	public static StudentJson fromStudentWithoutPcInfo(Student student) {
		StudentJson pcJson = new StudentJson();
		pcJson.setPcId("");
		pcJson.setIpAdress("");
		pcJson.setHelpStatus(student.getHelpStatus().getDisplayName());
		pcJson.setWaitingTimeBySecond(student.getWaitingTimeBySecond(LocalDateTime.now()));
		pcJson.setHandPriority(student.getPriority());
		return pcJson;
	}

	public static StudentJson fromStudentForStudent(Student student, IpAddress ipAddress) {
		if (student.getPc().getIpAddress().equals(ipAddress)) {
			return fromStudent(student);
		} else {
			return fromStudentWithoutPcInfo(student);
		}
	}

	public static List<StudentJson> getPcJsonForStudent(List<Student> studentList, IpAddress ipAddress) {
		List<StudentJson> pcJsonList = new LinkedList<StudentJson>();

		for (Student student : studentList) {
			StudentJson pcJson = fromStudentForStudent(student,ipAddress);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

	public static List<StudentJson> getPcJson(List<Student> studentList) {
		List<StudentJson> pcJsonList = new LinkedList<StudentJson>();

		for (Student student : studentList) {
			StudentJson pcJson = fromStudent(student);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

	public static StudentJson findPcJson(List<StudentJson> pcJsonList, String ipAddress) {
		for (StudentJson pcJson : pcJsonList) {
			if (pcJson.getIpAdress().equals(HttpClientFactory.ipAddress)) {
				return pcJson;
			}
		}
		return null;
	}
}
