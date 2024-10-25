package httpclient;

import javax.servlet.http.HttpServletRequest;

import domain.valueobjects.IpAddress;

public class HttpClientFactory {
	public static String ipAddress="133.44.118.158";
	public static String hostName="ics";

	public static IHttpClient getNetwork(HttpServletRequest request) {
		return new ServletHttpClient(request);
	}
	
	public static IHttpClient getNetwork(String strIpAddress) {
		DummyHttpClient dummyHttpClient=new DummyHttpClient();
		IpAddress ipAddress = new IpAddress(strIpAddress);
		dummyHttpClient.setIpAddress(ipAddress);
		return dummyHttpClient;
	}

}
