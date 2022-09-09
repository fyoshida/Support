package helper;

import java.util.LinkedList;
import java.util.List;

import model.Student;
import network.NetworkFactory;

public class PcJsonHelper {

	public static PcJson getPcJson(Student student) {
		PcJson pcJson = new PcJson();

		pcJson.setPcId(student.getPc().getHostName().get());
		pcJson.setIpAdress(student.getPc().getIpAddress().get());
		pcJson.setIsStudent(student.getPc().isStudent());
		pcJson.setHelpStatus(student.getHelpStatus().toString());
		pcJson.setHandPriority(student.getPriority());
		pcJson.setIsLogin(student.isLogin());

		return pcJson;
	}

	public static List<PcJson> getPcJson(List<Student> pcList) {
		List<PcJson> pcJsonList = new LinkedList<PcJson>();

		for (Student pc : pcList) {
			PcJson pcJson = getPcJson(pc);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}

	public static PcJson findPcJson(List<PcJson> pcJsonList,String ipAddress) {
		for(PcJson pcJson : pcJsonList) {
			if(pcJson.getIpAdress().equals(NetworkFactory.ipAddress)) {
				return pcJson;
			}
		}
		return null;
	}
}
