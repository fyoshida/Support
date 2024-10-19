package servlet;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestServletBase;
import domain.valueobjects.HelpStatus;
import helper.JsonConverter;
import helper.PcJson;
import helper.PcJsonHelper;
import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class HandDownServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
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
	public void GETメソッドでアクセスすると手を下げられる() throws Exception {
		String targetPcIpAddress = NetworkFactory.ipAddress;
		String targetPcHostName = NetworkFactory.hostName;

		getMessages("InitializeServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("HandDownServlet");

		String response = webResponse.getText();
		List<PcJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(),62);

		PcJson pcJson = PcJsonHelper.findPcJson(pcJsonList,targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(),targetPcHostName);
		assertEquals(pcJson.getHelpStatus(),HelpStatus.None.getDisplayName());
	}

	@Test
	public void 手をあげている状態でGETメソッドでアクセスすると手を下げられる() throws Exception {
		String targetPcIpAddress = NetworkFactory.ipAddress;
		String targetPcHostName = NetworkFactory.hostName;

		getMessages("InitializeServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("HandUpServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("HandDownServlet");

		String response = webResponse.getText();
		List<PcJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(),62);

		PcJson pcJson = PcJsonHelper.findPcJson(pcJsonList,targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(),targetPcHostName);
		assertEquals(pcJson.getHelpStatus(),HelpStatus.None.getDisplayName());
	}

	@Test
	public void サポート状態でGETメソッドでアクセスすると手を下げられる() throws Exception {
		String targetPcIpAddress = NetworkFactory.ipAddress;
		String targetPcHostName = NetworkFactory.hostName;

		getMessages("InitializeServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("HandUpServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("SupportServlet");

		webRequest.setParameter("HostName", ""+targetPcHostName);
		getMessages("HandDownServlet");

		String response = webResponse.getText();
		List<PcJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(),62);

		PcJson pcJson = PcJsonHelper.findPcJson(pcJsonList,targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(),targetPcHostName);
		assertEquals(pcJson.getHelpStatus(),HelpStatus.None.getDisplayName());
	}
}
