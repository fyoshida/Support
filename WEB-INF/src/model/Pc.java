package model;

import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;
import java.util.StringTokenizer;

public class Pc {
	protected String ipAddress;
	protected String hostName;
	protected boolean isStudent = false;

	//--------コンストラクタ--------------
	public Pc(String ipAddress, String hostName, boolean isStudent) {
		this.ipAddress = checkIpAddress(ipAddress);
		this.hostName = checkHostName(hostName);
		this.isStudent = isStudent;
	}

	private String checkIpAddress(String ipAddress) {
		notNull(ipAddress);
		notBlank(ipAddress);

		StringTokenizer st = new StringTokenizer(ipAddress, ".");
		isTrue(st.countTokens() == 4);

		int[] addresses = new int[4];
		for (int i = 0; i < 4; i++) {
			int address = Integer.parseInt(st.nextToken());
			inclusiveBetween(0, 255, address);

			addresses[i] = address;
		}
		
		return  addresses[0]+"."+addresses[1]+"."+addresses[2]+"."+addresses[3];
	}

	private String checkHostName(String hostName) {
		notNull(hostName);
		inclusiveBetween(0,12,hostName.length());
		isTrue(hostName.matches("[0-9a-zA-Z]*"));
		return hostName;
	}
	
	//--------アクセッサ--------------
	public String getIpAddress() {
		return ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public boolean isStudent() {
		return isStudent;
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
		return pc.getIpAddress().equals(this.getIpAddress());
	}

}
