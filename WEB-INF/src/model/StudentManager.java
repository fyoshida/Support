package model;

import static org.apache.commons.lang3.Validate.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import _old.model.HostName;
import _old.model.IpAddress;

public class StudentManager {

	private final Map<String, Student> pcIpAddressMap = new LinkedHashMap<String, Student>();
	private final Map<String, Student> pcHostNameMap = new LinkedHashMap<String, Student>();
	private final WaitingManager<String> waitingManager=new WaitingManager<String>();

	public StudentManager(List<Pc> pcList) {
		notNull(pcList);
		for (Pc pc : pcList) {
			if (pc.isStudent()) {
				Student student = new Student(pc.getIpAddress(), pc.getHostName(), pc.isStudent());
				pcIpAddressMap.put(pc.getIpAddress(), student);
				pcHostNameMap.put(pc.getHostName(), student);
			}
		}
	}

	public List<Student> getStudentList() {
		List<Student> studentList = new LinkedList<Student>();

		for (Student student : pcIpAddressMap.values()) {
			studentList.add(student);
		}

		return studentList;
	}
//
//	public List<Student> getHandUpStudent() {
//		List<Student> pcList = new LinkedList<Student>();
//
//		for (Student student : pcIpAddressMap.values()) {
//			switch (student.getHelpStatus()) {
//			case None:
//				break;
//			default:
//				pcList.add(student);
//			}
//		}
//
//		return pcList;
//	}

	public void handUp(String ipAddress) {
		Student student=findStudentByIpAddress(ipAddress);
		student.handUp();
		sehelpStatus = HelpStatus.Troubled;
		handUpTime = LocalDateTime.now();
		waitingManager.regist(ipAddress);
	}

	public void supported(String ipAddress) {
		helpStatus = HelpStatus.Supporting;
		handUpTime = null;
		waitingManager.unregist(ipAddress);
	}

	public void handDown(String ipAddress) {
		helpStatus = HelpStatus.None;
		handUpTime = null;
		waitingManager.unregist(ipAddress);
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
