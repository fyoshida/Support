package model;

public class PcId {
	private final String hostName; //icsXXX
	private final String ipAddress; //ドメインは133.44.118.158-228の間

	public PcId(String hostName, String ipAddress) {
		this.hostName = hostName;
		this.ipAddress = ipAddress;
	}

	public boolean equals(PcId pcId) {
		if( this.hostName.equals(pcId.hostName)) {
			return true;
		}else{
			return false;
		}
	}

	public String getHostName() {
		return hostName;
	}

	public String getIpAdress() {
		return ipAddress;
	}
}
