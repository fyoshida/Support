package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HelpStatus;
import model.PcJson;

public class IpAddressTest {

	private static final String SAMPLE_ID1 = "ics801";
	private static final String SAMPLE_ID2 = "ics725";

	private static final String SAMPLE_IPADDRESS1 = "133.44.118.158";
	private static final String SAMPLE_IPADDRESS2 = "133.44.118.228";

	@Test
	public void 属性PcIdはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setPcId(SAMPLE_ID1);
		assertEquals(pcjson.getPcId(), SAMPLE_ID1);

		pcjson.setPcId(SAMPLE_ID2);
		assertEquals(pcjson.getPcId(), SAMPLE_ID2);
	}

	@Test
	public void 属性IpAdresseはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setIpAdress(SAMPLE_IPADDRESS1);
		assertEquals(pcjson.getIpAdress(), SAMPLE_IPADDRESS1);

		pcjson.setIpAdress(SAMPLE_IPADDRESS2);
		assertEquals(pcjson.getIpAdress(), SAMPLE_IPADDRESS2);
	}

	@Test
	public void 属性IsStudentはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setIsStudent(true);
		assertTrue(pcjson.getIsStudent());

		pcjson.setIsStudent(false);
		assertFalse(pcjson.getIsStudent());
	}

	@Test
	public void 属性IsLoginはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setIsLogin(true);
		assertTrue(pcjson.getIsLogin());

		pcjson.setIsLogin(false);
		assertFalse(pcjson.getIsLogin());
	}

	@Test
	public void 属性HelpStatusはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setHelpStatus(HelpStatus.None);
		assertEquals(pcjson.getHelpStatus(),HelpStatus.None);

		pcjson.setHelpStatus(HelpStatus.Troubled);
		assertEquals(pcjson.getHelpStatus(),HelpStatus.Troubled);

		pcjson.setHelpStatus(HelpStatus.Supporting);
		assertEquals(pcjson.getHelpStatus(),HelpStatus.Supporting);
	}

	@Test
	public void 属性HandPriorityはアクセッサで読み書きできる() {
		PcJson pcjson = new PcJson();

		pcjson.setHandPriority(-1);
		assertEquals(pcjson.getHandPriority(),-1);
	}
}