package model;

import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;

public class Pc {
	protected final IpAddress ipAddress;
	protected final HostName hostName;
	protected boolean isStudent = false;

	//--------コンストラクタ--------------
	public Pc(String ipAddress, String hostName, boolean isStudent) {
		this.ipAddress = new IpAddress(ipAddress);
		this.hostName = new HostName(hostName);
		this.isStudent = isStudent;
	}

	//--------アクセッサ--------------

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public HostName getHostName() {
		return hostName;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	//--------比較用基本関数--------------
	@Override
	public int hashCode() {
		return Objects.hash(ipAddress);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Pc)) {
			return false;
		}

		Pc pc = (Pc) object;
		if (pc.getIpAddress().equals(this.getIpAddress())) {
			return false;
		}
		return true;

	}

}
