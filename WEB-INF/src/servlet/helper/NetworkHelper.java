package servlet.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import model.IpAddress;

public class NetworkHelper {
	public String getIpAddress() {
		String clientIpAddr=null;

		try {
			InetAddress cIpAddr = InetAddress.getLocalHost();
			clientIpAddr = cIpAddr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return clientIpAddr;
	}

	public String getIpAddress(HttpServletRequest req) {
		String clientIpAddr = req.getRemoteAddr();

		try {
			if (clientIpAddr.equals("0:0:0:0:0:0:0:1")) {
				InetAddress cIpAddr = InetAddress.getLocalHost();
				clientIpAddr = cIpAddr.getHostAddress();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return clientIpAddr;
	}
}
