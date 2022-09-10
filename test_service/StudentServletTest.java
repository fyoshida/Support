

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import helper.JsonConverter;
import helper.PcJson;
import model.HelpStatus;
import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class StudentServletTest extends _TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		NetworkFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("StudentServlet");
	}

	@Test
	public void GET_全PC情報を取得できる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		callHttp(MethodType.GET,"StudentServlet");

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

	@Test
	public void Get_あるPCの情報を取得できる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		
		Map<String,String> parameters =new LinkedHashMap<String,String>();
		parameters.put("HostName", NetworkFactory.hostName);
		callHttpWithParameter(MethodType.GET,"StudentServlet",parameters);

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);

		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),NetworkFactory.ipAddress);
		assertTrue(pcJson.getPcId().equals(NetworkFactory.hostName));
	}
	
	@Test
	public void Put_手を上げられる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		
		Map<String,String> parameters =new LinkedHashMap<String,String>();
		parameters.put("HostName", NetworkFactory.hostName);
		parameters.put("HelpStatus", HelpStatus.Troubled.toString());
		callHttpWithParameter(MethodType.PUT,"StudentServlet",parameters);

		parameters.clear();
		parameters.put("HostName", NetworkFactory.hostName);
		callHttpWithParameter(MethodType.GET,"StudentServlet",parameters);

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);

		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),NetworkFactory.ipAddress);
		assertTrue(pcJson.getPcId().equals(NetworkFactory.hostName));
		assertTrue(pcJson.getHelpStatus().equals(HelpStatus.Troubled.toString()));
	}
	
	@Test
	public void Put_手を下げられる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		
		Map<String,String> parameters =new LinkedHashMap<String,String>();
		parameters.put("HostName", NetworkFactory.hostName);
		parameters.put("HelpStatus", HelpStatus.None.toString());
		callHttpWithParameter(MethodType.PUT,"StudentServlet",parameters);

		parameters.clear();
		parameters.put("HostName", NetworkFactory.hostName);
		callHttpWithParameter(MethodType.GET,"StudentServlet",parameters);

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);

		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),NetworkFactory.ipAddress);
		assertTrue(pcJson.getPcId().equals(NetworkFactory.hostName));
		assertTrue(pcJson.getHelpStatus().equals(HelpStatus.None.toString()));
	}
	
	@Test
	public void Put_サポートできる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		
		Map<String,String> parameters =new LinkedHashMap<String,String>();
		parameters.put("HostName", NetworkFactory.hostName);
		parameters.put("HelpStatus", HelpStatus.Supporting.toString());
		callHttpWithParameter(MethodType.PUT,"StudentServlet",parameters);

		parameters.clear();
		parameters.put("HostName", NetworkFactory.hostName);
		callHttpWithParameter(MethodType.GET,"StudentServlet",parameters);

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);

		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),NetworkFactory.ipAddress);
		assertTrue(pcJson.getPcId().equals(NetworkFactory.hostName));
		assertTrue(pcJson.getHelpStatus().equals(HelpStatus.Supporting.toString()));
	}
	
	@Test
	public void Put_ユーザ名を設定できる() throws Exception {
		callHttp(MethodType.GET,"InitializeServlet");
		
		Map<String,String> parameters =new LinkedHashMap<String,String>();
		parameters.put("HostName", NetworkFactory.hostName);
		parameters.put("UserName", "abc");
		callHttpWithParameter(MethodType.PUT,"StudentServlet",parameters);

		parameters.clear();
		parameters.put("HostName", NetworkFactory.hostName);
		callHttpWithParameter(MethodType.GET,"StudentServlet",parameters);

		String response = webResponse.getText();
		PcJson pcJson =JsonConverter.getPcJson(response);

		assertNotNull(pcJson);
		assertEquals(pcJson.getIpAdress(),NetworkFactory.ipAddress);
		assertTrue(pcJson.getPcId().equals(NetworkFactory.hostName));
		assertTrue(pcJson.getUserName().equals("abc"));
	}
}
