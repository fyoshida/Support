

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

public class _TestServletBase extends _TestServlet{

	public void callHttp(MethodType methodType, String servletName) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(methodType);

		startServer();
		callServlet();
	}

	public void callHttpWithParameter(MethodType methodType,String servletName,Map<String, String> parameters) throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(methodType);

		for(Entry<String, String> element : parameters.entrySet()) {
			webRequest.setParameter(element.getKey(), element.getValue());
		}
		
		startServer();
		callServlet();
	}
	
	protected void callHttpWithServletContext(MethodType methodType,String servletName, Map<String, Object> scParameters)
			throws MalformedURLException, IOException, ServletException, Exception {
		setServletName(servletName);
		setMethodType(methodType);

		startServer();
		
		for (Entry<String, Object> entry : scParameters.entrySet()) {
			servletContext.setAttribute(entry.getKey(), entry.getValue());
		}

		callServlet();
	}

//	protected void postMessage(String servletName) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(servletName);
//		setMethodType(MethodType.POST);
//
//		startServer();
//		callServlet();
//	}
//
//	protected void postMessageWithParameter(String servletName,Map<String, String> parameters) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(servletName);
//		setMethodType(MethodType.POST);
//
//		for(Entry<String, String> element : parameters.entrySet()) {
//			webRequest.setParameter(element.getKey(), element.getValue());
//		}
//		
//		startServer();
//		callServlet();
//	}


//	protected void putMessage(String servletName) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(servletName);
//		setMethodType(MethodType.PUT);
//
//		startServer();
//		callServlet();
//	}
//
//	protected void putMessageWithParameter(String servletName,Map<String, String> parameters) throws MalformedURLException, IOException, ServletException, Exception {
//		setServletName(servletName);
//		setMethodType(MethodType.PUT);
//
//		for(Entry<String, String> element : parameters.entrySet()) {
//			webRequest.setParameter(element.getKey(), element.getValue());
//		}
//		
//		startServer();
//		callServlet();
//	}
//
//
//
//	private void setServletContext(Map<String, Object> scParameters) {
//		for (Entry<String, Object> entry : scParameters.entrySet()) {
//			servletContext.setAttribute(entry.getKey(), entry.getValue());
//		}
//	}
}
