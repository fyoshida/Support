package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PcManagerTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	public static final String HOSTNAME_GATEWAY = "ics";

	public static final String IPADDRESS_1 = "133.44.118.158";
	public static final String HOSTNAME_1 = "ics801";

	public static final String IPADDRESS_2 = "133.44.118.188";
	public static final String HOSTNAME_2 = "ics831";

	public static final String IPADDRESS_3 = "133.44.118.228";
	public static final String HOSTNAME_3 = "ics871";

	private IpAddress ipAddressGateWay;
	private IpAddress ipAddress1;
	private IpAddress ipAddress2;
	private IpAddress ipAddress3;

	private PcBean pcBeanGateway;
	private PcBean pcBean1;
	private PcBean pcBean2;
	private PcBean pcBean3;

	private PcManager pcManager;

	@Before
	public void setUp() {
		ipAddressGateWay = new IpAddress(IPADDRESS_GATEWAY);
		ipAddress1 = new IpAddress(IPADDRESS_1);
		ipAddress2 = new IpAddress(IPADDRESS_2);
		ipAddress3 = new IpAddress(IPADDRESS_3);

		pcBeanGateway = new PcBean();
		pcBeanGateway.setIpAddress(ipAddressGateWay);
		pcBeanGateway.setHostName(HOSTNAME_GATEWAY);
		pcBeanGateway.setStudent(false);

		pcBean1 = new PcBean();
		pcBean1.setIpAddress(ipAddress1);
		pcBean1.setHostName(HOSTNAME_1);
		pcBean1.setStudent(true);

		pcBean2 = new PcBean();
		pcBean2.setIpAddress(ipAddress2);
		pcBean2.setHostName(HOSTNAME_2);
		pcBean2.setStudent(true);

		pcBean3 = new PcBean();
		pcBean3.setIpAddress(ipAddress3);
		pcBean3.setHostName(HOSTNAME_3);
		pcBean3.setStudent(true);

		List<PcBean> pcBeanList = new LinkedList<PcBean>();
		pcBeanList.add(pcBeanGateway);
		pcBeanList.add(pcBean1);
		pcBeanList.add(pcBean2);
		pcBeanList.add(pcBean3);

		pcManager = new PcManager(pcBeanList);
	}

	@Test
	public void IPアドレスでPCが登録されているか調べられる() {
		assertFalse(pcManager.existPc(ipAddressGateWay));
		assertTrue(pcManager.existPc(ipAddress1));
		assertTrue(pcManager.existPc(ipAddress2));
		assertTrue(pcManager.existPc(ipAddress3));
	}

	@Test
	public void ホスト名でPCが登録されているか調べられる() {
		assertFalse(pcManager.existPc(HOSTNAME_GATEWAY));
		assertTrue(pcManager.existPc(HOSTNAME_1));
		assertTrue(pcManager.existPc(HOSTNAME_2));
		assertTrue(pcManager.existPc(HOSTNAME_3));
	}

	@Test
	public void IPアドレスでPCを取得できる() {
		assertNull(pcManager.getPc(ipAddressGateWay));

		Pc pc1 = pcManager.getPc(ipAddress1);
		assertEquals(pc1.getIpAddress(), ipAddress1);

		Pc pc2 = pcManager.getPc(ipAddress2);
		assertEquals(pc2.getIpAddress(), ipAddress2);

		Pc pc3 = pcManager.getPc(ipAddress3);
		assertEquals(pc3.getIpAddress(), ipAddress3);
	}

	@Test
	public void ホスト名でPCを取得できる() {
		assertNull(pcManager.getPc(HOSTNAME_GATEWAY));

		Pc pc1 = pcManager.getPc(HOSTNAME_1);
		assertEquals(pc1.getHostName(), HOSTNAME_1);

		Pc pc2 = pcManager.getPc(HOSTNAME_2);
		assertEquals(pc2.getHostName(), HOSTNAME_2);

		Pc pc3 = pcManager.getPc(HOSTNAME_3);
		assertEquals(pc3.getHostName(), HOSTNAME_3);
	}

}