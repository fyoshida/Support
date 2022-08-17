package common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;

import com.meterware.httpunit.DeleteMethodWebRequest;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.PutMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class TestServlet {
	public enum MethodType {
		GET, POST, PUT, DELETE
	}

	private String servletName = "";
	private MethodType methodType = null;
	private String methodName = "";

	private String TEST_URL_NAME = "http://localhost/";
	private String TEST_PACKAGE_NAME = "servlet";

	private ServletRunner sr;
	private ServletUnitClient sc;
	private InvocationContext ic;
	Object servlet;

	protected WebRequest webRequest;
	protected WebResponse webResponse;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	private String sessionID = "JSESSION=1";

	@Before
	public void setUp() throws Exception {
		HttpUnitOptions.setScriptingEnabled(false);
		HttpUnitOptions.setExceptionsThrownOnScriptError(false);
		HttpUnitOptions.setExceptionsThrownOnErrorStatus(false);
		HttpUnitOptions.setDefaultCharacterSet("UTF-8");

		System.getProperty("user.dir").toLowerCase();
		sessionID = "JSESSION=1";

		sr = new ServletRunner();
		String str = TEST_PACKAGE_NAME + "."+getClassName();
		sr.registerServlet(getClassName(), str);
	}

	protected void registServlet(String servletName) {
		sr.registerServlet(servletName, TEST_PACKAGE_NAME + "." + servletName);
	}

	protected void setServletName(String servletName) {
		this.servletName = servletName;
	}

	protected void setMethodType(MethodType method) {
		this.methodType = method;
		setWebRequest();
	}

	private void setWebRequest() {
		String testUrl = TEST_URL_NAME + servletName;
		switch (methodType) {
		case GET:
			methodName = "doGet";
			webRequest = new GetMethodWebRequest(testUrl);
			break;
		case POST:
			methodName = "doPost";
			webRequest = new PostMethodWebRequest(testUrl);
			break;
		case PUT:
			methodName = "doPut";
			webRequest = new PutMethodWebRequest(testUrl);
			break;
		case DELETE:
			methodName = "doDelete";
			webRequest = new DeleteMethodWebRequest(testUrl);
			break;
		default:
		}
	}

	protected void startServer() throws MalformedURLException, IOException, ServletException{
		sc = sr.newClient();
		sc.setHeaderField("Cookie", sessionID);
		ic = sc.newInvocation(webRequest);

		servlet = ic.getServlet();
		request = ic.getRequest();
		response = ic.getResponse();
		session = request.getSession();
	}

	protected void callServlet() throws Exception {
		Method m = servlet.getClass().getMethod(methodName,
				new Class[] { HttpServletRequest.class, HttpServletResponse.class });
		m.invoke(servlet, new Object[] { request, response });

		webResponse = sc.getResponse(ic);
		String[] headers = webResponse.getHeaderFields("SET-Cookie");
		if (headers != null && headers.length > 0) {
			sessionID = headers[0].substring(0, headers[0].indexOf(";"));
		}
	}

	@After
	public void tearDown() {
		webRequest = null;
		webResponse = null;
		request = null;
		response = null;
		session = null;
	}

	private String getClassName() {
		String testClassName = this.getClass().getName();
		String testClassLowerName = testClassName.toLowerCase();
		int startPos=testClassLowerName.indexOf(".")+1;
		int endPos = testClassLowerName.indexOf("test");
		return testClassName.substring(startPos,endPos);
	}
}
