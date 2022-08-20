package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class DummyNetwork implements NetworkInterface{

	@Override
	public String getClientIpAddress() {
		return "133.44.118.191";
	}

}
