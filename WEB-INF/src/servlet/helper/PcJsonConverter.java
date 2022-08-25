package servlet.helper;

import java.util.LinkedList;
import java.util.List;

import model.Pc;

public class PcJsonConverter {

	public static PcJson getPcJson(Pc pc) {
		PcJson pcJson=new PcJson();

		pcJson.setPcId(pc.getHostName());
		pcJson.setIpAdress(pc.getIpAddress().get());
		pcJson.setIsStudent(pc.isStudent());
		pcJson.setHelpStatus(pc.getHelpStatus().toString());
		pcJson.setHandPriority(pc.getPriority());
		pcJson.setIsLogin(false);

		return pcJson;
	}

	public static List<PcJson> getPcJson(List<Pc> pcList) {
		List<PcJson> pcJsonList = new LinkedList<PcJson>();

		for(Pc pc : pcList) {
			PcJson pcJson = getPcJson(pc);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}
}
