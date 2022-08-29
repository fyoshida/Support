package servlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GetPcServletTest extends TestServletBase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		registServlet("InitializeServlet");
		registServlet("GetPcServlet");
	}

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		getMessages("InitializeServlet");
		getMessages("GetPcServlet");
		String response = webResponse.getText();
		System.out.println(response);
		assertNotNull(webResponse);
	}
}
