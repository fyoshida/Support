package domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.StudentManager;
import domain.aggregate.WaitingManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.HelpStatus;
import domain.valueobjects.IpAddress;

public class StudentManagerTest {

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

	private StudentManager studentManager;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(IPADDRESS_GATEWAY);
		ipAddress1 = new IpAddress(IPADDRESS_1);
		ipAddress2 = new IpAddress(IPADDRESS_2);
		ipAddress3 = new IpAddress(IPADDRESS_3);

		ipAddress1_another = new IpAddress(IPADDRESS_1);
		ipAddress2_another = new IpAddress(IPADDRESS_2);
		ipAddress3_another = new IpAddress(IPADDRESS_3);

		pcGateway = new Pc(ipAddressGateWay,HOSTNAME_GATEWAY);
		pc1=new Pc(ipAddress1,HOSTNAME_1);
		pc2=new Pc(ipAddress2,HOSTNAME_2);
		pc3=new Pc(ipAddress3,HOSTNAME_3);

		List<Pc> pcList = new LinkedList<Pc>();
		pcList.add(pc1);
		pcList.add(pc2);
		pcList.add(pc3);

		studentManager = new StudentManager(pcList);
	}

	@Test
	public void IPアドレスでPCが登録されているか調べられる() {
		assertFalse(studentManager.existStudent(ipAddressGateWay));
		assertTrue(studentManager.existStudent(ipAddress1_another));
		assertTrue(studentManager.existStudent(ipAddress2_another));
		assertTrue(studentManager.existStudent(ipAddress3_another));
	}

	@Test
	public void IPアドレスでPCを取得できる() {
		assertTrue(studentManager.getStudent(ipAddressGateWay).isEmpty());

		Optional<Student> student1 = studentManager.getStudent(ipAddress1);
		assertEquals(student1.get().getPc().getIpAddress(), ipAddress1_another);

		Optional<Student> student2 = studentManager.getStudent(ipAddress2);
		assertEquals(student2.get().getPc().getIpAddress(), ipAddress2_another);

		Optional<Student> student3 = studentManager.getStudent(ipAddress3);
		assertEquals(student3.get().getPc().getIpAddress(), ipAddress3_another);
	}


	@Test
	public void getListで学生が取得できる() {
		List<Student> studentList = studentManager.getStudentList();

		Student student1 = studentList.get(0);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_another);

		Student student2 = studentList.get(1);
		assertEquals(student2.getPc().getIpAddress(), ipAddress2_another);

		Student student3 = studentList.get(2);
		assertEquals(student3.getPc().getIpAddress(), ipAddress3_another);

	}
	
	@Test
	public void 手を上げている学生のListが取得できる() {
		studentManager.handUp(ipAddress1);
		studentManager.handUp(ipAddress3);
		
		List<Student> studentList = studentManager.getHandUpStudentList();
		
		assertEquals(2,studentList.size());

		Student student1 = studentList.get(0);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_another);

		Student student2 = studentList.get(1);
		assertEquals(student2.getPc().getIpAddress(), ipAddress3_another);
	}
	

	@Test
	public void 手を上げるとHelpStatusとHandUpTimeと順位が変わる() {
		studentManager.handUp(ipAddress2);
		
		Student student = studentManager.getStudent(ipAddress2).get();

		assertEquals(HelpStatus.Troubled,student.getHelpStatus() );

		assertNotNull(student.getHandUpTime());
		assertNotNull(student.getWaitingTimeBySecond(LocalDateTime.now()));
		assertEquals(1,student.getPriority());
	}
	
	@Test
	public void 手を上げてからサポートするとHelpStatusと順位が変わる() {
		studentManager.handUp(ipAddress2);
		studentManager.supporting(ipAddress2);
		
		Student student = studentManager.getStudent(ipAddress2).get();

		assertEquals(student.getHelpStatus(), HelpStatus.Supporting);

		assertNull(student.getHandUpTime());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
		assertEquals(WaitingManager.NOT_REGISTED,student.getPriority());
	}
	
	@Test
	public void 手を上げてから手を下ろすと最初の状態にもどる() {
		studentManager.handUp(ipAddress2);
		studentManager.handDown(ipAddress2);
		
		Student student = studentManager.getStudent(ipAddress2).get();

		assertEquals(student.getHelpStatus(), HelpStatus.None);

		assertNull(student.getHandUpTime());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
		assertEquals(WaitingManager.NOT_REGISTED,student.getPriority());
	}

}