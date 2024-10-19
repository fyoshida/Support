package domain.aggregate;

import static org.apache.commons.lang3.Validate.*;

import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.entities.Pc;
import domain.entities.Student;

public class StudentManager {

	private List<Student> studentList;

	public StudentManager(List<Pc> pcList) {
		notNull(pcList);
		for (Pc pc : pcList) {
			Student student = new Student(pc);
			studentList.add(student);
		}
	}

	public List<Student> getStudentList() {
		return Collections.unmodifiableList(studentList);
	}

	public List<Student> getHandUpStudentList() {
		List<Student> list = studentList.stream().filter(s->s.isHandup()).toList();
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
	}

	public void handDown(InetAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.handDown();
	}

	public void supported(InetAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.supporting();
	}

}
