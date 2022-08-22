package servlet;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

public class NetworkHelper {
	public String getIpAddress(HttpServletRequest req) {
		InetAddress cIpAddr = InetAddress.getLocalHost();
		clientIpAddr = cIpAddr.getHostAddress();
	}
		String clientIpAddr = req.getRemoteAddr();
	if(clientIpAddr.equals("0:0:0:0:0:0:0:1")) {
		InetAddress cIpAddr = InetAddress.getLocalHost();
		clientIpAddr = cIpAddr.getHostAddress();
	}
	}
}

