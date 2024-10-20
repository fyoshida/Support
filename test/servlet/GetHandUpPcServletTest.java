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

public class GetHandUpPcServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
		HttpClientFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("HandUpServlet");
		registServlet("GetHandUpPcServlet");
	}

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		getMessages("InitializeServlet");

		getMessages("GetHandUpPcServlet");
		assertNotNull(webResponse);
	}

	@Test
	public void Getメソッドでアクセスすると全学生の情報を取得できる() throws Exception {
		Map<String,Object> parameter =new HashMap<String,Object>();
		parameter.put("Administrator", "fyoshida");
		
		getMessages("InitializeServlet");
		getMessages("HandUpServlet");
		getMessagesWithSession("GetHandUpPcServlet",parameter);

		String response = webResponse.getText();
		List<StudentJson> pcJsonList =JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);
		assertEquals(1,pcJsonList.size());

		StudentJson pcJsonFirst = pcJsonList.get(0);
		assertEquals("ics801",pcJsonFirst.getPcId());
		assertEquals("133.44.118.158",pcJsonFirst.getIpAdress());
	}
	


}
