package domain;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import domain.entities.Pc;

public class PcTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private InetAddress ipAddress;
	private String hostName="icsGateWay";
	private Pc pc;

	@Before
	public void setUp() {
		try {
			ipAddress=InetAddress.getByName(IPADDRESS_GATEWAY);
			pc = new Pc(ipAddress,hostName);
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Test
	public void 属性IPアドレスを取得できる() {
		assertEquals(pc.getIpAddress(),ipAddress);
	}
	@Test
	public void 属性HostNameを取得できる() {

		assertEquals(pc.getHostName(),hostName);
	}


}