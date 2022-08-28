package servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.junit.Test;

import common.TestServlet;
import model.StudentManager;

public class InitializeServletTest extends TestServlet {

	private static final String SERVLET_NAME = "InitializeServlet";

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		getMessages();
		assertNotNull(webResponse);
	}

	@Test
	public void GetメソッドでアクセスするとServletContextにStudentManagerが取得できる() throws Exception {
		getMessages();
		assertNotNull(webResponse);
		
		StudentManager studentManager =(StudentManager)servletContext.getAttribute("StudentManager");
		assertEquals(studentManager.getStudentList().size(),62);
	}
	
	private void getMessages(Map<String,Object> scParameters) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		for(Entry<String,Object> entry : scParameters.entrySet()) {
			servletContext.setAttribute(entry.getKey(), entry.getValue());
		}
		callServlet();
	}

	private void getMessages() throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		callServlet();
	}

	private void postMessage() throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.POST);

		startServer();
		callServlet();
	}

	private void postMessage(String text, String name)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.POST);
		webRequest.setParameter("Text", text);
		webRequest.setParameter("Name", name);
		startServer();
		callServlet();
	}
}
