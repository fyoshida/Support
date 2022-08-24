package servlet.helper;

import java.util.LinkedList;
import java.util.List;

import model.Pc;

public class PcJsonConverter {

	public static PcJson getPcJsonFromPc(Pc pc) {
		PcJson pcJson=new PcJson();

		pcJson.setPcId(pc.getHostName());
		pcJson.setIpAdress(pc.getIpAddress().get());
		pcJson.setIsStudent(pc.isStudent());
		pcJson.setHelpStatus(pc.getHelpStatus().toString());
		pcJson.setHandPriority(pc.getPriority());
		pcJson.setIsLogin(false);

		return pcJson;
	}

	public static List<PcJson> getPcJsonFromPcList(List<Pc> pcList) {
		List<PcJson> pcJsonList = new LinkedList<PcJson>();

		for(Pc pc : pcList) {
			PcJson pcJson = getPcJsonFromPc(pc);
			pcJsonList.add(pcJson);
		}

		return pcJsonList;
	}
}
