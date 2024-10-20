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

public class HandUpServletTest extends TestServletBase {

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
	}

	@Test
	public void GETメソッドでアクセスすると手を上げられる() throws Exception {
		String targetPcIpAddress = DummyHttpClient.IP_ADDRESS;
		String targetPcHostName = DummyHttpClient.HOST_NAME;
		
		getMessages("InitializeServlet");
		getMessages("HandUpServlet");

		List<StudentJson> pcJsonList =JsonConverter.getPcJsonList(webResponse.getText());
		assertNotNull(pcJsonList);
		assertEquals(pcJsonList.size(),71);

		StudentJson pcJson = StudentJsonHelper.findPcJson(pcJsonList,targetPcIpAddress);
		assertNotNull(pcJson);
		assertEquals(pcJson.getPcId(),targetPcHostName);
		assertEquals(pcJson.getHelpStatus(),HelpStatus.Troubled.getDisplayName());
	}
}
