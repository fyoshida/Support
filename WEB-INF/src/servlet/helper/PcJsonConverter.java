package servlet.helper;

import java.util.LinkedList;
import java.util.List;

import model.Student;

public class PcJsonConverter {

	public static PcJson getPcJson(Student pc) {
		PcJson pcJson=new PcJson();

		pcJson.setPcId(pc.getHostName());
		pcJson.setIpAdress(pc.getIpAddress().get());
		pcJson.setIsStudent(pc.isStudent());
		pcJson.setHelpStatus(pc.getHelpStatus().toString());
		pcJson.setHandPriority(pc.getPriority());
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
