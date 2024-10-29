package domain.valueobjects;

import static org.apache.commons.lang3.Validate.*;

import java.util.Objects;
import java.util.StringTokenizer;

public class IpAddress {
	private int[] addresses = new int[4];

	public IpAddress(String strIpAddress) {
		if(strIpAddress==null || strIpAddress.equals("")) {
			strIpAddress ="0.0.0.0";
		}

		StringTokenizer st = new StringTokenizer(strIpAddress, ".");
		isTrue(st.countTokens() == 4);

		for (int i = 0; i < 4; i++) {
			int address = Integer.parseInt(st.nextToken());
			inclusiveBetween(0, 255, address);

			addresses[i] = address;
		}
	}

	public String getDisplayName() {
		return "" + addresses[0] + "." + addresses[1] + "." + addresses[2] + "." + addresses[3];
	}

	@Override
	public int hashCode() {
        return Objects.hash(addresses[0],addresses[1],addresses[2],addresses[3]);
    }

	@Override
	public boolean equals(Object object) {
		if(this==object) {
			return true;
		}
		if (!(object instanceof IpAddress)) {
			return false;
		}

		IpAddress ipAddress=(IpAddress)object;
		if (ipAddress.addresses.length != 4) {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			if (addresses[i] != ipAddress.addresses[i]) {
				return false;
			}
		}
		return true;
	}
}
