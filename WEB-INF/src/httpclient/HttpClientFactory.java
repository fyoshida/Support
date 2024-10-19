package httpclient;

import javax.servlet.http.HttpServletRequest;

public class HttpClientFactory {

	public static NetworkType networkType = NetworkType.Dummy;

	public static IHttpClient getNetwork(HttpServletRequest request) {
		switch (networkType) {
		case Servlet:
			return new ServletHttpClient(request);
		default:
			return new DummyHttpClient();
		}
	}
}
