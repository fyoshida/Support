package domain;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.StudentManager;
import domain.entities.Pc;
import domain.entities.Student;

public class StudentManagerTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String HOSTNAME_GATEWAY = "ics";

	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String HOSTNAME_1 = "ics801";

	public static final String IPADDRESS_2 = "133.44.118.188";
	public static final String HOSTNAME_2 = "ics831";

	public static final String IPADDRESS_3 = "133.44.118.228";
	public static final String HOSTNAME_3 = "ics871";

	private InetAddress ipAddressGateWay;
	private InetAddress ipAddress1;
	private InetAddress ipAddress2;
	private InetAddress ipAddress3;
	private InetAddress ipAddress1_another;
	private InetAddress ipAddress2_another;
	private InetAddress ipAddress3_another;
	private Pc pcGateway;
	private Pc pc1;
	private Pc pc2;
	private Pc pc3;

	private StudentManager studentManager;

	@Before
	public void setUp() {
		try {
			ipAddressGateWay =InetAddress.getByName(IPADDRESS_GATEWAY);
			ipAddress1 = InetAddress.getByName(IPADDRESS_1);
			ipAddress2 = InetAddress.getByName(IPADDRESS_2);
			ipAddress3 = InetAddress.getByName(IPADDRESS_3);

			ipAddress1_another = InetAddress.getByName(IPADDRESS_1);
			ipAddress2_another = InetAddress.getByName(IPADDRESS_2);
			ipAddress3_another =InetAddress.getByName(IPADDRESS_3);

			List<Pc> pcList = new LinkedList<Pc>();
			pcList.add(pcGateway);
			pcList.add(pc1);
			pcList.add(pc2);
			pcList.add(pc3);

			studentManager = new StudentManager(pcList);
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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

		Optional<Student> student1 = studentManager.getStudent(ipAddress1);
		assertEquals(student1.get().getPc().getIpAddress(), ipAddress1_another);

		Optional<Student> student2 = studentManager.getStudent(ipAddress2);
		assertEquals(student2.get().getPc().getIpAddress(), ipAddress2_another);

		Optional<Student> student3 = studentManager.getStudent(ipAddress3);
		assertEquals(student3.get().getPc().getIpAddress(), ipAddress3_another);
	}

	@Test
	public void ホスト名でPCを取得できる() {
		assertNull(studentManager.getStudent(HOSTNAME_GATEWAY));

		Optional<Student> student1 = studentManager.getStudent(HOSTNAME_1);
		assertEquals(student1.get().getPc().getHostName(), HOSTNAME_1);

		Optional<Student> student2 = studentManager.getStudent(HOSTNAME_2);
		assertEquals(student2.get().getPc().getHostName(), HOSTNAME_2);

		Optional<Student> student3 = studentManager.getStudent(HOSTNAME_3);
		assertEquals(student3.get().getPc().getHostName(), HOSTNAME_3);
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