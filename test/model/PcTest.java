package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import _data.Const;

public class PcTest {

	private IpAddress ipAddress;
	private Pc pc;

	@Before
	public void setUp() {
		ipAddress = new IpAddress(Const.IPADDRESS_GATEWAY);
		pc = new Pc(ipAddress, Const.HOSTNAME_GATEWAY, false);
	}

	@Test
	public void IPアドレスがNullではPCを生成できない() {
		assertThrows(NullPointerException.class,()->new Pc(null,Const.HOSTNAME_GATEWAY,false));
	}

	@Test
	public void ホスト名がNullではPCを生成できない() {
		assertThrows(NullPointerException.class,()->new Pc(ipAddress,null,false));
	}

	@Test
	public void ホスト名が13文字以上ではPCを生成できない() {
		assertThrows(IllegalArgumentException.class,()->new Pc(ipAddress,"1234567890123",false));
	}

	@Test
	public void ホスト名に英数字以外の文字があるとPCを生成できない() {
		assertThrows(IllegalArgumentException.class,()->new Pc(ipAddress,"ics800-",false));
	}

	@Test
	public void 属性IPアドレスを取得できる() {
		assertEquals(pc.getIpAddress(), ipAddress);
	}

	@Test
	public void 属性HostNameを取得できる() {

		assertEquals(pc.getHostName(), Const.HOSTNAME_GATEWAY);
	}

	@Test
	public void 属性IsStudentをアクセッサで読み書きできる() {
		pc.setStudent(true);

		assertTrue(pc.isStudent());
	}

}