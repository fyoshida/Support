package model;

import static org.apache.commons.lang3.Validate.*;

public class Pc {
	protected final IpAddress ipAddress;
	protected final String hostName;
	protected boolean isStudent = false;

	//--------コンストラクタ--------------
	public Pc(IpAddress ipAddress, String hostName) {
		notNull(ipAddress);
		notNull(hostName);
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}

	public Pc(IpAddress ipAddress, String hostName, boolean isStudent) {
		notNull(ipAddress);
		notNull(hostName);
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.isStudent = isStudent;
	}

	//--------アクセッサ--------------

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
}
