package servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import common.TestServlet;

public class GetAllPcServletTest extends TestServlet {

	private static String SERVLET_NAME;

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

	protected void getMessages(String servletName) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(MethodType.GET);

		startServer();
		callServlet();
	}


}
