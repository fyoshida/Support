package domain.aggregate;

import static org.apache.commons.lang3.Validate.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.IpAddress;

public class StudentManager {

	private List<Student> studentList = new ArrayList<Student>();
	private WaitingManager waitingManager;

	public StudentManager(List<Pc> pcList) {
		notNull(pcList);
		for (Pc pc : pcList) {
			Student student = new Student(pc);
			studentList.add(student);
		}
		waitingManager = new WaitingManager();
	}

	private Student addPriority(Student student) {
		IpAddress ipAddress = student.getPc().getIpAddress();
		int priority = waitingManager.getPriority(student);
		student.setPriority(priority);
		return student; 
	}

	private List<Student> addPriority(List<Student> studentList) {
		return studentList.stream().map(s->addPriority(s)).toList();
	}

	public List<Student> getStudentList() {
		return Collections.unmodifiableList(addPriority(studentList));
	}

	public List<Student> getHandUpStudentList() {
		return Collections.unmodifiableList(addPriority(waitingManager.getAll()));
	}
	
	public Optional<Student> getStudent(IpAddress ipAddress) {
		Optional<Student> optStudent =studentList.stream().filter(s -> s.getPc().getIpAddress().equals(ipAddress)).findFirst();
		if(optStudent.isEmpty()) {
			return Optional.empty();
		}
		Student student=addPriority(optStudent.get());
		return Optional.of(student);
	}

	public boolean existStudent(IpAddress ipAddress) {
		return getStudent(ipAddress).isPresent();
	}

	public void handUp(IpAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.handUp();
		waitingManager.regist(student);
	}

	public void handDown(IpAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.handDown();
		waitingManager.unregist(student);
	}

	public void supporting(IpAddress ipAddress) {
		Optional<Student> optStudent = getStudent(ipAddress);
		if (optStudent.isEmpty()) {
			return;
		}
		Student student = optStudent.get();
		student.supporting();
		waitingManager.unregist(student);
	}

}
