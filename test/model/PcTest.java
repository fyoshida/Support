package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PcTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private IpAddress ipAddress;
	private String hostName="icsGateWay";
	private Pc pc;

	@Before
	public void setUp() {
		ipAddress=new IpAddress(IPADDRESS_GATEWAY);
		pc = new Pc(ipAddress,hostName);
	}

	@Test
	public void 属性IPアドレスを取得できる() {
		assertEquals(pc.getIpAddress(),ipAddress);
	}
	@Test
	public void 属性HostNameを取得できる() {

		assertEquals(pc.getHostName(),hostName);
	}

	@Test
	public void 属性IsStudentをアクセッサで読み書きできる() {
		pc.setStudent(true);

		assertTrue(pc.isStudent());
	}


}