package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.TestServletBase;
import httpclient.HttpClientFactory;
import httpclient.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;
import services.StudentService;

public class InitializeServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Memory;
		HttpClientFactory.networkType = NetworkType.Dummy;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();

		RepositoryFactory.repositoryType=RepositoryType.File;
		registServlet("InitializeServlet");
	}

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		getMessages("InitializeServlet");
		assertNotNull(webResponse);
	}

	@Test
	public void GetメソッドでアクセスするとServletContextにStudentServiceが取得できる() throws Exception {
		getMessages("InitializeServlet");
		assertNotNull(webResponse);

		StudentService studentService =(StudentService)servletContext.getAttribute("StudentService");
		assertEquals(71,studentService.getStudentList().size());
	}
}
