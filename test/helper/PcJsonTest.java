package helper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import _test_data.Cnst;

public class PcJsonTest {
	private PcJson pcJson;

	@Before
	public void setUp() {
		pcJson = new PcJson();
	}

	@Test
	public void 属性IPアドレスをアクセッサで読み書きできる() {
		pcJson.setIpAdress(Cnst.IPADDRESS_GATEWAY);

		assertEquals(pcJson.getIpAdress(),Cnst.IPADDRESS_GATEWAY);
	}
	@Test
	public void 属性HostNameアクセッサで読み書きできる() {
		pcJson.setPcId("ics800");

		assertEquals(pcJson.getPcId(),"ics800");
	}

	@Test
	public void 属性IsStudentをアクセッサで読み書きできる() {
		pcJson.setIsStudent(true);
		assertTrue(pcJson.getIsStudent());

		pcJson.setIsStudent(false);
		assertFalse(pcJson.getIsStudent());
	}

	@Test
	public void 属性IsLoginをアクセッサで読み書きできる() {
		pcJson.setIsLogin(true);
		assertTrue(pcJson.getIsLogin());

		pcJson.setIsLogin(false);
		assertFalse(pcJson.getIsLogin());
	}

	@Test
	public void 属性HelpStatusをアクセッサで読み書きできる() {
		//手を挙げていない: None
		//手を挙げている: Troubled
		//TA教員対応中: Supporting

		pcJson.setHelpStatus("None");
		assertEquals(pcJson.getHelpStatus(),"None");

		pcJson.setHelpStatus("Troubled");
		assertEquals(pcJson.getHelpStatus(),"Troubled");

		pcJson.setHelpStatus("Supporting");
		assertEquals(pcJson.getHelpStatus(),"Supporting");
	}

	@Test
	public void 属性HandPriorityをアクセッサで読み書きできる() {
		pcJson.setHandPriority(1);
		assertEquals(pcJson.getHandPriority(),1);

		pcJson.setHandPriority(999);
		assertEquals(pcJson.getHandPriority(),999);
	}

}

