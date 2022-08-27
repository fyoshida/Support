package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PcTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private IpAddress ipAddress;
	private WaitingManager waitingManager;
	private Pc pc;

	@Before
	public void setUp() {
		ipAddress = new IpAddress(IPADDRESS_GATEWAY);
		waitingManager = new WaitingManager();

		pc = new Pc(ipAddress, waitingManager);
	}

	@Test
	public void 生成直後の状態を確認() {
		assertNotNull(pc.getIpAddress());
		assertEquals(pc.getHostName(), "");
		assertFalse(pc.isStudent);
		assertEquals(pc.getHelpStatus(), HelpStatus.None);
		assertNull(pc.getHandUpTime());
	}

	@Test
	public void ログインしていない場合はgetUserNameでホスト名が得られる() {
		assertEquals(pc.getUserName(), pc.getHostName());
	}

	@Test
	public void ログインするとgetUserNameでユーザ名が得られる() {
		pc.login("abc");
		assertEquals(pc.getUserName(), "abc");
	}

	@Test
	public void ログアウトするとgetUserNameでホスト名が得られる() {
		pc.login("abc");
		assertEquals(pc.getUserName(), "abc");

		pc.logout();
		assertEquals(pc.getUserName(), pc.getHostName());
	}

	@Test
	public void ログアウトしなくても再ログインできる() {
		pc.login("abc");
		assertEquals(pc.getUserName(), "abc");

		pc.login("def");
		assertEquals(pc.getUserName(), "def");
	}

	@Test
	public void 生成直後は手を上げていない() {
		assertEquals(pc.getHelpStatus(), HelpStatus.None);
		assertEquals(pc.getPriority(), 999);

		assertNull(pc.getHandUpTime());
		assertNull(pc.getWaitingTime());
	}

	@Test
	public void 手を上げるとHelpStatusとHandUpTimeが変わり優先順位が1になる() {
		pc.handUp();

		assertEquals(pc.getHelpStatus(), HelpStatus.Troubled);
		assertEquals(pc.getPriority(), 1);

		assertNotNull(pc.getHandUpTime());
		assertNotNull(pc.getWaitingTime());
	}

	@Test
	public void 手を上げてから手を下ろすと最初の状態にもどる() {
		pc.handUp();
		pc.handDown();

		assertEquals(pc.getHelpStatus(), HelpStatus.None);
		assertEquals(pc.getPriority(), 999);

		assertNull(pc.getHandUpTime());
		assertNull(pc.getWaitingTime());
	}

	@Test
	public void 手を上げてからサポートするとHelpStatusが変わりPriorityが最初の状態にもどる() {
		pc.handUp();
		pc.supported();

		assertEquals(pc.getHelpStatus(), HelpStatus.Supporting);
		assertEquals(pc.getPriority(), 999);

		assertNotNull(pc.getHandUpTime());
		assertNotNull(pc.getWaitingTime());
	}

	@Test
	public void 手の上げ下げやサポートによってHelpStatusが変わる() {
		assertEquals(pc.getHelpStatus(),HelpStatus.None);

		pc.handUp();

		assertEquals(pc.getHelpStatus(),HelpStatus.Troubled);

		pc.supported();

		assertEquals(pc.getHelpStatus(),HelpStatus.Supporting);

		pc.handDown();

		assertEquals(pc.getHelpStatus(),HelpStatus.None);
	}

	@Test
	public void 手の上げ下げやサポートによって順位が変わる() {
		assertEquals(pc.getPriority(),999);

		pc.handUp();

		assertEquals(pc.getPriority(),1);

		pc.supported();

		assertEquals(pc.getPriority(),999);

		pc.handDown();

		assertEquals(pc.getPriority(),999);
	}

	@Test
	public void 手の上げ下げやサポートによってHandUpTimeが変わる() {
		assertNull(pc.getHandUpTime());

		pc.handUp();

		assertNotNull(pc.getHandUpTime());

		pc.supported();

		assertNotNull(pc.getHandUpTime());

		pc.handDown();

		assertNull(pc.getHandUpTime());
	}

	@Test
	public void 手の上げ下げやサポートによってWaitingTimeが変わる() {
		assertNull(pc.getWaitingTime());

		pc.handUp();

		assertNotNull(pc.getWaitingTime());

		pc.supported();

		assertNotNull(pc.getWaitingTime());

		pc.handDown();

		assertNull(pc.getWaitingTime());
	}
}