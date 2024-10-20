package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.entities.Pc;
import domain.valueobjects.IpAddress;

public class PcTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private static final String hostName = "icsGateWay";

	private IpAddress ipAddress;
	private Pc pc;

	@Before
	public void setUp() {
		ipAddress = new IpAddress(IPADDRESS_GATEWAY);
		pc = new Pc(ipAddress, hostName);
	}

	@Test
	public void 属性IPアドレスを取得できる() {
		assertEquals(ipAddress, pc.getIpAddress());
	}

	@Test
	public void 属性HostNameを取得できる() {
		assertEquals(hostName, pc.getHostName());
	}

}