package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.StudentManager;
import repository.RepositoryFactory;
import repository.RepositoryType;

public class InitializeServletTest extends TestServletBase {

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
