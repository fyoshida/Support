package domain.aggregate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import domain.entities.Student;

public class WaitingManager {
	public static final int NOT_REGISTED = 999;

	private LinkedList<Student> waitingList = new LinkedList<Student>();

	public boolean isRegisted(Student student) {
		return waitingList.contains(student);
	}
	
	public void regist(Student student) {
		if (!isRegisted(student)) {
			waitingList.addLast(student);
		}
	}

	public void unregist(Student student) {
		waitingList.removeIf(s -> s.equals(student));
	}

	public List<Student> getAll() {
		List<Student> pcList = waitingList.stream().toList();
		return Collections.unmodifiableList(pcList);
	}

	public int getPriority(Student student) {
		int pos = waitingList.indexOf(student);
		if (pos < 0) {
			return NOT_REGISTED;
		}

		return pos + 1;
	}

	public void clrar() {
		waitingList.clear();
	}
}
