package domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.WaitingManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.IpAddress;
import httpclient.DummyHttpClient;
import repository.IPcRepository;
import repository.RepositoryFactory;
import services.StudentService;

public class StudentServiceTest {
	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String HOSTNAME_GATEWAY = "ics";

	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String HOSTNAME_1 = "ics801";

	public static final String IPADDRESS_2 = "133.44.118.188";
	public static final String HOSTNAME_2 = "ics831";

	public static final String IPADDRESS_3 = "133.44.118.228";
	public static final String HOSTNAME_3 = "ics871";

	private IpAddress ipAddressGateWay;
	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;
	private IpAddress ipAddress1_another;
	private IpAddress ipAddress2_another;
	private IpAddress ipAddress3_another;
	private Pc pcGateway;
	private Pc pc1;
	private Pc pc2;
	private Pc pc3;

	private DummyHttpClient httpClient;

	private StudentService studentService;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(IPADDRESS_GATEWAY);
		ipAddress1 = new IpAddress(IPADDRESS_1);
		ipAddress2 = new IpAddress(IPADDRESS_2);
		ipAddress3 = new IpAddress(IPADDRESS_3);

		ipAddress1_another = new IpAddress(IPADDRESS_1);
		ipAddress2_another = new IpAddress(IPADDRESS_2);
		ipAddress3_another = new IpAddress(IPADDRESS_3);

		pcGateway = new Pc(ipAddressGateWay, HOSTNAME_GATEWAY);
		pc1 = new Pc(ipAddress1, HOSTNAME_1);
		pc2 = new Pc(ipAddress2, HOSTNAME_2);
		pc3 = new Pc(ipAddress3, HOSTNAME_3);

		IPcRepository pcRepository = RepositoryFactory.getRepository();
		httpClient = new DummyHttpClient();
		studentService = new StudentService(pcRepository, httpClient);
	}

	@Test
	public void StudentListを取得できる() {
		List<Student> studentList = studentService.getStudentList();

		assertEquals(71, studentList.size());
	}

	@Test
	public void 手を上げている学生のListを取得できる() {
		httpClient.setIpAddress(ipAddress1);
		studentService.clientHandUp();

		httpClient.setIpAddress(ipAddress3);
		studentService.clientHandUp();

		List<Student> studentList = studentService.getHandUpStudent();

		assertEquals(2, studentList.size());
		Student student0 = studentList.get(0);
		Student student1 = studentList.get(1);
		assertEquals(ipAddress1, student0.getPc().getIpAddress());
		assertEquals(ipAddress3, student1.getPc().getIpAddress());
		assertEquals(1, student0.getPriority());
		assertEquals(2, student1.getPriority());
	}

	@Test
	public void 手を上げられる() {
		httpClient.setIpAddress(ipAddress2);
		studentService.clientHandUp();

		Student student = studentService.getClientStudent().get();

		assertEquals(ipAddress2, student.getPc().getIpAddress());
		assertEquals(1, student.getPriority());
		assertTrue(student.getWaitingTimeBySecond(LocalDateTime.now()) >= 0);
	}
	
	@Test
	public void 手を下げられる() {
		httpClient.setIpAddress(ipAddress2);
		studentService.clientHandUp();
		studentService.clientHandDown();

		Student student = studentService.getClientStudent().get();

		assertEquals(ipAddress2, student.getPc().getIpAddress());
		assertEquals(WaitingManager.NOT_REGISTED, student.getPriority());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()) );
	}
	
	@Test
	public void サポートできる() {
		httpClient.setIpAddress(ipAddress2);
		studentService.clientHandUp();
		studentService.clientSupporting();

		Student student = studentService.getClientStudent().get();

		assertEquals(ipAddress2, student.getPc().getIpAddress());
		assertEquals(WaitingManager.NOT_REGISTED, student.getPriority());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()) );
	}

	//	@Test
	//	public void StudentListを取得できる() {
	//		httpClient.setIpAddress(ipAddress2);
	//
	//		studentManager.handUp(ipAddress1);
	//
	//		Optional<Student> student = studentManager.getStudent(ipAddress1);
	//		studentManager.assertEquals(student.getPriority(),1);
	//
	//		student.supporting();
	//
	//		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);
	//
	//		student.handDown();
	//
	//		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);
	//	}

}
