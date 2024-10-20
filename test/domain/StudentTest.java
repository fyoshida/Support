package domain;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.HelpStatus;
import domain.valueobjects.IpAddress;

public class StudentTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String hostName="icsGateWay";
	
	private IpAddress ipAddress;
	private Student student;

	@Before
	public void setUp() {
		ipAddress = new IpAddress(IPADDRESS_GATEWAY);
		Pc pc = new Pc(ipAddress,hostName);

		student = new Student(pc);
	}

	@Test
	public void 生成直後の状態を確認() {
		assertNotNull(student.getPc());
		assertEquals(student.getHelpStatus(), HelpStatus.None);
		assertNull(student.getHandUpTime());
	}


	@Test
	public void 生成直後は手を上げていない() {
		assertEquals(student.getHelpStatus(), HelpStatus.None);

		assertNull(student.getHandUpTime());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
	}

	@Test
	public void 手を上げるとHelpStatusとHandUpTimeが変わる() {
		student.handUp();

		assertEquals(student.getHelpStatus(), HelpStatus.Troubled);

		assertNotNull(student.getHandUpTime());
		assertNotNull(student.getWaitingTimeBySecond(LocalDateTime.now()));
	}

	@Test
	public void 手を上げてから手を下ろすと最初の状態にもどる() {
		student.handUp();
		student.handDown();

		assertEquals(student.getHelpStatus(), HelpStatus.None);

		assertNull(student.getHandUpTime());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
	}

	@Test
	public void 手を上げてからサポートするとHelpStatusが変わる() {
		student.handUp();
		student.supporting();

		assertEquals(student.getHelpStatus(), HelpStatus.Supporting);

		assertNull(student.getHandUpTime());
		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
	}

	@Test
	public void 手の上げ下げやサポートによってHelpStatusが変わる() {
		assertEquals(student.getHelpStatus(),HelpStatus.None);

		student.handUp();

		assertEquals(student.getHelpStatus(),HelpStatus.Troubled);

		student.supporting();

		assertEquals(student.getHelpStatus(),HelpStatus.Supporting);

		student.handDown();

		assertEquals(student.getHelpStatus(),HelpStatus.None);
	}

	@Test
	public void 手の上げ下げやサポートによってHandUpTimeが変わる() {
		assertNull(student.getHandUpTime());

		student.handUp();

		assertNotNull(student.getHandUpTime());

		student.supporting();

		assertNull(student.getHandUpTime());
		
		student.handDown();

		assertNull(student.getHandUpTime());
	}

	@Test
	public void 手の上げ下げやサポートによってWaitingTimeが変わる() {
		assertEquals(0 , student.getWaitingTimeBySecond(LocalDateTime.now()));

		student.handUp();

		assertTrue(student.getWaitingTimeBySecond(LocalDateTime.now())>=0);

		student.supporting();

		assertTrue(student.getWaitingTimeBySecond(LocalDateTime.now())>=0);

		student.handDown();

		assertEquals(0,student.getWaitingTimeBySecond(LocalDateTime.now()));
	}
}