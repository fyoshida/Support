package model;

import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;
import java.util.StringTokenizer;

public class IpAddress {
	private int[] addresses = new int[4];

	public IpAddress(String ipAddressString) {
		notNull(ipAddressString);
		notBlank(ipAddressString);

		StringTokenizer st = new StringTokenizer(ipAddressString, ".");
		isTrue(st.countTokens() == 4);

		for (int i = 0; i < 4; i++) {
			int address = Integer.parseInt(st.nextToken());
			inclusiveBetween(0, 255, address);

			addresses[i] = address;
		}
	}

	public String get() {
		return "" + addresses[0] + "." + addresses[1] + "." + addresses[2] + "." + addresses[3];
	}

	//--------比較用基本関数--------------

	@Override
	public int hashCode() {
		return Objects.hash(addresses[0], addresses[1], addresses[2], addresses[3]);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		IpAddress targetIpAddress;
		if (object instanceof String) {
			targetIpAddress=new IpAddress((String)object);
		}else if ((object instanceof IpAddress)) {
			targetIpAddress = (IpAddress) object;
		}else {
			return false;
		}

		if (targetIpAddress.addresses.length != 4) {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			if (addresses[i] != targetIpAddress.addresses[i]) {
				return false;
			}
		}
		return true;
	}
}
