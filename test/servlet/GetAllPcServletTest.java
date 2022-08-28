package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GetAllPcServletTest extends TestServletBase {

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
}
