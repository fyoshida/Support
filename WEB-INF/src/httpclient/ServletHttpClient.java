package httpclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class ServletHttpClient implements IHttpClient {
	private HttpServletRequest req;

	public ServletHttpClient(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public Optional<InetAddress> getClientIpAddress() {
		String clientIpAddress = req.getRemoteAddr();		if (clientIpAddress.equals("0:0:0:0:0:0:0:1")) {			try {				 return Optional.of(InetAddress.getLocalHost());			} catch (UnknownHostException e) {				return Optional.empty();			}		}else {
			 try {
				return Optional.of(InetAddress.getByName(clientIpAddress));
			} catch (UnknownHostException e) {
				return Optional.empty();
			}
		}
	}

	@Override
	public String getClientHostName() {
		return req.getParameter("HostName");
//		String url = req.getRequestURI();
//
//		int beginIdx = url.lastIndexOf("/"); //icsのsの位置を取得
//		String clientId = url.substring(beginIdx + 1); //実際は番号だけ知りたいので+1する
//
//		return clientId;
	}
}
