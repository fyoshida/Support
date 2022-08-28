package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentManager {

	private final Map<IpAddress, Student> pcIpAddressMap = new HashMap<IpAddress, Student>();
	private final Map<String, Student> pcHostNameMap = new HashMap<String, Student>();
	private final WaitingManager waitingManager = new WaitingManager();

	public StudentManager(List<Pc> pcList) {
		for (Pc pc : pcList) {
			Student student = new Student(pc, waitingManager);
			if (pc.isStudent()) {
				pcIpAddressMap.put(pc.getIpAddress(), student);
				pcHostNameMap.put(pc.getHostName(), student);
			}
		}
	}

	public List<Student> getPcList() {
		return (List<Student>) pcIpAddressMap.values();
	}

	public Student getStudent(IpAddress ipAddress) {
		return pcIpAddressMap.get(ipAddress);
	}

	public Student getStudent(String hostName) {
		return pcHostNameMap.get(hostName);
	}

	public boolean existStudent(IpAddress ipAddress) {
		return pcIpAddressMap.containsKey(ipAddress);
	}

	public boolean existStudent(String hostName) {
		return pcHostNameMap.containsKey(hostName);
	}

	public List<Student> getHandUpStudent() {
		List<Student> pcList = new LinkedList<Student>();

		for (Student pc : pcIpAddressMap.values()) {
			switch (pc.getHelpStatus()) {
			case None:
				break;
			default:
				pcList.add(pc);
			}
		}

		return pcList;
	}


}
