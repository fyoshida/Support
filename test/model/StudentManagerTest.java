package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import _data.Const;

public class StudentManagerTest {

	private IpAddress ipAddressGateWay;
	private IpAddress ipAddressTeacher;
	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;

	private IpAddress ipAddress1_otherInstance;
	private IpAddress ipAddress2_otherInstance;
	private IpAddress ipAddress3_otherInstance;

	private StudentManager studentManager;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(Const.IPADDRESS_GATEWAY);
		ipAddressTeacher = new IpAddress(Const.IPADDRESS_TEACHER);
		ipAddress1 = new IpAddress(Const.IPADDRESS_1);
		ipAddress2 = new IpAddress(Const.IPADDRESS_2);
		ipAddress3 = new IpAddress(Const.IPADDRESS_3);

		ipAddress1_otherInstance = new IpAddress(Const.IPADDRESS_1);
		ipAddress2_otherInstance = new IpAddress(Const.IPADDRESS_2);
		ipAddress3_otherInstance = new IpAddress(Const.IPADDRESS_3);

		studentManager = createStudentManager_forTest();
	}

	private StudentManager createStudentManager_forTest() {
		Pc pcGateway = new Pc(ipAddressGateWay,Const.HOSTNAME_GATEWAY,false);
		Pc pcTeacher = new Pc(ipAddressTeacher,Const.HOSTNAME_TEACHER,false);

		Pc pc1 = new Pc(ipAddress1,Const.HOSTNAME_1,Const.ISSTUDENT_PC_1);
		Pc pc2 = new Pc(ipAddress2,Const.HOSTNAME_2,Const.ISSTUDENT_PC_2);
		Pc pc3 = new Pc(ipAddress3,Const.HOSTNAME_3,Const.ISSTUDENT_PC_3);

		List<Pc> pcList = new LinkedList<Pc>();
		pcList.add(pcGateway);
		pcList.add(pcTeacher);
		pcList.add(pc1);
		pcList.add(pc2);
		pcList.add(pc3);

		return new StudentManager(pcList);
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
		assertFalse(studentManager.existStudent(Const.HOSTNAME_GATEWAY));
		assertFalse(studentManager.existStudent(Const.HOSTNAME_TEACHER));
		assertTrue(studentManager.existStudent(Const.HOSTNAME_1));
		assertTrue(studentManager.existStudent(Const.HOSTNAME_2));
		assertTrue(studentManager.existStudent(Const.HOSTNAME_3));
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
		assertNull(studentManager.getStudent(Const.HOSTNAME_GATEWAY));

		Student student1 = studentManager.getStudent(Const.HOSTNAME_1);
		assertEquals(student1.getPc().getHostName(), Const.HOSTNAME_1);

		Student student2 = studentManager.getStudent(Const.HOSTNAME_2);
		assertEquals(student2.getPc().getHostName(), Const.HOSTNAME_2);

		Student student3 = studentManager.getStudent(Const.HOSTNAME_3);
		assertEquals(student3.getPc().getHostName(), Const.HOSTNAME_3);
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