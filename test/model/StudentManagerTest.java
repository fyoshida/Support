package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import _data.Const;

public class StudentManagerTest {

	private StudentManager studentManager;

	@Before
	public void setUp() {
		studentManager = createStudentManager_forTest();
	}

	private StudentManager createStudentManager_forTest() {
		Pc pcGateway = new Pc(Const.IPADDRESS_GATEWAY, Const.HOSTNAME_GATEWAY, false);
		Pc pcTeacher = new Pc(Const.IPADDRESS_TEACHER, Const.HOSTNAME_TEACHER, false);

		Pc pc1 = new Pc(Const.IPADDRESS_1, Const.HOSTNAME_1, Const.ISSTUDENT_PC_1);
		Pc pc2 = new Pc(Const.IPADDRESS_2, Const.HOSTNAME_2, Const.ISSTUDENT_PC_2);
		Pc pc3 = new Pc(Const.IPADDRESS_3, Const.HOSTNAME_3, Const.ISSTUDENT_PC_3);

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
		assertFalse(studentManager.existStudentByIpAddress(Const.IPADDRESS_GATEWAY));
		assertTrue(studentManager.existStudentByIpAddress(Const.IPADDRESS_1));
		assertTrue(studentManager.existStudentByIpAddress(Const.IPADDRESS_2));
		assertTrue(studentManager.existStudentByIpAddress(Const.IPADDRESS_3));
	}

	@Test
	public void ホスト名でPCが登録されているか調べられる() {
		assertFalse(studentManager.existStudentByHostName(Const.HOSTNAME_GATEWAY));
		assertFalse(studentManager.existStudentByHostName(Const.HOSTNAME_TEACHER));
		assertTrue(studentManager.existStudentByHostName(Const.HOSTNAME_1));
		assertTrue(studentManager.existStudentByHostName(Const.HOSTNAME_2));
		assertTrue(studentManager.existStudentByHostName(Const.HOSTNAME_3));
	}

	@Test
	public void IPアドレスでPCを取得できる() {
		assertNull(studentManager.findStudentByIpAddress(Const.IPADDRESS_GATEWAY));

		Student student1 = studentManager.findStudentByIpAddress(Const.IPADDRESS_1);
		assertEquals(student1.getPc().getIpAddress(), Const.IPADDRESS_1);

		Student student2 = studentManager.findStudentByIpAddress(Const.IPADDRESS_2);
		assertEquals(student2.getPc().getIpAddress(), Const.IPADDRESS_2);

		Student student3 = studentManager.findStudentByIpAddress(Const.IPADDRESS_3);
		assertEquals(student3.getPc().getIpAddress(), Const.IPADDRESS_3);
	}

	@Test
	public void ホスト名でPCを取得できる() {
		assertNull(studentManager.findStudentByHostName(Const.HOSTNAME_GATEWAY));

		Student student1 = studentManager.findStudentByHostName(Const.HOSTNAME_1);
		assertEquals(student1.getPc().getHostName(), Const.HOSTNAME_1);

		Student student2 = studentManager.findStudentByHostName(Const.HOSTNAME_2);
		assertEquals(student2.getPc().getHostName(), Const.HOSTNAME_2);

		Student student3 = studentManager.findStudentByHostName(Const.HOSTNAME_3);
		assertEquals(student3.getPc().getHostName(), Const.HOSTNAME_3);
	}

	@Test
	public void getListで等されている学生が取得できる() {
		List<Student> studentList = studentManager.getStudentList();

		Student student1 = studentList.get(0);
		assertEquals(student1.getPc().getIpAddress(), Const.IPADDRESS_1);

		Student student2 = studentList.get(1);
		assertEquals(student2.getPc().getIpAddress(), Const.IPADDRESS_2);

		Student student3 = studentList.get(2);
		assertEquals(student3.getPc().getIpAddress(), Const.IPADDRESS_3);

	}

}