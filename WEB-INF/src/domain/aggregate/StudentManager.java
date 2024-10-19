package domain.aggregate;

import static org.apache.commons.lang3.Validate.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.entities.Pc;
import domain.entities.Student;

public class StudentManager {

	private List<Student> studentList = new ArrayList<Student>();
	private WaitingManager waitingManager = new WaitingManager();

	public StudentManager(List<Pc> pcList) {
		notNull(pcList);
		for (Pc pc : pcList) {
			Student student = new Student(pc);
			studentList.add(student);
		}
	}

	public int getPriority(Student student) {
		InetAddress ipAddress = student.getPc().getIpAddress();
		return waitingManager.getPriority(ipAddress);
	}
	
	public List<Student> getStudentList() {
		List<Student> list = new ArrayList<Student>();
		for (Student student : studentList) {
			int priority = getPriority(student);
			student.setPriority(priority);
		}
		return Collections.unmodifiableList(list);
	}

	public List<Student> getHandUpStudent() {
		List<Student> list = new ArrayList<Student>();
		for (Student student : studentList) {
			if (student.isHandup()) {
				int priority = getPriority(student);
				student.setPriority(priority);
			}
		}
		return Collections.unmodifiableList(list);
	}

	public Optional<Student> getStudent(InetAddress ipAddress) {
		return studentList.stream().filter(s -> s.getPc().getIpAddress().equals(ipAddress)).findFirst();
	}

	public Optional<Student> getStudent(String hostName) {
		return studentList.stream().filter(s -> s.getPc().getHostName().equals(hostName)).findFirst();
	}
	
	public boolean existStudent(InetAddress ipAddress) {
		return getStudent(ipAddress).isPresent();
	}

	public boolean existStudent(String hostName) {
		return getStudent(hostName).isPresent();
	}

	public void handUp(InetAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.handUp();
		waitingManager.regist(ipAddress);
	}

	public void handDown(InetAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.handDown();
		waitingManager.unregist(ipAddress);
	}

	public void supported(InetAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.supporting();
		waitingManager.unregist(ipAddress);
	}

}
