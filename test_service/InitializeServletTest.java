

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.StudentManager;
import network.NetworkFactory;
import network.NetworkType;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class InitializeServletTest extends TestServletBase {

	@BeforeClass
	public static void リポジトリとネットワークを設定() {
		RepositoryFactory.repositoryType = RepositoryType.Dummy;
		NetworkFactory.networkType = NetworkType.Dummy;
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
	public void GetメソッドでアクセスするとServletContextにStudentManagerが取得できる() throws Exception {
		getMessages("InitializeServlet");
		assertNotNull(webResponse);

		StudentManager studentManager =(StudentManager)servletContext.getAttribute("StudentManager");
		assertEquals(studentManager.getStudentList().size(),62);
	}
}
