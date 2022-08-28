package servlet.helper;

import java.util.LinkedList;
import java.util.List;

import model.Student;

public class PcJsonConverter {

	public static PcJson getPcJson(Student student) {
		PcJson pcJson=new PcJson();

		pcJson.setPcId(student.getPc().getHostName());
		pcJson.setIpAdress(student.getPc().getIpAddress().get());
		pcJson.setIsStudent(student.getPc().isStudent());
		pcJson.setHelpStatus(student.getHelpStatus().toString());
		pcJson.setHandPriority(student.getPriority());
		pcJson.setIsLogin(false);

		return pcJson;
	}

	public static List<PcJson> getPcJson(List<Student> pcList) {
		List<PcJson> pcJsonList = new LinkedList<PcJson>();

		for(Student pc : pcList) {
			PcJson pcJson = getPcJson(pc);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}
}
