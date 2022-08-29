package network;

public class DummyNetwork implements NetworkInterface {

	String default_ipAddress = "133.44.118.191";
	String default_hostName = "ics834";

	String ipAddress;
	String hostName;

	public DummyNetwork() {
	}

	public DummyNetwork(String ipAddress, String hostName) {
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}

	@Override
	public String getClientIpAddress() {
		if (ipAddress != null) {
			return ipAddress;
		} else {
			return default_ipAddress;
		}
	}

	@Override
	public String getClientHostName() {
		if (hostName != null) {
			return hostName;
		} else {
			return "ics834";
		}
	}

}
