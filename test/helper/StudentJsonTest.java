package helper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentJsonTest {

	public static final String IPADDRESS_GATEWAY = "133.44.118.254";
	private StudentJson pcJson;

	@Before
	public void setUp() {
		pcJson = new StudentJson();
	}

	@Test
	public void 属性IPアドレスをアクセッサで読み書きできる() {
		pcJson.setIpAdress(IPADDRESS_GATEWAY);

		assertEquals(pcJson.getIpAdress(),IPADDRESS_GATEWAY);
	}
	@Test
	public void 属性HostNameアクセッサで読み書きできる() {
		pcJson.setPcId("ics800");

		assertEquals(pcJson.getPcId(),"ics800");
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

