package domain;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import domain.aggregate.WaitingManager;

public class WaitingManagerTest {
	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String IPADDRESS_2 = "133.44.118.228";

	private InetAddress ipAddress1;
	private InetAddress ipAddress2;
	private InetAddress ipAddress3;

	@Before
	public void setUp() {
		try {
			ipAddress1 = InetAddress.getByName(IPADDRESS_1);
			ipAddress2 = InetAddress.getByName(IPADDRESS_2);
			ipAddress3 = InetAddress.getByName(IPADDRESS_GATEWAY);
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

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