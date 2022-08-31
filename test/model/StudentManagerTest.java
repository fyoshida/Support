package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import _test_data.Cnst;

public class StudentManagerTest {

	private IpAddress ipAddressGateWay;
	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;
	private IpAddress ipAddress1_otherInstance;
	private IpAddress ipAddress2_otherInstance;
	private IpAddress ipAddress3_otherInstance;
	private Pc pcGateway;
	private Pc pc1;
	private Pc pc2;
	private Pc pc3;

	private StudentManager studentManager;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(Cnst.IPADDRESS_GATEWAY);
		ipAddress1 = new IpAddress(Cnst.IPADDRESS_1);
		ipAddress2 = new IpAddress(Cnst.IPADDRESS_2);
		ipAddress3 = new IpAddress(Cnst.IPADDRESS_3);

		ipAddress1_otherInstance = new IpAddress(Cnst.IPADDRESS_1);
		ipAddress2_otherInstance = new IpAddress(Cnst.IPADDRESS_2);
		ipAddress3_otherInstance = new IpAddress(Cnst.IPADDRESS_3);

		pcGateway = new Pc(ipAddressGateWay,Cnst.HOSTNAME_GATEWAY);
		pcGateway.setStudent(false);

		pc1 = new Pc(ipAddress1,Cnst.HOSTNAME_1);
		pc1.setStudent(true);

		pc2 = new Pc(ipAddress2,Cnst.HOSTNAME_2);
		pc2.setStudent(true);

		pc3 = new Pc(ipAddress3,Cnst.HOSTNAME_3);
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
		assertTrue(studentManager.existStudent(ipAddress1_otherInstance));
		assertTrue(studentManager.existStudent(ipAddress2_otherInstance));
		assertTrue(studentManager.existStudent(ipAddress3_otherInstance));
	}

	@Test
	public void ホスト名でPCが登録されているか調べられる() {
		assertFalse(studentManager.existStudent(Cnst.HOSTNAME_GATEWAY));
		assertTrue(studentManager.existStudent(Cnst.HOSTNAME_1));
		assertTrue(studentManager.existStudent(Cnst.HOSTNAME_2));
		assertTrue(studentManager.existStudent(Cnst.HOSTNAME_3));
	}

	@Test
	public void IPアドレスでPCを取得できる() {
		assertNull(studentManager.getStudent(ipAddressGateWay));

		Student student1 = studentManager.getStudent(ipAddress1);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_otherInstance);

		Student student2 = studentManager.getStudent(ipAddress2);
		assertEquals(student2.getPc().getIpAddress(), ipAddress2_otherInstance);

		Student student3 = studentManager.getStudent(ipAddress3);
		assertEquals(student3.getPc().getIpAddress(), ipAddress3_otherInstance);
	}

	@Test
	public void ホスト名でPCを取得できる() {
		assertNull(studentManager.getStudent(Cnst.HOSTNAME_GATEWAY));

		Student student1 = studentManager.getStudent(Cnst.HOSTNAME_1);
		assertEquals(student1.getPc().getHostName(), Cnst.HOSTNAME_1);

		Student student2 = studentManager.getStudent(Cnst.HOSTNAME_2);
		assertEquals(student2.getPc().getHostName(), Cnst.HOSTNAME_2);

		Student student3 = studentManager.getStudent(Cnst.HOSTNAME_3);
		assertEquals(student3.getPc().getHostName(), Cnst.HOSTNAME_3);
	}

	@Test
	public void getListで等されている学生が取得できる() {
		List<Student> studentList=studentManager.getStudentList();

		Student student1=studentList.get(0);
		assertEquals(student1.getPc().getIpAddress(), ipAddress1_otherInstance);

		Student student2=studentList.get(1);
		assertEquals(student2.getPc().getIpAddress(), ipAddress2_otherInstance);

		Student student3=studentList.get(2);
		assertEquals(student3.getPc().getIpAddress(), ipAddress3_otherInstance);

	}

}