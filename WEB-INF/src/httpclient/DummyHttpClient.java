package httpclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

public class DummyHttpClient implements IHttpClient {

	InetAddress default_ipAddress;
	String default_hostName = "ics834";

	InetAddress ipAddress;
	String hostName;

	public DummyHttpClient() {
		try {
			default_ipAddress = InetAddress.getByName("133.44.118.191");
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public DummyHttpClient(InetAddress ipAddress, String hostName) {
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}

	@Override
	public Optional<InetAddress> getClientIpAddress() {
		if (ipAddress != null) {
			return Optional.of(ipAddress);
		} else {
			return Optional.of(default_ipAddress);
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
