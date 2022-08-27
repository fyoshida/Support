package model;

public class PcBean {
	protected IpAddress ipAddress=null;
	protected String hostName="";
	protected boolean isStudent = false;

	//--------アクセッサ--------------
	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public IpAddress getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

}

