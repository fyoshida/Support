package services;

import static org.apache.commons.lang3.Validate.*;

import java.util.List;
import java.util.Optional;

import domain.aggregate.StudentManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.IpAddress;
import httpclient.IHttpClient;
import repository.IPcRepository;

public class StudentService {
	private StudentManager studentManager;
	private IHttpClient httpClient;
	
	public StudentService(IPcRepository pcRepository,IHttpClient httpClient) {
		notNull(pcRepository);
		notNull(httpClient);
		this.httpClient = httpClient;
		
		List<Pc> pcList = pcRepository.getPcList(); 
		this.studentManager=new StudentManager(pcList);
	}
	
	public Optional<Student> getClientStudent() {
		IpAddress ipAddress = httpClient.getClientIpAddress();
		
		Optional<Student> optStudent =studentManager.getStudent(ipAddress);
		if(optStudent.isEmpty()) {
			return Optional.empty();
		}
		Student student = optStudent.get();
		return Optional.of(student);
	}


	public List<Student> getStudentList() {
		return studentManager.getStudentList();
	}

	public List<Student> getHandUpStudent() {
		return studentManager.getHandUpStudentList();
	}
	
	public void clientHandUp() {
		IpAddress ipAddress= httpClient.getClientIpAddress();
		studentManager.handUp(ipAddress);
	}

	public void clientHandDown() {
		IpAddress ipAddress= httpClient.getClientIpAddress();
		studentManager.handDown(ipAddress);
	}

	public void clientSupporting() {
		IpAddress ipAddress= httpClient.getClientIpAddress();
		studentManager.supporting(ipAddress);
	}

}
