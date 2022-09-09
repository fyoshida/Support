package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import _data.Const;

public class PcTest {

	private Pc pc;

	@Before
	public void setUp() {
		pc = new Pc(Const.IPADDRESS_GATEWAY, Const.HOSTNAME_GATEWAY, false);
	}

	@Test
	public void 属性IPアドレスを取得できる() {
		assertEquals(pc.getIpAddress(), Const.IPADDRESS_GATEWAY);
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