package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PcBeanTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private IpAddress ipAddress;
	private PcBean pcBean;

	@Before
	public void setUp() {
		ipAddress=new IpAddress(IPADDRESS_GATEWAY);
		pcBean = new PcBean();
	}

	@Test
	public void 生成直後の状態を確認() {
		assertNull(pcBean.getIpAddress());
		assertEquals(pcBean.getHostName(),"");
		assertFalse(pcBean.isStudent);
	}

	@Test
	public void 属性IPアドレスをアクセッサで読み書きできる() {
		pcBean.setIpAddress(ipAddress);

		assertEquals(pcBean.getIpAddress(),ipAddress);
	}
	@Test
	public void 属性HostNameアクセッサで読み書きできる() {
		pcBean.setHostName("ics800");

		assertEquals(pcBean.getHostName(),"ics800");
	}

	@Test
	public void 属性IsStudentをアクセッサで読み書きできる() {
		pcBean.setStudent(true);

		assertTrue(pcBean.isStudent());
	}


}