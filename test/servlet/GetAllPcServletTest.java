package servlet;

import static org.junit.Assert.*;

import java.util.List;

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

public class GetAllPcServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
		HttpClientFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("GetAllPcServlet");
	}

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		getMessages("InitializeServlet");
		getMessages("GetAllPcServlet");
		assertNotNull(webResponse);
	}

	@Test
	public void Getメソッドでアクセスすると全学生の情報を取得できる() throws Exception {
		getMessages("InitializeServlet");
		getMessages("GetAllPcServlet");

		String response = webResponse.getText();
		List<PcJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(),62);

		PcJson pcJsonFirst = pcJsonList.get(0);
		assertEquals(pcJsonFirst.getIpAdress(),"133.44.118.158");
		assertEquals(pcJsonFirst.getPcId(),"ics801");

		PcJson pcJsonLast = pcJsonList.get(61);
		assertEquals(pcJsonLast.getIpAdress(),"133.44.118.221");
		assertEquals(pcJsonLast.getPcId(),"ics864");
	}

}
