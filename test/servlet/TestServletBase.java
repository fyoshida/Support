package servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import common.TestServlet;

public class TestServletBase extends TestServlet{
	protected static String SERVLET_NAME;

	protected void getMessages(Map<String, Object> scParameters)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		setServletContext(scParameters);
		callServlet();
	}

	protected void getMessages2() throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		callServlet();
	}

	protected void getMessages() throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		callServlet();
	}

	protected void postMessage() throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.POST);

		startServer();
		callServlet();
	}

	protected void postMessage(String text, String name)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.POST);
		webRequest.setParameter("Text", text);
		webRequest.setParameter("Name", name);
		startServer();
		callServlet();
	}

	private void setServletContext(Map<String, Object> scParameters) {
		for (Entry<String, Object> entry : scParameters.entrySet()) {
			servletContext.setAttribute(entry.getKey(), entry.getValue());
		}
	}
}
