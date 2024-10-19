package domain.services;

import static org.apache.commons.lang3.Validate.*;

import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import domain.aggregate.StudentManager;
import domain.aggregate.WaitingManager;
import domain.entities.Pc;
import domain.entities.Student;
import httpclient.IHttpClient;
import repository.IPcRepository;

public class StudentService {
	private StudentManager studentManager;
	private WaitingManager waitingManager;
	private IHttpClient httpClient;
	
	public StudentService(IPcRepository pcRepository,IHttpClient httpClient) {
		notNull(pcRepository);
		notNull(httpClient);
		this.httpClient = httpClient;
		
		List<Pc> pcList = pcRepository.getPcList(); 
		this.studentManager=new StudentManager(pcList);
		this.waitingManager =new WaitingManager();
	}
	
	public Optional<Student> getClientStudent() {
		Optional<InetAddress> optIpAddress = httpClient.getClientIpAddress();
		if(optIpAddress.isEmpty()) {
			return Optional.empty();
		}
		InetAddress ipAddress= optIpAddress.get();
		return studentManager.getStudent(ipAddress);

	}

	private List<Student> addPriority(List<Student> studentList){
		for (Student student : studentList) {
			InetAddress ipAddress = student.getPc().getIpAddress();
			int priority = waitingManager.getPriority(ipAddress);
			student.setPriority(priority);
		}
		return Collections.unmodifiableList(studentList);
	}
	
	public List<Student> getStudentList() {
		List<Student> studentList = studentManager.getStudentList();
		return addPriority(studentList);
	}

	public List<Student> getHandUpStudent() {
		List<Student> studentList = studentManager.getHandUpStudentList();
		return addPriority(studentList);
	}
	
	public void clientHandUp() {
		Optional<InetAddress> optIpAddress = httpClient.getClientIpAddress();
		if(optIpAddress.isEmpty()) {
			return;
		}
		InetAddress ipAddress= optIpAddress.get();
		studentManager.handUp(ipAddress);
		waitingManager.regist(ipAddress);
	}

	public void clientHandDown() {
		Optional<InetAddress> optIpAddress = httpClient.getClientIpAddress();
		if(optIpAddress.isEmpty()) {
			return;
		}
		InetAddress ipAddress= optIpAddress.get();
		studentManager.handDown(ipAddress);
		waitingManager.unregist(ipAddress);
	}

	public void clientSupported() {
		Optional<InetAddress> optIpAddress = httpClient.getClientIpAddress();
		if(optIpAddress.isEmpty()) {
			return;
		}
		InetAddress ipAddress= optIpAddress.get();
		studentManager.supported(ipAddress);
		waitingManager.unregist(ipAddress);
	}

}
