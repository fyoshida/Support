package model;

import static org.apache.commons.lang3.Validate.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentManager {

	private final WaitingManager<Student> waitingManager;
	private final Map<IpAddress, Student> pcIpAddressMap = new LinkedHashMap<IpAddress, Student>();
	private final Map<HostName, Student> pcHostNameMap = new LinkedHashMap<HostName, Student>();

	public StudentManager(List<Pc> pcList) {
		waitingManager = new WaitingManager<Student>();
		notNull(pcList);
		for (Pc pc : pcList) {
			Student student = new Student(pc,waitingManager);
			if (pc.isStudent()) {
				pcIpAddressMap.put(pc.getIpAddress(), student);
				pcHostNameMap.put(pc.getHostName(), student);
			}
		}
	}

	public List<Student> getStudentList() {
		List<Student> studentList = new LinkedList<Student>();

		for(Student student : pcIpAddressMap.values()) {
			studentList.add(student);
		}

		return studentList;
	}

	public List<Student> getHandUpStudent() {
		List<Student> pcList = new LinkedList<Student>();

		for (Student student : pcIpAddressMap.values()) {
			switch (student.getHelpStatus()) {
			case None:
				break;
			default:
				pcList.add(student);
			}
		}

		return pcList;
	}

	public Student findStudentByIpAddress(String ipAddress) {
		return pcIpAddressMap.get(new IpAddress(ipAddress));
	}

	public Student findStudentByHostName(String hostName) {
		return pcHostNameMap.get(new HostName(hostName));
	}

	public boolean existStudentByIpAddress(String ipAddress) {
		return pcIpAddressMap.containsKey(new IpAddress(ipAddress));
	}

	public boolean existStudentByHostName(String hostName) {
		return pcHostNameMap.containsKey(new HostName(hostName));
	}

}
