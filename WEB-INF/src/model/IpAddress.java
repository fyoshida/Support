package model;

import static org.apache.commons.lang3.Validate.*;
import java.util.StringTokenizer;

public class IpAddress {
	private int[] addresses = new int[4];

	public IpAddress(String ipAddressString) {
		notNull(ipAddressString);
		notBlank(ipAddressString);

		StringTokenizer st = new StringTokenizer(ipAddressString, ".");
		isTrue(st.countTokens()==4);

		for (int i = 0; i < 4; i++) {
			int address = Integer.parseInt(st.nextToken());
			inclusiveBetween(0,255,address);

			addresses[i] = address;
		}
	}

	public String get() {
		return "" + addresses[0] + "." + addresses[1] + "." + addresses[2] + "." + addresses[3];
	}

}
