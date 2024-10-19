package domain.entities;
import static org.apache.commons.lang3.Validate.*;

import java.net.InetAddress;
public class Pc {
	protected final InetAddress  ipAddress;
	protected final String hostName;

	//--------コンストラクタ--------------
	public Pc(InetAddress  ipAddress,String hostName) {
		notNull(ipAddress);
		notNull(hostName);
		this.ipAddress=ipAddress;
		this.hostName=hostName;
	}

	//--------アクセッサ--------------

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

}
