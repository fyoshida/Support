package model;

import java.util.LinkedList;
import java.util.List;

public class StudentManager {
	private List<Student> studentList= new LinkedList<Student>();

	public void addStudent(Student student) {
		studentList.add(student);
	}
	public int getHandPriority(Student student) {
		return handPriority;
	}

}
