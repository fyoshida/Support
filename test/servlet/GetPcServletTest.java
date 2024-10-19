package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestServletBase;
import helper.JsonConverter;
import helper.PcJson;
import httpclient.HttpClientFactory;
import httpclient.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class GetPcServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
		HttpClientFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("GetPcServlet");
	}

	@Test
	public void GetメソッドでアクセスするとアクセスしたPCの情報を取得できる() throws Exception {
		getMessages("InitializeServlet");
		getMessages("GetPcServlet");

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);
		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),HttpClientFactory.ipAddress);
		assertEquals(pcJson.getPcId(),HttpClientFactory.hostName);
		
	}
}
