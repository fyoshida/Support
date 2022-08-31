package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import _test_data.Cnst;

public class WaitingManagerTest {
	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;

	@Before
	public void setUp() {
		ipAddress1 = new IpAddress(Cnst.IPADDRESS_1);
		ipAddress2 = new IpAddress(Cnst.IPADDRESS_2);
		ipAddress3 = new IpAddress(Cnst.IPADDRESS_GATEWAY);
	}

	@Test
	public void 登録した順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);

		assertEquals(wm.getPriority(ipAddress1), 1);
		assertEquals(wm.getPriority(ipAddress2), 2);
	}

	@Test
	public void 全部削除できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);
		wm.regist(ipAddress3);
		wm.unregist(ipAddress2);
		wm.unregist(ipAddress1);
		wm.unregist(ipAddress3);

		assertEquals(wm.getPriority(ipAddress1), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(ipAddress2), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(ipAddress3), WaitingManager.NOT_REGISTED);
	}

	@Test
	public void 先頭を削除しても正しく順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);
		wm.regist(ipAddress3);
		wm.unregist(ipAddress1);

		assertEquals(wm.getPriority(ipAddress1), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(ipAddress2), 1);
		assertEquals(wm.getPriority(ipAddress3), 2);
	}

	@Test
	public void 中央を削除しても正しく順序を取得できる() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);
		wm.regist(ipAddress3);
		wm.unregist(ipAddress2);

		assertEquals(wm.getPriority(ipAddress1), 1);
		assertEquals(wm.getPriority(ipAddress2), WaitingManager.NOT_REGISTED);
		assertEquals(wm.getPriority(ipAddress3), 2);
	}

	@Test
	public void 削除して再登録すると最後になる_1() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);
		wm.regist(ipAddress3);
		wm.unregist(ipAddress1);
		wm.regist(ipAddress1);

		assertEquals(wm.getPriority(ipAddress2), 1);
		assertEquals(wm.getPriority(ipAddress3), 2);
		assertEquals(wm.getPriority(ipAddress1), 3);
	}

	@Test
	public void 削除して再登録すると最後になる_2() {

		WaitingManager wm = new WaitingManager();
		wm.regist(ipAddress1);
		wm.regist(ipAddress2);
		wm.regist(ipAddress3);
		wm.unregist(ipAddress2);
		wm.regist(ipAddress2);

		assertEquals(wm.getPriority(ipAddress1), 1);
		assertEquals(wm.getPriority(ipAddress3), 2);
		assertEquals(wm.getPriority(ipAddress2), 3);
	}

}