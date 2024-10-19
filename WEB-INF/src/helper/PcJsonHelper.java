package helper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import domain.entities.Student;
import httpclient.HttpClientFactory;

public class PcJsonHelper {

	public static PcJson fromStudent(Student student) {
		PcJson pcJson = new PcJson();

		pcJson.setPcId(student.getPc().getHostName());
		pcJson.setIpAdress(student.getPc().getIpAddress().toString());
		pcJson.setHelpStatus(student.getHelpStatus().getDisplayName());
		pcJson.setWaitingTimeBySecond(student.getWaitingTime(LocalDateTime.now()).toSeconds());
		pcJson.setHandPriority(student.getPriority());

		return pcJson;
	}

	public static List<PcJson> getPcJson(List<Student> studentList) {
		List<PcJson> pcJsonList = new LinkedList<PcJson>();

		for (Student student : studentList) {
			PcJson pcJson = fromStudent(student);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

	public static PcJson findPcJson(List<PcJson> pcJsonList, String ipAddress) {
		for (PcJson pcJson : pcJsonList) {
			if (pcJson.getIpAdress().equals(HttpClientFactory.ipAddress)) {
				return pcJson;
			}
		}
		return null;
	}
}
