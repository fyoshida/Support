package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<IpAddress, Pc> pcMap = new HashMap<IpAddress, Pc>();

	public PcManager(List<Pc> pcList) {
		for (Pc pc : pcList) {
			pcMap.put(pc.getIpAddress(), pc);
		}
	}

	public Pc getPc(IpAddress ipAddress) {
		return pcMap.get(ipAddress);
	}

	public List<Pc> getPcList(){
		return (List<Pc>)pcMap.values();
	}

	public Pc getPcFromIpAddress(IpAddress ipAddress) {
		return pcMap.get(ipAddress);
	}

	public List<Pc> getHandUpPc() {
		List<Pc> pcList = new LinkedList<Pc>();

		for(Pc pc : pcMap.values()) {
			switch(pc.getHelpStatus()) {
			case None:
				break;
				default:
					pcList.add(pc);
			}
		}

		return pcList;
	}

}
