package model;

import static org.apache.commons.lang3.Validate.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StudentManager {

	private final Map<IpAddress, Student> pcIpAddressMap = new LinkedHashMap<IpAddress, Student>();
	private final Map<String, Student> pcHostNameMap = new LinkedHashMap<String, Student>();
	private final WaitingManager waitingManager = new WaitingManager();

	public StudentManager(List<Pc> pcList) {
		notNull(pcList);
		for (Pc pc : pcList) {
			Student student = new Student(pc, waitingManager);
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




}
