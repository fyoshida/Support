package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PcManager {

	private final Map<IpAddress, Pc> pcIpAddressMap = new HashMap<IpAddress, Pc>();
	private final Map<String, Pc> pcHostNameMap = new HashMap<String, Pc>();
	private final WaitingManager waitingManager = new WaitingManager();

	public PcManager(List<PcBean> pcBeanList) {
		for (PcBean pcBean : pcBeanList) {
			Pc pc = convert(pcBean, waitingManager);
			pcIpAddressMap.put(pc.getIpAddress(), pc);
			pcHostNameMap.put(pc.getHostName(), pc);
		}
	}

	public List<Pc> getPcList() {
		return (List<Pc>) pcIpAddressMap.values();
	}

	public Pc getPc(IpAddress ipAddress) {
		return pcIpAddressMap.get(ipAddress);
	}

	public Pc getPc(String hostName) {
		return pcHostNameMap.get(hostName);
	}

	public boolean existPc(IpAddress ipAddress) {
		return pcIpAddressMap.containsKey(ipAddress);
	}

	public boolean existPc(String hostName) {
		return pcHostNameMap.containsKey(hostName);
	}

	public List<Pc> getHandUpPc() {
		List<Pc> pcList = new LinkedList<Pc>();

		for (Pc pc : pcIpAddressMap.values()) {
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
