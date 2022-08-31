package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		pcGateway.setStudent(false);

		pc1 = new Pc(ipAddress1,HOSTNAME_1);
		pc1.setStudent(true);

		pc2 = new Pc(ipAddress2,HOSTNAME_2);
		pc2.setStudent(true);

		pc3 = new Pc(ipAddress3,HOSTNAME_3);
		pc3.setStudent(true);

		List<Pc> pcList = new LinkedList<Pc>();
		pcList.add(pcGateway);
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
	public void ホスト名でPCが登録されているか調べられる() {
		assertFalse(studentManager.existStudent(HOSTNAME_GATEWAY));
		assertTrue(studentManager.existStudent(HOSTNAME_1));
		assertTrue(studentManager.existStudent(HOSTNAME_2));
		assertTrue(studentManager.existStudent(HOSTNAME_3));
	}

	@Test
	public void IPアドレスでPCを取得できる() {
		assertNull(studentManager.getStudent(ipAddressGateWay));

		Student student1 = studentManager.getStudent(ipAddress1);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_another);

		Student student2 = studentManager.getStudent(ipAddress2);
		assertEquals(student2.getPc().getIpAddress(), ipAddress2_another);

		Student student3 = studentManager.getStudent(ipAddress3);
		assertEquals(student3.getPc().getIpAddress(), ipAddress3_another);
	}

	@Test
	public void ホスト名でPCを取得できる() {
		assertNull(studentManager.getStudent(HOSTNAME_GATEWAY));

		Student student1 = studentManager.getStudent(HOSTNAME_1);
		assertEquals(student1.getPc().getHostName(), HOSTNAME_1);

		Student student2 = studentManager.getStudent(HOSTNAME_2);
		assertEquals(student2.getPc().getHostName(), HOSTNAME_2);

		Student student3 = studentManager.getStudent(HOSTNAME_3);
		assertEquals(student3.getPc().getHostName(), HOSTNAME_3);
	}

	@Test
	public void getListで等されている学生が取得できる() {
		List<Student> studentList=studentManager.getStudentList();

		Student student1=studentList.get(0);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_another);

		Student student2=studentList.get(1);
		assertEquals(student2.getPc().getIpAddress(), ipAddress2_another);

		Student student3=studentList.get(2);
		assertEquals(student3.getPc().getIpAddress(), ipAddress3_another);

	}

}