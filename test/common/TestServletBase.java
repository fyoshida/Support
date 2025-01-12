package common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

public class TestServletBase extends TestServlet{

	protected void getMessagesWithServletContext(String servletName, Map<String, Object> scParameters)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(MethodType.GET);

		startServer();
		setServletContext(scParameters);
		callServlet();
	}
	
	protected void getMessagesWithSession(String servletName, Map<String, Object> sessionParameters)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(MethodType.GET);

		startServer();
		setSession(sessionParameters);
		callServlet();
	}

	protected void getMessages(String servletName) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(MethodType.GET);

		startServer();
		callServlet();
	}

	protected void postMessage(String servletName) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(MethodType.POST);

		startServer();
		callServlet();
	}

	protected void postMessage(String servletName,String text, String name)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
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
	
	private void setSession(Map<String, Object> sessionParameters) {
		for (Entry<String, Object> entry : sessionParameters.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}
	}
}
