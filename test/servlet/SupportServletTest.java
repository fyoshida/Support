package servlet;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestServletBase;
import domain.valueobjects.HelpStatus;
import helper.JsonConverter;
import helper.StudentJson;
import helper.StudentJsonHelper;
import httpclient.DummyHttpClient;
import httpclient.HttpClientFactory;
import httpclient.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class SupportServletTest extends TestServletBase {

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
		registServlet("HandDownServlet");
		registServlet("SupportServlet");
	}

	@Test
	public void GETメソッドでアクセスするとサポートできる() throws Exception {
		String targetPcIpAddress = DummyHttpClient.IP_ADDRESS;
		String targetPcHostName = DummyHttpClient.HOST_NAME;

		getMessages("InitializeServlet");

		webRequest.setParameter("HostName", "" + targetPcHostName);
		getMessages("HandUpServlet");

		webRequest.setParameter("HostName", "" + targetPcHostName);
		getMessages("SupportServlet");

		String response = webResponse.getText();
		List<StudentJson> pcJsonList = JsonConverter.getPcJsonList(response);
		assertNotNull(pcJsonList);

		StudentJson pcJson = StudentJsonHelper.findPcJson(pcJsonList, targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(), targetPcHostName);
		assertEquals(pcJson.getHelpStatus(), HelpStatus.Supporting.getDisplayName());

	}
}
