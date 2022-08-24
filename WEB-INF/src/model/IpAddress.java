package model;

import java.util.StringTokenizer;

public class IpAddress {
	private char[] addresses = new char[4];

	public IpAddress(String ipAddressString) throws IpAddressException {
		StringTokenizer st = new StringTokenizer(ipAddressString, ".");
		if (st.countTokens() > 4) {
			throw new IpAddressException();
		}

		for (int i = 0; i < 4; i++) {
			int address = Integer.parseInt(st.nextToken());
			addresses[i] = checkAddress(address);
		}
	}

	public void set(int i, char address) throws IpAddressException {
		addresses[i] = checkAddress(address);
	}

	public char get(int i) {
		return addresses[i];
	}

	public String get() {
		return "" + addresses[0] + "." + addresses[1] + "." + addresses[2] + "." + addresses[3];
	}

	private char checkAddress(int address) throws IpAddressException {
		if (address >= 255) {
			throw new IpAddressException();
		}

		return (char) address;
	}
}
