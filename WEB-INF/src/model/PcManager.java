package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<IpAddress, Pc> pcMap = new HashMap<IpAddress, Pc>();

	public PcManager(List<PcBean> pcBeanList) {
		WaitingManager waitingManager = new WaitingManager();
		for (PcBean pcBean : pcBeanList) {
			Pc pc = convert(pcBean, waitingManager);
			pcMap.put(pc.getIpAddress(), pc);
		}
	}

	public List<Pc> getPcList() {
		return (List<Pc>) pcMap.values();
	}

	public Pc getPc(IpAddress ipAddress) {
		return pcMap.get(ipAddress);
	}

	public boolean existPc(IpAddress ipAddress) {
		return pcMap.containsKey(ipAddress);
	}

	public List<Pc> getHandUpPc() {
		List<Pc> pcList = new LinkedList<Pc>();

		for (Pc pc : pcMap.values()) {
			switch (pc.getHelpStatus()) {
			case None:
				break;
			default:
				pcList.add(pc);
			}
		}

		return pcList;
	}

	private Pc convert(PcBean pcBean, WaitingManager waitingManager) {
		IpAddress ipAddress = pcBean.getIpAddress();
		String hostName = pcBean.getHostName();
		boolean isStudent = pcBean.isStudent();

		Pc pc = new Pc(ipAddress, waitingManager);

		pc.setHostName(hostName);
		pc.setStudent(isStudent);

		return pc;
	}

}
