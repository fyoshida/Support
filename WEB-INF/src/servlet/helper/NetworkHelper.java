package servlet.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import model.IpAddress;
import network.INetwork;
import network.ServletNetwork;

public class NetworkHelper {
	public static IpAddress getIpAddressWithServletNetwork(HttpServletRequest req) {
		IpAddress ipAddress = null;

		try {
			INetwork network = new ServletNetwork(req);
			String clientId = network.getClientHostName();
			ipAddress = new IpAddress(clientId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipAddress;
	}

	public static IpAddress getIpAddress(HttpServletRequest req) {
		IpAddress ipAddress = null;

		try {
			String clientIpAddr = req.getRemoteAddr();
			if (clientIpAddr.equals("0:0:0:0:0:0:0:1")) {
				InetAddress cIpAddr = InetAddress.getLocalHost();
				clientIpAddr = cIpAddr.getHostAddress();
			}
			ipAddress = new IpAddress(clientIpAddr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipAddress;
	}

//	public static IpAddress getIpAddress(HttpServletRequest req) {
//		NetworkInterface network = new ServletNetwork(req);
//		String clientId = network.getClientId();
//		IpAddress ipAddress = new IpAddress(clientId);
//	}
}
