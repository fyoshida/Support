package network;

public class DummyNetwork implements NetworkInterface{

	@Override
	public String getClientIpAddress() {
		return "133.44.118.191";
	}

	@Override
	public String getClientHostName() {
		return "ics834";
	}

}
