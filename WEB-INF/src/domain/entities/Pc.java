package domain.entities;
import static org.apache.commons.lang3.Validate.*;

import domain.valueobjects.IpAddress;
public class Pc {
	protected final IpAddress  ipAddress;
	protected final String hostName;

	//--------コンストラクタ--------------
	public Pc(IpAddress  ipAddress,String hostName) {
		notNull(ipAddress);
		notNull(hostName);
		this.ipAddress=ipAddress;
		this.hostName=hostName;
	}

	//--------アクセッサ--------------

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}
	
	public boolean equals(Object other) {
		if(!( other instanceof Pc)){
			return false;
		}
		Pc pc = (Pc)other;
		if(!ipAddress.equals(pc.ipAddress)) {
			return false;
		}
		if(!hostName.equals(pc.hostName)) {
			return false;
		}
		return true;
	}

}
