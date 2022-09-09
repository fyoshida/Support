

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import helper.JsonConverter;
import helper.PcJson;
import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class LoginServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		NetworkFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("LoginServlet");
		registServlet("GetPcServlet");
	}

	@Test
	public void GETメソッドでアクセスするとログインできる() throws Exception {
		String targetPcIpAddress = NetworkFactory.ipAddress;
		String targetPcHostName = NetworkFactory.hostName;
		String targetUserName="Test";

		getMessages("InitializeServlet");

		webRequest.setParameter("UserName", targetUserName);
		getMessages("LoginServlet");

		getMessages("GetPcServlet");
		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);
		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),targetPcIpAddress);
		assertEquals(pcJson.getPcId(),targetPcHostName);
		assertFalse(pcJson.getIsLogin());

	}
}
