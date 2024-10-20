package httpclient;

import domain.valueobjects.IpAddress;

public class DummyHttpClient implements IHttpClient {
	public static String IP_ADDRESS="133.44.118.158";
	public static String HOST_NAME="ics801";

	IpAddress default_ipAddress;
	String default_hostName;
	
	IpAddress ipAddress;

	public DummyHttpClient() {
		default_ipAddress = new IpAddress(IP_ADDRESS);
		default_hostName=HOST_NAME;
	}

	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress=ipAddress;
	}

	@Override
	public IpAddress getClientIpAddress() {
		if (ipAddress != null) {
			return ipAddress;
		} else {
			return default_ipAddress;
		}
	}

}
