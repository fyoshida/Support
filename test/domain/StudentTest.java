package domain;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.WaitingManager;
import domain.entities.Pc;
import domain.entities.Student;
import domain.valueobjects.HelpStatus;

public class StudentTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private InetAddress ipAddress;
	private String hostName;
	private WaitingManager waitingManager;
	private Student student;

	@Before
	public void setUp() {
		try {
			ipAddress = InetAddress.getByName(IPADDRESS_GATEWAY);
			hostName ="icsGateWay";
			Pc pc = new Pc(ipAddress,hostName);

			student = new Student(pc);
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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
		assertEquals(student.getPriority(), WaitingManager.NOT_REGISTED);

		assertNull(student.getHandUpTime());
		assertNull(student.getWaitingTime(LocalDateTime.now()));
	}

	@Test
	public void 手を上げるとHelpStatusとHandUpTimeが変わり優先順位が1になる() {
		student.handUp();

		assertEquals(student.getHelpStatus(), HelpStatus.Troubled);
		assertEquals(student.getPriority(), 1);

		assertNotNull(student.getHandUpTime());
		assertNotNull(student.getWaitingTime(LocalDateTime.now()));
	}

	@Test
	public void 手を上げてから手を下ろすと最初の状態にもどる() {
		student.handUp();
		student.handDown();

		assertEquals(student.getHelpStatus(), HelpStatus.None);
		assertEquals(student.getPriority(), WaitingManager.NOT_REGISTED);

		assertNull(student.getHandUpTime());
		assertNull(student.getWaitingTime(LocalDateTime.now()));
	}

	@Test
	public void 手を上げてからサポートするとHelpStatusが変わりPriorityが最初の状態にもどる() {
		student.handUp();
		student.supporting();

		assertEquals(student.getHelpStatus(), HelpStatus.Supporting);
		assertEquals(student.getPriority(), WaitingManager.NOT_REGISTED);

		assertNull(student.getHandUpTime());
		assertNull(student.getWaitingTime(LocalDateTime.now()));
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
	public void 手の上げ下げやサポートによって順位が変わる() {
		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);

		student.handUp();

		assertEquals(student.getPriority(),1);

		student.supporting();

		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);

		student.handDown();

		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);
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
		assertNull(student.getWaitingTime(LocalDateTime.now()));

		student.handUp();

		assertNotNull(student.getWaitingTime(LocalDateTime.now()));

		student.supporting();

		assertNull(student.getWaitingTime(LocalDateTime.now()));

		student.handDown();

		assertNull(student.getWaitingTime(LocalDateTime.now()));
	}
}