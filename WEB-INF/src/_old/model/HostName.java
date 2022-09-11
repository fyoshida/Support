package _old.model;

import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;

public class HostName {
	private String hostName;

	public HostName(String hostName) {
		notNull(hostName);
		inclusiveBetween(0,12,hostName.length());
		isTrue(hostName.matches("[0-9a-zA-Z]*"));
		this.hostName = hostName;
	}

	public String get() {
		return hostName;
	}

	//--------比較用基本関数--------------

	@Override
	public int hashCode() {
		return Objects.hash(hostName);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		HostName targetHostName;
		if (object instanceof String) {
			targetHostName=new HostName((String)object);
		}else if ((object instanceof HostName)) {
			targetHostName = (HostName) object;
		}else {
			return false;
		}

		if (hostName.equals(targetHostName.hostName)) {
				return true;
		}
		return false;
	}

}
