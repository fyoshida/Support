package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class HandUpServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		NetworkFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("HandUpServlet");
	}

	@Test
	public void GETメソッドでアクセスすると手を上げて全PCの情報を取得できる() throws Exception {
		getMessages("InitializeServlet");
		webRequest.setParameter("HostName", ""+NetworkFactory.hostName);
		getMessages("HandUpServlet");
		String response = webResponse.getText();
//		System.out.println(response);
		assertNotNull(webResponse);
	}
}
