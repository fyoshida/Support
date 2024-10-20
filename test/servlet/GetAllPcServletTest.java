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
		assertEquals(71,pcJsonList.size());

		PcJson pcJsonFirst = pcJsonList.get(0);
		assertEquals("ics801",pcJsonFirst.getPcId());
		assertEquals("133.44.118.158",pcJsonFirst.getIpAdress());

		PcJson pcJsonLast = pcJsonList.get(70);
		assertEquals("ics871",pcJsonLast.getPcId());
		assertEquals("133.44.118.228",pcJsonLast.getIpAdress());
	}

}
