package servlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.junit.Test;

import common.TestServlet;
import model.Pc;
import model.StudentManager;

public class GetAllPcServletTest extends TestServlet {

	private static final String SERVLET_NAME = "GetAllPcServlet";

	@Test
	public void servletにGetメソッドでアクセスできる() throws Exception {
		List<Pc> pcList=new LinkedList<Pc>();
		StudentManager studentManager=new StudentManager(pcList);
		
		Map<String,Object> scParameters=new HashMap<String,Object>();
		scParameters.put("StudentManager", studentManager);
		
		getMessages(scParameters);
		assertNotNull(webResponse);
	}
	
	protected void getMessages(Map<String,Object> scParameters) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(SERVLET_NAME);
		setMethodType(MethodType.GET);

		startServer();
		for(Entry<String,Object> entry : scParameters.entrySet()) {
			servletContext.setAttribute(entry.getKey(), entry.getValue());
		}
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


}
