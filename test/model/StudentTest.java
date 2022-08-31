package model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import _test_data.Cnst;

public class StudentTest {

	private IpAddress ipAddress;
	private String hostName;
	private WaitingManager waitingManager;
	private Student student;

	@Before
	public void setUp() {
		ipAddress = new IpAddress(Cnst.IPADDRESS_GATEWAY);
		hostName ="icsGateWay";
		Pc pc = new Pc(ipAddress,hostName);

		waitingManager = new WaitingManager();

		student = new Student(pc, waitingManager);
	}

	@Test
	public void 生成直後の状態を確認() {
		assertNotNull(student.getPc());
		assertEquals(student.getHelpStatus(), HelpStatus.None);
		assertNull(student.getHandUpTime());
	}

	@Test
	public void ログインしていない場合はgetUserNameでホスト名が得られる() {
		assertEquals(student.getUserName(), student.getPc().getHostName());
	}

	@Test
	public void ログインするとgetUserNameでユーザ名が得られる() {
		student.login("abc");
		assertEquals(student.getUserName(), "abc");
	}

	@Test
	public void ログアウトするとgetUserNameでホスト名が得られる() {
		student.login("abc");
		assertEquals(student.getUserName(), "abc");

		student.logout();
		assertEquals(student.getUserName(), student.getPc().getHostName());
	}

	@Test
	public void ログアウトしなくても再ログインできる() {
		student.login("abc");
		assertEquals(student.getUserName(), "abc");

		student.login("def");
		assertEquals(student.getUserName(), "def");
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
		student.supported();

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

		student.supported();

		assertEquals(student.getHelpStatus(),HelpStatus.Supporting);

		student.handDown();

		assertEquals(student.getHelpStatus(),HelpStatus.None);
	}

	@Test
	public void 手の上げ下げやサポートによって順位が変わる() {
		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);

		student.handUp();

		assertEquals(student.getPriority(),1);

		student.supported();

		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);

		student.handDown();

		assertEquals(student.getPriority(),WaitingManager.NOT_REGISTED);
	}

	@Test
	public void 手の上げ下げやサポートによってHandUpTimeが変わる() {
		assertNull(student.getHandUpTime());

		student.handUp();

		assertNotNull(student.getHandUpTime());

		student.supported();

		assertNull(student.getHandUpTime());
		
		student.handDown();

		assertNull(student.getHandUpTime());
	}

	@Test
	public void 手の上げ下げやサポートによってWaitingTimeが変わる() {
		assertNull(student.getWaitingTime(LocalDateTime.now()));

		student.handUp();

		assertNotNull(student.getWaitingTime(LocalDateTime.now()));

		student.supported();

		assertNull(student.getWaitingTime(LocalDateTime.now()));

		student.handDown();

		assertNull(student.getWaitingTime(LocalDateTime.now()));
	}
}