package servlet;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestServletBase;
import helper.JsonConverter;
import helper.StudentJson;
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
		Map<String,Object> parameter =new HashMap<String,Object>();
		parameter.put("Administrator", "fyoshida");
		getMessages("InitializeServlet");
		getMessagesWithSession("GetAllPcServlet",parameter);

		String response = webResponse.getText();
		List<StudentJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(71,pcJsonList.size());

		StudentJson pcJsonFirst = pcJsonList.get(0);
		assertEquals("ics801",pcJsonFirst.getPcId());
		assertEquals("133.44.118.158",pcJsonFirst.getIpAdress());

		StudentJson pcJsonLast = pcJsonList.get(70);
		assertEquals("ics871",pcJsonLast.getPcId());
		assertEquals("133.44.118.228",pcJsonLast.getIpAdress());
	}
	
	@Test
	public void 学生がGetメソッドでアクセスすると全学生のPC情報を除く情報を取得できる() throws Exception {
		getMessages("InitializeServlet");
		getMessages("GetAllPcServlet");

		String response = webResponse.getText();
		List<StudentJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(71,pcJsonList.size());

		StudentJson pcJsonFirst = pcJsonList.get(0);
		assertEquals("ics801",pcJsonFirst.getPcId());
		assertEquals("133.44.118.158",pcJsonFirst.getIpAdress());

		StudentJson pcJsonSecond = pcJsonList.get(1);
		assertEquals("",pcJsonSecond.getPcId());
		assertEquals("",pcJsonSecond.getIpAdress());

		StudentJson pcJsonLast = pcJsonList.get(70);
		assertEquals("",pcJsonLast.getPcId());
		assertEquals("",pcJsonLast.getIpAdress());
	}

}
