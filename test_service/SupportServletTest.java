

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;
import model.HelpStatus;
import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class SupportServletTest extends _TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		NetworkFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("HandUpServlet");
		registServlet("HandDownServlet");
		registServlet("SupportServlet");
	}

	@Test
	public void GETメソッドでアクセスするとサポートできる() throws Exception {
		String targetPcIpAddress = NetworkFactory.ipAddress;
		String targetPcHostName = NetworkFactory.hostName;

		callHttp(MethodType.GET,"InitializeServlet");

		webRequest.setParameter("HostName", "" + targetPcHostName);
		callHttp(MethodType.GET,"HandUpServlet");

		webRequest.setParameter("HostName", "" + targetPcHostName);
		callHttp(MethodType.GET,"SupportServlet");

		String response = webResponse.getText();
		List<PcJson> pcJsonList = JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(), 62);

		PcJson pcJson = PcJsonHelper.findPcJson(pcJsonList, targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(), targetPcHostName);
		assertEquals(pcJson.getHelpStatus(), HelpStatus.Supporting.toString());

	}
}
